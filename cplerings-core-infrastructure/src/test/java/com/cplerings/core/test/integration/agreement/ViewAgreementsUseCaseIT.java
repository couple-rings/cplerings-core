package com.cplerings.core.test.integration.agreement;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.cplerings.core.api.agreement.data.AgreementsData;
import com.cplerings.core.api.agreement.request.ViewAgreementsRequest;
import com.cplerings.core.api.agreement.response.ViewAgreementsResponse;
import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.domain.spouse.Agreement;
import com.cplerings.core.infrastructure.repository.AccountRepository;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.datasource.TestDataSource;

public class ViewAgreementsUseCaseIT extends AbstractIT {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TestDataSource testDataSource;

    @Test
    void givenAnyone_whenViewMetalSpecifications() {
        Agreement agreement = Agreement.builder()
                .customer(accountRepository.getReferenceById(1L))
                .mainName("test")
                .build();
        testDataSource.save(agreement);
        ViewAgreementsRequest request = ViewAgreementsRequest.builder()
                .page(0)
                .pageSize(1)
                .build();
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.AGREEMENTS_PATH)
                .method(RequestBuilder.Method.GET)
                .query(request)
                .send();
        thenResponseIsOk(response);
        thenResponseReturnAgreements(response);
    }

    private void thenResponseReturnAgreements(WebTestClient.ResponseSpec response) {
        final ViewAgreementsResponse responseBody = response.expectBody(ViewAgreementsResponse.class)
                .returnResult()
                .getResponseBody();

        assertThat(responseBody)
                .isNotNull();
        assertThat(responseBody.getType())
                .isEqualTo(AbstractResponse.Type.PAGINATED_DATA);

        final AgreementsData agreementsData = responseBody.getData();
        assertThat(agreementsData).isNotNull();
        assertThat(agreementsData.getPage()).isZero();
        assertThat(agreementsData.getPageSize()).isEqualTo(1);
        assertThat(agreementsData.getItems()).hasSize(1);
        assertThat(agreementsData.getTotalPages()).isEqualTo(1);
        assertThat(agreementsData.getItems().stream().findFirst().get().getCustomer()).isNotNull();
        assertThat(agreementsData.getItems().stream().findFirst().get().getMainName()).isNotNull();
    }
}
