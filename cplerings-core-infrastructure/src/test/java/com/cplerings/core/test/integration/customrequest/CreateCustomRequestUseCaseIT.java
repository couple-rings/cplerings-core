package com.cplerings.core.test.integration.customrequest;

import static org.assertj.core.api.Assertions.assertThat;

import com.cplerings.core.api.design.request.CreateCustomRequestRequest;
import com.cplerings.core.api.design.response.CreateCustomRequestResponse;
import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.application.shared.entity.design.request.ACustomRequest;
import com.cplerings.core.application.shared.entity.design.request.ACustomRequestStatus;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.design.DesignVersion;
import com.cplerings.core.infrastructure.repository.AccountRepository;
import com.cplerings.core.infrastructure.repository.DesignRepository;
import com.cplerings.core.infrastructure.repository.DesignVersionRepository;
import com.cplerings.core.infrastructure.repository.DocumentRepository;
import com.cplerings.core.infrastructure.repository.ImageRepository;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.account.AccountTestConstant;
import com.cplerings.core.test.shared.datasource.TestDataSource;
import com.cplerings.core.test.shared.helper.JWTTestHelper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Collection;
import java.util.List;
import java.util.Set;

class CreateCustomRequestUseCaseIT extends AbstractIT {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private DesignRepository designRepository;

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private DesignVersionRepository designVersionRepository;

    @Autowired
    private TestDataSource testDataSource;

    @Autowired
    private JWTTestHelper jwtTestHelper;

    @Test
    void givenStaff_whenCreateCustomRequest() {
        final Account customer = accountRepository.findByEmail(AccountTestConstant.CUSTOMER_EMAIL)
                .orElse(null);
        assertThat(customer).isNotNull();

        DesignVersion firstOldDesignVersion = DesignVersion.builder()
                .customer(customer)
                .design(designRepository.getReferenceById(1L))
                .isOld(false)
                .designFile(documentRepository.getReferenceById(1L))
                .image(imageRepository.getReferenceById(1L))
                .isAccepted(false)
                .versionNumber(1)
                .build();
        firstOldDesignVersion = testDataSource.save(firstOldDesignVersion);

        DesignVersion secondOldDesignVersion = DesignVersion.builder()
                .customer(customer)
                .design(designRepository.getReferenceById(11L))
                .isOld(false)
                .designFile(documentRepository.getReferenceById(1L))
                .image(imageRepository.getReferenceById(1L))
                .isAccepted(false)
                .versionNumber(2)
                .build();
        secondOldDesignVersion = testDataSource.save(secondOldDesignVersion);

        final CreateCustomRequestRequest request = CreateCustomRequestRequest.builder()
                .customerId(customer.getId())
                .designIds(Set.of(1L, 11L))
                .build();

        final String token = jwtTestHelper.generateToken(AccountTestConstant.STAFF_EMAIL);
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.CUSTOM_REQUEST_PATH)
                .method(RequestBuilder.Method.POST)
                .authorizationHeader(token)
                .body(request)
                .send();

        thenResponseIsOk(response);
        thenResponseBodyContainsNewlyCreatedCustomRequest(response);
        thenPreviousDesignVersionsAreUpdatedAsOld(List.of(firstOldDesignVersion.getId(), secondOldDesignVersion.getId()));
    }

    @Test
    void givenCustomer_whenCreateCustomRequest() {
        final CreateCustomRequestRequest request = CreateCustomRequestRequest.builder()
                .designIds(Set.of(1L, 11L))
                .build();

        final String token = jwtTestHelper.generateToken(AccountTestConstant.CUSTOMER_EMAIL);
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.CUSTOM_REQUEST_PATH)
                .method(RequestBuilder.Method.POST)
                .authorizationHeader(token)
                .body(request)
                .send();

        thenResponseIsOk(response);
        thenResponseBodyContainsNewlyCreatedCustomRequest(response);
    }

    private void thenResponseBodyContainsNewlyCreatedCustomRequest(WebTestClient.ResponseSpec response) {
        final CreateCustomRequestResponse responseBody = response.expectBody(CreateCustomRequestResponse.class)
                .returnResult()
                .getResponseBody();

        assertThat(responseBody).isNotNull();
        assertThat(responseBody.getType()).isEqualTo(AbstractResponse.Type.DATA);

        final ACustomRequest data = responseBody.getData();
        assertThat(data).isNotNull();
        assertThat(data.getId()).isNotNull();
        assertThat(data.getCustomer()).isNotNull();
        assertThat(data.getStatus()).isEqualTo(ACustomRequestStatus.PENDING);
        assertThat(data.getDesigns()).hasSize(2);
    }

    private void thenPreviousDesignVersionsAreUpdatedAsOld(Collection<Long> designVersionIds) {
        Collection<DesignVersion> designVersions = designVersionRepository.findAllById(designVersionIds);
        assertThat(designVersions).hasSize(designVersionIds.size());
        designVersions.forEach(designVersion -> assertThat(designVersion.getIsOld()).isTrue());
    }
}
