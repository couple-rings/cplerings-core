package com.cplerings.core.test.integration;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.cplerings.core.common.profile.ProfileConstant;
import com.cplerings.core.infrastructure.CplringsCoreApplication;
import com.cplerings.core.test.integration.helper.AccountTestHelper;
import com.cplerings.core.test.integration.helper.JWTTestHelper;

@SpringBootTest(
        classes = {
                CplringsCoreApplication.class,
                IntegrationTestConfiguration.class,
                AccountTestHelper.class,
                JWTTestHelper.class
        },
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
)
@ActiveProfiles(ProfileConstant.TEST)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public abstract class AbstractIT {

    private static final String BASE_URL = "http://localhost:%d/%s";
    private static final String AUTHENTICATION_HEADER = "Authorization";
    private static final String BEARER_FORMAT = "Bearer %s";

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
}
