package com.cplerings.core.test.integration.account;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.cplerings.core.api.account.data.JewelersData;
import com.cplerings.core.api.account.data.TransportersData;
import com.cplerings.core.api.account.request.ViewTransportersRequest;
import com.cplerings.core.api.account.response.ViewJewelersResponse;
import com.cplerings.core.api.account.response.ViewTransportersResponse;
import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.branch.Branch;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.account.AccountTestConstant;
import com.cplerings.core.test.shared.datasource.TestDataSource;
import com.cplerings.core.test.shared.helper.JWTTestHelper;

public class ViewJewelersUseCase extends AbstractIT{

    @Autowired
    private JWTTestHelper jwtTestHelper;

    @Autowired
    private TestDataSource testDataSource;

    @Test
    void givenStaff_whenViewJewelers() {
        final String token = jwtTestHelper.generateToken(AccountTestConstant.STAFF_EMAIL);

        Branch branch = Branch.builder()
                .address("123 Hello")
                .phone("1234567890")
                .storeName("Hello")
                .build();
        Branch branchCreated = testDataSource.save(branch);
        Account jeweler = testDataSource.getTransporterWithBranch(41L);
        jeweler.setBranch(branchCreated);
        testDataSource.save(jeweler);
        ViewTransportersRequest request = ViewTransportersRequest.builder()
                .page(0)
                .pageSize(1)
                .branchId(1L)
                .build();
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.JEWELERS_PATH)
                .authorizationHeader(token)
                .method(AbstractIT.RequestBuilder.Method.GET)
                .query(request)
                .send();

        thenResponseIsOk(response);
        thenReturnJewelers(response);
    }

    private void thenReturnJewelers(WebTestClient.ResponseSpec response) {
        final ViewJewelersResponse responseBody = response.expectBody(ViewJewelersResponse.class)
                .returnResult()
                .getResponseBody();

        assertThat(responseBody)
                .isNotNull();
        assertThat(responseBody.getType())
                .isEqualTo(AbstractResponse.Type.PAGINATED_DATA);

        final JewelersData jewelersData = responseBody.getData();
        assertThat(jewelersData).isNotNull();
        assertThat(jewelersData.getPage()).isZero();
        assertThat(jewelersData.getPageSize()).isEqualTo(1);
        assertThat(jewelersData.getItems()).hasSize(1);
    }
}
