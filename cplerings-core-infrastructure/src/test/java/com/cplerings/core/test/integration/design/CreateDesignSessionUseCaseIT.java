package com.cplerings.core.test.integration.design;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.cplerings.core.api.account.data.Profile;
import com.cplerings.core.api.account.response.ProfileResponse;
import com.cplerings.core.api.design.data.DesignSession;
import com.cplerings.core.api.design.response.CreateDesignSessionResponse;
import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.account.AccountTestConstant;
import com.cplerings.core.test.shared.helper.JWTTestHelper;

class CreateDesignSessionUseCaseIT extends AbstractIT {

    @Autowired
    private JWTTestHelper jwtTestHelper;

    @Test
    void givenStaff_whenCreateDesignSessionUseCase() {

        final String customerToken = jwtTestHelper.generateToken(AccountTestConstant.CUSTOMER_EMAIL);
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.DESIGN_SESSION_PATH)
                .authorizationHeader(customerToken)
                .method(RequestBuilder.Method.GET)
                .send();

        thenResponseIsOk(response);
        thenCreateSuccessfullyAndReturnSessionId(response);
    }

    private void thenCreateSuccessfullyAndReturnSessionId(WebTestClient.ResponseSpec response) {
        final CreateDesignSessionResponse responseBody = response.expectBody(CreateDesignSessionResponse.class)
                .returnResult()
                .getResponseBody();

        assertThat(responseBody)
                .isNotNull();
        assertThat(responseBody.getType())
                .isEqualTo(AbstractResponse.Type.DATA);
        assertThat(responseBody.getData())
                .isNotNull()
                .isExactlyInstanceOf(DesignSession.class);
        assertThat(responseBody.getData().sessionId()).isNotNull();
    }
}
