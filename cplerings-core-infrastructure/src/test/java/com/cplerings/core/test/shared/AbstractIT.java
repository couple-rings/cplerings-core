package com.cplerings.core.test.shared;

import static org.assertj.core.api.Assertions.assertThat;

import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.api.shared.NoResponse;
import com.cplerings.core.common.profile.ProfileConstant;
import com.cplerings.core.infrastructure.CplringsCoreApplication;

import org.apache.commons.lang3.StringUtils;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.blazebit.persistence.CriteriaBuilderFactory;
import com.blazebit.persistence.querydsl.BlazeJPAQuery;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.time.Duration;
import java.util.Objects;

@SpringBootTest(
        classes = {
                CplringsCoreApplication.class
        },
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
)
@Import(ITConfiguration.class)
@ActiveProfiles(ProfileConstant.TEST)
public abstract class AbstractIT {

    private static final Duration RESPONSE_TIMEOUT = Duration.ofSeconds(30);
    private static final String BASE_URL = "http://localhost:%d/%s";
    private static final String AUTHENTICATION_HEADER = "Authorization";
    private static final String BEARER_FORMAT = "Bearer %s";

    @Value("${server.port}")
    private int port;

    @Value("${cplerings.api.path}")
    private String apiPath;

    @Autowired
    private Flyway flyway;

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private CriteriaBuilderFactory cbf;

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

    protected final void thenResponseIsOk(WebTestClient.ResponseSpec response) {
        response.expectStatus().isOk();
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

        public WebTestClient.ResponseSpec send() {
            Objects.requireNonNull(path);
            final WebTestClient webTestClient = WebTestClient.bindToServer()
                    .baseUrl(String.format(BASE_URL, port, apiPath))
                    .responseTimeout(RESPONSE_TIMEOUT)
                    .build();
            final WebTestClient.RequestHeadersSpec<?> requestHeadersSpec = switch (method) {
                case GET -> webTestClient.get()
                        .uri(path);
                case POST -> webTestClient.post()
                        .uri(path)
                        .bodyValue(body);
                case PUT -> webTestClient.put()
                        .uri(path)
                        .bodyValue(body);
                case PATCH -> webTestClient.patch()
                        .uri(path)
                        .bodyValue(body);
                case DELETE -> webTestClient.delete()
                        .uri(path);
            };
            if (StringUtils.isNotBlank(token)) {
                return requestHeadersSpec.header(AUTHENTICATION_HEADER, String.format(BEARER_FORMAT, token))
                        .exchange();
            }
            return requestHeadersSpec.exchange();
        }

        public enum Method {

            GET, POST, PUT, PATCH, DELETE
        }
    }
}
