package com.cplerings.core.test.integration.design;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.cplerings.core.api.design.data.CustomDesignData;
import com.cplerings.core.api.design.response.ViewCustomDesignResponse;
import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.domain.design.CustomDesign;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.account.AccountTestConstant;
import com.cplerings.core.test.shared.datasource.TestDataSource;
import com.cplerings.core.test.shared.design.CustomDesignTestHelper;
import com.cplerings.core.test.shared.helper.JWTTestHelper;

public class ViewCustomDesignUseCaseIT extends AbstractIT {

    @Autowired
    private JWTTestHelper jwtTestHelper;

    @Autowired
    private CustomDesignTestHelper customDesignTestHelper;

    @Autowired
    private TestDataSource testDataSource;

    @Test
    void givenAnyone_whenViewCustomDesigns() {
        CustomDesign customDesign = customDesignTestHelper.createCustomDesign();
        testDataSource.save(customDesign);
        String token = jwtTestHelper.generateToken(AccountTestConstant.CUSTOMER_EMAIL);
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.VIEW_CUSTOM_DESIGN_PATH.replace("{customDesignId}", Long.toString(1L)))
                .method(AbstractIT.RequestBuilder.Method.GET)
                .authorizationHeader(token)
                .send();
        thenResponseIsOk(response);
        thenResponseReturnCustomDesign(response);

    }

    private void thenResponseReturnCustomDesign(WebTestClient.ResponseSpec response) {
        final ViewCustomDesignResponse responseBody = response.expectBody(ViewCustomDesignResponse.class)
                .returnResult()
                .getResponseBody();

        assertThat(responseBody)
                .isNotNull();
        assertThat(responseBody.getType())
                .isEqualTo(AbstractResponse.Type.DATA);

        final CustomDesignData customDesignData = responseBody.getData();
        assertThat(customDesignData).isNotNull();
    }
}
