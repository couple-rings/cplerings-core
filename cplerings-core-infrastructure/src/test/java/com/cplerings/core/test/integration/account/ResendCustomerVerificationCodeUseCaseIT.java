package com.cplerings.core.test.integration.account;

import static com.cplerings.core.api.shared.AbstractResponse.Type.DATA;
import static org.assertj.core.api.Assertions.assertThat;

import com.cplerings.core.api.account.data.CustomerEmailInfo;
import com.cplerings.core.api.account.request.ResendCustomerVerificationCodeRequest;
import com.cplerings.core.api.account.response.CustomerEmailInfoResponse;
import com.cplerings.core.application.shared.service.email.EmailService;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.common.locale.LocaleUtils;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.account.AccountStatus;
import com.cplerings.core.domain.account.AccountVerification;
import com.cplerings.core.domain.account.Role;
import com.cplerings.core.domain.account.VerificationCodeStatus;
import com.cplerings.core.domain.shared.State;
import com.cplerings.core.infrastructure.repository.AccountRepository;
import com.cplerings.core.infrastructure.repository.AccountVerificationRepository;
import com.cplerings.core.infrastructure.service.email.EmailServiceImpl;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.datasource.TestDataSource;
import com.cplerings.core.test.shared.helper.EmailHelper;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.icegreen.greenmail.util.GreenMail;

import jakarta.mail.internet.MimeMessage;

class ResendCustomerVerificationCodeUseCaseIT extends AbstractIT {

    @Autowired
    private EmailHelper emailHelper;

    @Autowired
    private EmailService emailService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountVerificationRepository accountVerificationRepository;

    @Autowired
    private TestDataSource testDataSource;

    private GreenMail greenMail;

    @BeforeEach
    public void startEmailService() {
        final Pair<GreenMail, JavaMailSender> mailPair = emailHelper.startServer();
        this.greenMail = mailPair.getLeft();
        if (emailService instanceof EmailServiceImpl emailServiceImpl) {
            emailServiceImpl.setJavaMailSender(mailPair.getRight());
        }

        Account account = Account.builder()
                .email(EmailHelper.TEST_EMAIL)
                .status(AccountStatus.VERIFYING)
                .password("test")
                .phone("test")
                .state(State.ACTIVE)
                .username("test")
                .role(Role.CUSTOMER)
                .build();
        testDataSource.save(account);
    }

    @AfterEach
    public void stopEmailService() {
        this.greenMail.stop();
    }

    @Test
    void givenCustomer_whenResendVerificationCode() throws Exception {
        final ResendCustomerVerificationCodeRequest request = ResendCustomerVerificationCodeRequest.builder()
                .email(EmailHelper.TEST_EMAIL)
                .build();

        final Account testAccount = accountRepository.findByEmail(EmailHelper.TEST_EMAIL).orElse(null);
        assertThat(testAccount).isNotNull();
        AccountVerification oldAccountVerification = AccountVerification.builder()
                .code("123456")
                .status(VerificationCodeStatus.PENDING)
                .account(testAccount)
                .build();
        oldAccountVerification = testDataSource.save(oldAccountVerification);

        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.RESEND_CUSTOMER_VERIFICATION_CODE_PATH)
                .method(RequestBuilder.Method.POST)
                .body(request)
                .send();

        // Assert
        thenResponseIsOk(response);
        thenResponseContainsRegistrationEmail(response);
        thenExistsEmailWithVerificationCode();
        thenOldVerificationsAreDisabled(oldAccountVerification.getId());
    }

    private void thenResponseContainsRegistrationEmail(WebTestClient.ResponseSpec response) {
        final CustomerEmailInfoResponse resendVerificationResponse = response.expectBody(CustomerEmailInfoResponse.class)
                .returnResult()
                .getResponseBody();
        assertThat(resendVerificationResponse).isNotNull();
        assertThat(resendVerificationResponse.getType()).isEqualTo(DATA);

        final CustomerEmailInfo customerRegistration = resendVerificationResponse.getData();
        assertThat(customerRegistration).isNotNull();
        assertThat(customerRegistration.email()).isEqualTo(EmailHelper.TEST_EMAIL);
    }

    private void thenExistsEmailWithVerificationCode() throws Exception {
        MimeMessage[] emails = greenMail.getReceivedMessages();
        assertThat(emails).hasSize(1);

        final MimeMessage email = emails[0];
        assertThat(email).isNotNull();
        assertThat(email.getSubject()).isEqualTo(LocaleUtils.translateLocale("accountVerificationService.text.subject"),
                EmailHelper.TEST_EMAIL);

        final AccountVerification accountVerification = accountVerificationRepository.findFirstByAccountEmailOrderByIdDesc(EmailHelper.TEST_EMAIL)
                .orElse(null);
        assertThat(accountVerification).isNotNull();
        assertThat(accountVerification.getStatus()).isEqualTo(VerificationCodeStatus.PENDING);
        assertThat(accountVerification.getState()).isEqualTo(State.ACTIVE);

        final Object body = email.getContent();
        assertThat(body).isInstanceOf(String.class);
        final String verificationCode = (String) body;
        assertThat(verificationCode).contains(accountVerification.getCode());
    }

    private void thenOldVerificationsAreDisabled(Long accountVerificationId) {
        final AccountVerification accountVerification = accountVerificationRepository.findById(accountVerificationId).orElse(null);
        assertThat(accountVerification).isNotNull();
        assertThat(accountVerification.getState()).isEqualTo(State.INACTIVE);
    }
}
