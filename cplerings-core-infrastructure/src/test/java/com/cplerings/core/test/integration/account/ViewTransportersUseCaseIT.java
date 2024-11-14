package com.cplerings.core.test.integration.account;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.cplerings.core.api.account.data.TransportersData;
import com.cplerings.core.api.account.request.ViewTransportersRequest;
import com.cplerings.core.api.account.response.ViewTransportersResponse;
import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.branch.Branch;
import com.cplerings.core.infrastructure.repository.AccountRepository;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.account.AccountTestConstant;
import com.cplerings.core.test.shared.datasource.TestDataSource;
import com.cplerings.core.test.shared.helper.JWTTestHelper;

class ViewTransportersUseCaseIT extends AbstractIT {

    @Autowired
    private JWTTestHelper jwtTestHelper;

    @Autowired
    private TestDataSource testDataSource;

    @Test
    void givenStaff_whenViewTransporters() {
        final String token = jwtTestHelper.generateToken(AccountTestConstant.STAFF_EMAIL);

        Branch branch = Branch.builder()
                .address("123 Hello")
                .phone("1234567890")
                .storeName("Hello")
                .build();
        Branch branchCreated = testDataSource.save(branch);
        Account transporter = testDataSource.getTransporterWithBranch(51L);
        transporter.setBranch(branchCreated);
        testDataSource.save(transporter);
        ViewTransportersRequest request = ViewTransportersRequest.builder()
                .page(0)
                .pageSize(1)
                .branchId(1L)
                .build();
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.TRANSPORTERS_PATH)
                .authorizationHeader(token)
                .method(RequestBuilder.Method.GET)
                .query(request)
                .send();

        thenResponseIsOk(response);
        thenReturnTransporters(response);
    }

    private void thenReturnTransporters(WebTestClient.ResponseSpec response) {
        final ViewTransportersResponse responseBody = response.expectBody(ViewTransportersResponse.class)
                .returnResult()
                .getResponseBody();

        assertThat(responseBody)
                .isNotNull();
        assertThat(responseBody.getType())
                .isEqualTo(AbstractResponse.Type.PAGINATED_DATA);

        final TransportersData transportersData = responseBody.getData();
        assertThat(transportersData).isNotNull();
        assertThat(transportersData.getPage()).isZero();
        assertThat(transportersData.getPageSize()).isEqualTo(1);
        assertThat(transportersData.getItems()).hasSize(1);
    }
}
