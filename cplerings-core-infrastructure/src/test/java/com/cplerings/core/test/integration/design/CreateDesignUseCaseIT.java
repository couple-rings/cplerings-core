package com.cplerings.core.test.integration.design;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.cplerings.core.api.design.request.CreateDesignRequest;
import com.cplerings.core.api.design.response.CreateDesignResponse;
import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.application.shared.entity.design.ADesign;
import com.cplerings.core.application.shared.entity.design.ADesignCharacteristic;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.domain.file.Document;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.account.AccountTestConstant;
import com.cplerings.core.test.shared.datasource.TestDataSource;
import com.cplerings.core.test.shared.helper.JWTTestHelper;

class CreateDesignUseCaseIT extends AbstractIT {

    @Autowired
    private JWTTestHelper jwtTestHelper;

    @Autowired
    private TestDataSource testDataSource;

    @Test
    void givenManager_whenCreateDesignUseCase() {
        final String customerToken = jwtTestHelper.generateToken(AccountTestConstant.MANAGER_EMAIL);
        Document document = Document.builder()
                .url("test")
                .build();
        Document blueprintCreated = testDataSource.save(document);
        CreateDesignRequest request = CreateDesignRequest.builder()
                .jewelryCategoryId(1L)
                .blueprintId(blueprintCreated.getId())
                .characteristic(ADesignCharacteristic.ANDROGYNOUS)
                .size(5)
                .metalWeight(BigDecimal.valueOf(5))
                .sideDiamond(2)
                .description("test")
                .collectionId(1L)
                .name("Test")
                .build();

        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.DESIGN_PATH)
                .authorizationHeader(customerToken)
                .method(RequestBuilder.Method.POST)
                .body(request)
                .send();

        thenResponseIsOk(response);
        thenCreateSuccessfullyAndReturnDesign(response);
    }

    private void thenCreateSuccessfullyAndReturnDesign(WebTestClient.ResponseSpec response) {
        final CreateDesignResponse responseBody = response.expectBody(CreateDesignResponse.class)
                .returnResult()
                .getResponseBody();

        assertThat(responseBody)
                .isNotNull();
        assertThat(responseBody.getType())
                .isEqualTo(AbstractResponse.Type.DATA);
        assertThat(responseBody.getData())
                .isNotNull()
                .isExactlyInstanceOf(ADesign.class);
        assertThat(responseBody.getData().getCharacteristic()).isNotNull();
    }
}
