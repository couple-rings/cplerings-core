package com.cplerings.core.test.integration.branch;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.cplerings.core.api.branch.data.BranchesData;
import com.cplerings.core.api.branch.request.ViewBranchesRequest;
import com.cplerings.core.api.branch.response.ViewBranchesResponse;
import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.helper.BranchTestHelper;

public class ViewBranchesUseCaseIT extends AbstractIT {

    @Autowired
    private BranchTestHelper branchTestHelper;

    @Test
    void givenAnyone_whenViewBranches() {
        branchTestHelper.createBranch();
        ViewBranchesRequest request = ViewBranchesRequest.builder()
                .page(0)
                .pageSize(1)
                .build();
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.BRANCHES_PATH)
                .method(RequestBuilder.Method.GET)
                .query(request)
                .send();
        thenResponseIsOk(response);
        thenResponseReturnBranches(response);
    }

    private void thenResponseReturnBranches(WebTestClient.ResponseSpec response) {
        final ViewBranchesResponse responseBody = response.expectBody(ViewBranchesResponse.class)
                .returnResult()
                .getResponseBody();

        assertThat(responseBody)
                .isNotNull();
        assertThat(responseBody.getType())
                .isEqualTo(AbstractResponse.Type.PAGINATED_DATA);

        final BranchesData branchesData = responseBody.getData();
        assertThat(branchesData).isNotNull();
        assertThat(branchesData.getPage()).isZero();
        assertThat(branchesData.getPageSize()).isEqualTo(1);
        assertThat(branchesData.getItems()).hasSize(1);
    }
}
