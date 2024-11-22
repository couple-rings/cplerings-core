package com.cplerings.core.test.integration.agreement;

import static org.assertj.core.api.Assertions.assertThat;

import com.cplerings.core.api.agreement.request.SignAgreementRequest;
import com.cplerings.core.api.agreement.response.SignAgreementResponse;
import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.application.agreement.error.SignAgreementErrorCode;
import com.cplerings.core.application.shared.entity.agreement.AAgreement;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.account.Role;
import com.cplerings.core.domain.file.Image;
import com.cplerings.core.domain.spouse.Agreement;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.account.AccountTestConstant;
import com.cplerings.core.test.shared.datasource.TestDataSource;
import com.cplerings.core.test.shared.helper.AccountTestHelper;
import com.cplerings.core.test.shared.helper.AgreementTestHelper;
import com.cplerings.core.test.shared.helper.FileTestHelper;
import com.cplerings.core.test.shared.helper.JWTTestHelper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

class SignAgreementUseCaseIT extends AbstractIT {

    @Autowired
    private JWTTestHelper jwtTestHelper;

    @Autowired
    private AgreementTestHelper agreementTestHelper;

    @Autowired
    private FileTestHelper fileTestHelper;

    @Autowired
    private TestDataSource testDataSource;

    @Autowired
    private AccountTestHelper accountTestHelper;

    @Test
    void givenCustomer_whenSignNewAgreement() {
        final Agreement agreement = agreementTestHelper.createUnsignedAgreement();
        final Image mainSignature = fileTestHelper.createImage();
        final Image partnerSignature = fileTestHelper.createImage();

        final String token = jwtTestHelper.generateToken(AccountTestConstant.CUSTOMER_EMAIL);
        final SignAgreementRequest request = SignAgreementRequest.builder()
                .mainName("John Doe")
                .mainSignatureId(mainSignature.getId())
                .partnerName("Jane Doe")
                .partnerSignatureId(partnerSignature.getId())
                .build();

        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.SIGN_AGREEMENT_PATH, agreement.getId())
                .authorizationHeader(token)
                .method(RequestBuilder.Method.PUT)
                .body(request)
                .send();

        thenResponseIsOk(response);
        thenAgreementIsReturned(response, agreement.getId());
        thenAgreementIsUpdatedInDatabase(agreement.getId());
    }

    @Test
    void givenCustomer_whenSignOldAgreement() {
        final Agreement oldAgreement = agreementTestHelper.createSignedAgreement();
        final Image mainSignature = fileTestHelper.createImage();
        final Image partnerSignature = fileTestHelper.createImage();

        final String token = jwtTestHelper.generateToken(AccountTestConstant.CUSTOMER_EMAIL);
        final SignAgreementRequest request = SignAgreementRequest.builder()
                .mainName("John Doe")
                .mainSignatureId(mainSignature.getId())
                .partnerName("Jane Doe")
                .partnerSignatureId(partnerSignature.getId())
                .build();

        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.SIGN_AGREEMENT_PATH, oldAgreement.getId())
                .authorizationHeader(token)
                .method(RequestBuilder.Method.PUT)
                .body(request)
                .send();

        thenResponseIsBadRequestWithErrorCode(response, SignAgreementErrorCode.AGREEMENT_ALREADY_SIGNED);
    }

    @Test
    void givenCustomer_whenSignOtherAgreement() {
        final Agreement agreement = agreementTestHelper.createUnsignedAgreement();
        final Image mainSignature = fileTestHelper.createImage();
        final Image partnerSignature = fileTestHelper.createImage();

        final Account otherCustomer = accountTestHelper.createAccount(Role.CUSTOMER);
        final String token = jwtTestHelper.generateToken(otherCustomer.getEmail());
        final SignAgreementRequest request = SignAgreementRequest.builder()
                .mainName("John Doe")
                .mainSignatureId(mainSignature.getId())
                .partnerName("Jane Doe")
                .partnerSignatureId(partnerSignature.getId())
                .build();

        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.SIGN_AGREEMENT_PATH, agreement.getId())
                .authorizationHeader(token)
                .method(RequestBuilder.Method.PUT)
                .body(request)
                .send();

        thenResponseIsBadRequestWithErrorCode(response, SignAgreementErrorCode.NOT_SAME_CUSTOMER_TO_SIGN);
    }

    private void thenAgreementIsReturned(WebTestClient.ResponseSpec response, Long agreementId) {
        final SignAgreementResponse responseBody = response.expectBody(SignAgreementResponse.class)
                .returnResult()
                .getResponseBody();

        assertThat(responseBody)
                .isNotNull();
        assertThat(responseBody.getType())
                .isEqualTo(AbstractResponse.Type.DATA);

        final AAgreement agreementData = responseBody.getData();
        assertThat(agreementData).isNotNull();
        assertThat(agreementData.getId()).isEqualTo(agreementId);
        assertThat(agreementData.getMainName()).isEqualTo("John Doe");
        assertThat(agreementData.getPartnerName()).isEqualTo("Jane Doe");
        assertThat(agreementData.getCustomer()).isNotNull();
        assertThat(agreementData.getMainSignature()).isNotNull();
        assertThat(agreementData.getPartnerSignature()).isNotNull();
    }

    private void thenAgreementIsUpdatedInDatabase(Long agreementId) {
        final Agreement updatedAgreement = testDataSource.findAgreementById(agreementId)
                .orElse(null);
        assertThat(updatedAgreement).isNotNull();
        assertThat(updatedAgreement.getCustomer()).isNotNull();
        assertThat(updatedAgreement.getMainName()).isEqualTo("John Doe");
        assertThat(updatedAgreement.getPartnerName()).isEqualTo("Jane Doe");
        assertThat(updatedAgreement.getMainSignature()).isNotNull();
        assertThat(updatedAgreement.getPartnerSignature()).isNotNull();
    }
}
