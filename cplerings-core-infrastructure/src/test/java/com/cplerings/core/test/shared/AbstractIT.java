package com.cplerings.core.test.shared;

import static org.assertj.core.api.Assertions.assertThat;

import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.api.shared.NoResponse;
import com.cplerings.core.common.profile.ProfileConstant;
import com.cplerings.core.infrastructure.CplringsCoreApplication;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.util.UriBuilder;

import com.blazebit.persistence.CriteriaBuilderFactory;
import com.blazebit.persistence.querydsl.BlazeJPAQuery;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.lang.reflect.Modifier;
import java.net.URI;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

@Slf4j
@SpringBootTest(
        classes = {
                CplringsCoreApplication.class
        },
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@Import(ITConfiguration.class)
@ActiveProfiles(ProfileConstant.TEST)
public abstract class AbstractIT {

    private static final Duration RESPONSE_TIMEOUT = Duration.ofSeconds(30);
    private static final String BASE_URL = "http://localhost:%d/%s";
    private static final String AUTHENTICATION_HEADER = "Authorization";
    private static final String BEARER_FORMAT = "Bearer %s";

    @LocalServerPort
    private int port;

    @Value("${cplerings.api.path}")
    private String apiPath;

    @Autowired
    private Flyway flyway;

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private CriteriaBuilderFactory cbf;

    @Autowired
    private ObjectMapper objectMapper;

    protected <B> RequestBuilder<B> requestBuilder() {
        return new RequestBuilder<>();
    }

    @BeforeEach
    protected final void populateDatabase() {
        flyway.clean();
        flyway.migrate();
    }

    protected <T> BlazeJPAQuery<T> createQuery() {
        return new BlazeJPAQuery<>(em, cbf);
    }

    protected final TestDataLoader getTestDataLoader(String folder) {
        return TestDataLoader.builder()
                .folder(folder)
                .objectMapper(objectMapper)
                .build();
    }

    protected final void thenResponseIsOk(WebTestClient.ResponseSpec response) {
        response.expectStatus().is2xxSuccessful();
    }

    protected final void thenResponseIsForbidden(WebTestClient.ResponseSpec response) {
        response.expectStatus().isForbidden();
    }

    protected final void thenNoResponseIsReturned(WebTestClient.ResponseSpec response) {
        final NoResponse responseBody = response.expectBody(NoResponse.class)
                .returnResult()
                .getResponseBody();
        assertThat(responseBody).isNotNull();
        assertThat(responseBody.getType()).isEqualTo(AbstractResponse.Type.INFO);
        assertThat(responseBody.getData()).isNull();
    }

    protected final class RequestBuilder<B> {

        private final Map<String, String> queries = new TreeMap<>();
        private String path = "";
        private Method method;
        private String token;
        private B body;

        private RequestBuilder() {
        }

        public RequestBuilder<B> path(String path) {
            this.path = path;
            return this;
        }

        public RequestBuilder<B> method(Method method) {
            this.method = method;
            return this;
        }

        public RequestBuilder<B> authorizationHeader(String token) {
            this.token = token;
            return this;
        }

        public RequestBuilder<B> body(B body) {
            this.body = body;
            return this;
        }

        public <Q> RequestBuilder<B> query(Q queryObject) {
            extractQueriesFromObject(queryObject);
            return this;
        }

        private <Q> void extractQueriesFromObject(Q queryObject) {
            final Collection<Class<?>> classAndItsParents = extractClassAndItsParents(queryObject);
            classAndItsParents.stream()
                    .flatMap(clazz -> Arrays.stream(clazz.getDeclaredFields()))
                    .filter(field -> !Modifier.isStatic(field.getModifiers()))
                    .forEach(field -> {
                        final boolean originalAccessible = field.canAccess(queryObject);
                        try {
                            field.setAccessible(true);
                            final Object value = field.get(queryObject);
                            if (value != null) {
                                queries.put(field.getName(), Objects.toString(value));
                            }
                        } catch (Exception e) {
                            log.error(e.getMessage(), e);
                        } finally {
                            field.setAccessible(originalAccessible);
                        }
                    });
        }

        private <Q> Collection<Class<?>> extractClassAndItsParents(Q obj) {
            final Collection<Class<?>> classAndItsParents = new HashSet<>();
            Class<?> clazz = obj.getClass();
            while (clazz != null && !clazz.equals(Object.class)) {
                classAndItsParents.add(clazz);
                clazz = clazz.getSuperclass();
            }
            return classAndItsParents;
        }

        public WebTestClient.ResponseSpec send() {
            Objects.requireNonNull(path);
            final WebTestClient webTestClient = WebTestClient.bindToServer()
                    .baseUrl(String.format(BASE_URL, port, apiPath))
                    .responseTimeout(RESPONSE_TIMEOUT)
                    .build();
            final WebTestClient.RequestHeadersSpec<?> requestHeadersSpec = switch (method) {
                case GET -> webTestClient.get()
                        .uri(this::buildUri);
                case POST -> {
                    var tmp = webTestClient.post()
                            .uri(this::buildUri);
                    if (body != null) {
                        tmp.bodyValue(body);
                    }
                    yield tmp;
                }
                case PUT -> {
                    var tmp = webTestClient.put()
                            .uri(this::buildUri);
                    if (body != null) {
                        tmp.bodyValue(body);
                    }
                    yield tmp;
                }
                case PATCH -> {
                    var tmp = webTestClient.patch()
                            .uri(this::buildUri);
                    if (body != null) {
                        tmp.bodyValue(body);
                    }
                    yield tmp;
                }
                case DELETE -> webTestClient.delete()
                        .uri(this::buildUri);
            };
            if (StringUtils.isNotBlank(token)) {
                return requestHeadersSpec.header(AUTHENTICATION_HEADER, String.format(BEARER_FORMAT, token))
                        .exchange();
            }
            return requestHeadersSpec.exchange();
        }

        private URI buildUri(UriBuilder uriBuilder) {
            UriBuilder builder = uriBuilder.path(path);
            if (MapUtils.isEmpty(queries)) {
                return builder.build();
            }
            for (Map.Entry<String, String> query : queries.entrySet()) {
                builder = builder.queryParam(query.getKey(), query.getValue());
            }
            return builder.build();
        }

        public enum Method {

            GET, POST, PUT, PATCH, DELETE
        }
    }
}
