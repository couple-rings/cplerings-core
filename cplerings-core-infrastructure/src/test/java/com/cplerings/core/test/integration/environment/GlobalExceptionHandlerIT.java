package com.cplerings.core.test.integration.environment;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.api.shared.ErrorCodeResponse;
import com.cplerings.core.api.shared.ErrorCodesResponse;
import com.cplerings.core.api.shared.ExceptionResponse;
import com.cplerings.core.application.shared.errorcode.ErrorCode;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.entity.hello.HelloController;

class GlobalExceptionHandlerIT extends AbstractIT {

    @Test
    void givenAnyone_whenExceptionIsThrown() {
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(HelloController.TEST_EXCEPTION_PATH)
                .method(RequestBuilder.Method.GET)
                .send();

        thenResponseIsServerError(response);
        thenResponseBodyHasServerInternalError(response);
    }

    private void thenResponseBodyHasServerInternalError(WebTestClient.ResponseSpec response) {
        final ExceptionResponse responseBody = response.expectBody(ExceptionResponse.class)
                .returnResult()
                .getResponseBody();

        assertThat(responseBody)
                .isNotNull();
        assertThat(responseBody.getType())
                .isEqualTo(AbstractResponse.Type.EXCEPTION);
        assertThat(responseBody.getStackTrace())
                .isNotBlank()
                .contains("Hello Exception!");
    }

    @Test
    void givenAnyone_whenErrorCodeExceptionIsThrown() {
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(HelloController.TEST_ERROR_CODE_EXCEPTION)
                .method(RequestBuilder.Method.GET)
                .send();

        thenResponseIsServerError(response);
        thenResponseBodyHasServerErrorCode(response);
    }

    private void thenResponseBodyHasServerErrorCode(WebTestClient.ResponseSpec response) {
        final ErrorCodesResponse responseBody = response.expectBody(ErrorCodesResponse.class)
                .returnResult()
                .getResponseBody();

        assertThat(responseBody)
                .isNotNull();
        assertThat(responseBody.getType())
                .isEqualTo(AbstractResponse.Type.ERROR);
        assertThat(responseBody.getErrors()).hasSize(1);
        ErrorCodeResponse errorCodeResponse = responseBody.getErrors().stream()
                .findFirst()
                .orElse(null);
        assertThat(errorCodeResponse).isNotNull();
        assertThat(errorCodeResponse.getCode()).isEqualTo(ErrorCode.System.ERROR.getCode());
    }
}
