package com.cplerings.core.test.integration;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.blazebit.persistence.CriteriaBuilderFactory;
import com.blazebit.persistence.querydsl.BlazeJPAQuery;
import com.cplerings.core.common.profile.ProfileConstant;
import com.cplerings.core.infrastructure.CplringsCoreApplication;
import com.cplerings.core.test.integration.helper.AccountTestConstant;
import com.cplerings.core.test.integration.helper.JWTTestHelper;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@SpringBootTest(
        classes = {
                CplringsCoreApplication.class,
                IntegrationTestConfiguration.class,
                AccountTestConstant.class,
                JWTTestHelper.class
        },
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
)
@ActiveProfiles(ProfileConstant.TEST)
public abstract class AbstractIT {

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

    protected final class RequestBuilder<B> {

        public enum Method {

            GET, POST, PUT, PATCH, DELETE
        }

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
    }

    protected <B> RequestBuilder<B> requestBuilder() {
        return new RequestBuilder<>();
    }

    @BeforeEach
    protected void beforeEachTestMethod() {
        flyway.clean();
        flyway.migrate();
    }

    protected <T> BlazeJPAQuery<T> createQuery() {
        return new BlazeJPAQuery<>(em, cbf);
    }
}
