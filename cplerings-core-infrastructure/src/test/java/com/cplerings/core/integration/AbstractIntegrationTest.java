package com.cplerings.core.integration;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.cplerings.core.common.profile.ProfileConstant;
import com.cplerings.core.infrastructure.CplringsCoreApplication;
import com.cplerings.core.integration.helper.AccountTestHelper;

@SpringBootTest(
        classes = {
                CplringsCoreApplication.class,
                IntegrationTestConfiguration.class,
                AccountTestHelper.class,
        },
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
)
@ActiveProfiles(ProfileConstant.TEST)
public abstract class AbstractIntegrationTest {

    private static final String BASE_URL = "http://localhost:%d/%s";

    @Value("${server.port}")
    private int port;

    @Value("${cplerings.api.path}")
    private String apiPath;

    protected final class RequestBuilder<B> {

        public enum Method {

            GET, POST, PUT, PATCH, DELETE
        }

        private String path = "";
        private Method method;
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

        public RequestBuilder<B> body(B body) {
            this.body = body;
            return this;
        }

        public WebTestClient.ResponseSpec send() {
            Objects.requireNonNull(path);
            final WebTestClient webTestClient = WebTestClient.bindToServer()
                    .baseUrl(String.format(BASE_URL, port, apiPath))
                    .build();
            return switch (method) {
                case GET -> webTestClient.get()
                        .uri(path)
                        .exchange();
                case POST -> webTestClient.post()
                        .uri(path)
                        .bodyValue(body)
                        .exchange();
                case PUT -> webTestClient.put()
                        .uri(path)
                        .bodyValue(body)
                        .exchange();
                case PATCH -> webTestClient.patch()
                        .uri(path)
                        .bodyValue(body)
                        .exchange();
                case DELETE -> webTestClient.delete()
                        .uri(path)
                        .exchange();
            };
        }
    }

    protected <B> RequestBuilder<B> requestBuilder() {
        return new RequestBuilder<>();
    }
}
