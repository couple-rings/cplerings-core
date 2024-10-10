package com.cplerings.core.test.integration.resendverification;

import static com.cplerings.core.api.shared.AbstractResponse.Type.DATA;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.cplerings.core.api.verification.data.ResendVerification;
import com.cplerings.core.api.verification.request.ResendVerificationRequest;
import com.cplerings.core.api.verification.response.ResendVerificationResponse;
import com.cplerings.core.application.shared.service.email.EmailService;
import com.cplerings.core.common.locale.LocaleUtils;
import com.cplerings.core.domain.State;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.account.AccountStatus;
import com.cplerings.core.domain.account.Role;
import com.cplerings.core.infrastructure.repository.AccountRepository;
import com.cplerings.core.infrastructure.service.email.EmailServiceImpl;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.helper.EmailHelper;
import com.icegreen.greenmail.util.GreenMail;

import jakarta.mail.internet.MimeMessage;

class ResendVerificationIT extends AbstractIT {

    private final String TEST_EMAIL = "test2@test.com";
    private static final String RESEND_VERIFICATION_PATH = "/accounts/customer/verification";

    @Autowired
    private EmailHelper emailHelper;

    @Autowired
    private EmailService emailService;

    @Autowired
    private AccountRepository accountRepository;

    private GreenMail greenMail;

    @BeforeEach
    public void startEmailService() {
        final Pair<GreenMail, JavaMailSender> mailPair = emailHelper.startServer();
        this.greenMail = mailPair.getLeft();
        if (emailService instanceof EmailServiceImpl emailServiceImpl) {
            emailServiceImpl.setJavaMailSender(mailPair.getRight());
        }

        Account account = Account.builder()
                .email(TEST_EMAIL)
                .status(AccountStatus.VERIFYING)
                .createdBy("test")
                .modifiedBy("test")
                .password("test")
                .phone("test")
                .state(State.ACTIVE)
                .username("test")
                .role(Role.CUSTOMER)
                .build();
        accountRepository.save(account);
    }

    @AfterEach
    public void stopEmailService() {
        this.greenMail.stop();
    }

    @Test
    void givenCustomer_whenResendVerificationCode() throws Exception {
        final ResendVerificationRequest request = ResendVerificationRequest.builder()
                .email(TEST_EMAIL)
                .build();

        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(RESEND_VERIFICATION_PATH)
                .method(RequestBuilder.Method.POST)
                .body(request)
                .send();

        // Assert
        thenResponseIsOk(response);
        thenResponseContainsRegistrationEmail(response);
        thenExistsEmailWithVerificationCode();
    }

    private void thenResponseContainsRegistrationEmail(WebTestClient.ResponseSpec response) {
        final ResendVerificationResponse resendVerificationResponse = response.expectBody(ResendVerificationResponse.class)
                .returnResult()
                .getResponseBody();
        assertThat(resendVerificationResponse).isNotNull();
        assertThat(resendVerificationResponse.getType()).isEqualTo(DATA);

        final ResendVerification customerRegistration = resendVerificationResponse.getData();
        assertThat(customerRegistration).isNotNull();
        assertThat(customerRegistration.email()).isEqualTo(TEST_EMAIL);
    }

    private void thenExistsEmailWithVerificationCode() throws Exception {
        MimeMessage[] emails = greenMail.getReceivedMessages();
        assertThat(emails).hasSize(1);

        final MimeMessage email = emails[0];
        assertThat(email).isNotNull();
        assertThat(email.getSubject()).isEqualTo(LocaleUtils.translateLocale("accountVerificationService.text.subject"),
                TEST_EMAIL);

        final Object body = email.getContent();
        assertThat(body).isInstanceOf(String.class);
        final String verificationCode = (String) body;
        assertThat(verificationCode).hasSize(6)
                .containsOnlyDigits();
    }
}
