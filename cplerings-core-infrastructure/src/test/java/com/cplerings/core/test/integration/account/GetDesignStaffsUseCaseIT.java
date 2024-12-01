package com.cplerings.core.test.integration.account;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.cplerings.core.api.account.data.DesignStaffsData;
import com.cplerings.core.api.account.request.GetDesignStaffsRequest;
import com.cplerings.core.api.account.response.GetDesignStaffsResponse;
import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.account.AccountStatus;
import com.cplerings.core.domain.account.Role;
import com.cplerings.core.domain.account.StaffPosition;
import com.cplerings.core.infrastructure.repository.BranchRepository;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.account.AccountTestConstant;
import com.cplerings.core.test.shared.datasource.TestDataSource;
import com.cplerings.core.test.shared.helper.JWTTestHelper;

class GetDesignStaffsUseCaseIT extends AbstractIT {

    @Autowired
    private JWTTestHelper jwtTestHelper;

    @Autowired
    private TestDataSource testDataSource;

    @Autowired
    private BranchRepository branchRepository;

    @Test
    void givenManager_whenViewDesignStaffs() {
        final String token = jwtTestHelper.generateToken(AccountTestConstant.MANAGER_EMAIL);

        Account staff = Account.builder()
                .email("test@mail")
                .password("password")
                .username("test")
                .role(Role.STAFF)
                .status(AccountStatus.ACTIVE)
                .staffPosition(StaffPosition.DESIGNER)
                .branch(branchRepository.getReferenceById(1L))
                .build();
        testDataSource.save(staff);
        GetDesignStaffsRequest request = GetDesignStaffsRequest.builder()
                .page(0)
                .pageSize(1)
                .branchId(1L)
                .build();
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.GET_DESIGN_STAFFS_PATH)
                .authorizationHeader(token)
                .method(AbstractIT.RequestBuilder.Method.GET)
                .query(request)
                .send();

        thenResponseIsOk(response);
        thenReturnDesignStaffs(response);
    }

    private void thenReturnDesignStaffs(WebTestClient.ResponseSpec response) {
        final GetDesignStaffsResponse responseBody = response.expectBody(GetDesignStaffsResponse.class)
                .returnResult()
                .getResponseBody();

        assertThat(responseBody)
                .isNotNull();
        assertThat(responseBody.getType())
                .isEqualTo(AbstractResponse.Type.PAGINATED_DATA);

        final DesignStaffsData designStaffsData = responseBody.getData();
        assertThat(designStaffsData).isNotNull();
        assertThat(designStaffsData.getPage()).isZero();
        assertThat(designStaffsData.getPageSize()).isEqualTo(1);
        assertThat(designStaffsData.getItems()).hasSize(1);
        assertThat(designStaffsData.getItems()).hasSize(1)
                .allSatisfy(x -> {
                    assertThat(x.getNumberOfHandledCustomRequest()).isNotNull();
                });
    }
}
