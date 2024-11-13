package com.cplerings.core.test.integration.design;

import static org.assertj.core.api.Assertions.assertThat;

import com.cplerings.core.api.design.data.DesignVersion;
import com.cplerings.core.api.design.request.CreateDesignVersionRequest;
import com.cplerings.core.api.design.response.CreateDesignVersionResponse;
import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.account.AccountTestConstant;
import com.cplerings.core.test.shared.helper.JWTTestHelper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

class CreateDesignVersionUseCaseIT extends AbstractIT {

    @Autowired
    private JWTTestHelper jwtTestHelper;

    @Test
    void givenStaff_whenCreateDesignVersionUseCase() {
        final String token = jwtTestHelper.generateToken(AccountTestConstant.STAFF_EMAIL);

        CreateDesignVersionRequest request = CreateDesignVersionRequest.builder()
                .customerId(1L)
                .designId(1L)
                .designFileId(1L)
                .previewImageId(11L)
                .build();
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.DESIGN_VERSION_PATH)
                .authorizationHeader(token)
                .method(RequestBuilder.Method.POST)
                .body(request)
                .send();

        thenResponseIsOk(response);
        thenCreateSuccessfullyANdReturnDesignVersionData(response);
    }

    private void thenCreateSuccessfullyANdReturnDesignVersionData(WebTestClient.ResponseSpec response) {
        final CreateDesignVersionResponse responseBody = response.expectBody(CreateDesignVersionResponse.class)
                .returnResult()
                .getResponseBody();

        assertThat(responseBody)
                .isNotNull();
        assertThat(responseBody.getType())
                .isEqualTo(AbstractResponse.Type.DATA);
        assertThat(responseBody.getData())
                .isNotNull()
                .isExactlyInstanceOf(DesignVersion.class);
        assertThat(responseBody.getData().designVersion()).isNotNull();
    }
}
