package com.cplerings.core.test.integration.environment;

import static org.assertj.core.api.Assertions.assertThat;

import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.api.shared.ErrorCodeResponse;
import com.cplerings.core.api.shared.ErrorCodesResponse;
import com.cplerings.core.application.shared.errorcode.ErrorCode;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.entity.hello.HelloController;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.reactive.server.WebTestClient;

class GlobalExceptionHandlerIT extends AbstractIT {

    @Test
    void givenAnyone_whenExceptionIsThrown() {
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(HelloController.TEST_EXCEPTION_PATH)
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
