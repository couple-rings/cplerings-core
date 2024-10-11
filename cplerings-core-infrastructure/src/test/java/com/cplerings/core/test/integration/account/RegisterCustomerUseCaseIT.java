package com.cplerings.core.test.integration.account;

import static com.cplerings.core.api.shared.AbstractResponse.Type.DATA;
import static org.assertj.core.api.Assertions.assertThat;

import com.cplerings.core.api.account.data.CustomerEmailInfo;
import com.cplerings.core.api.account.request.RegisterCustomerRequest;
import com.cplerings.core.api.account.response.CustomerEmailInfoResponse;
import com.cplerings.core.application.shared.service.email.EmailService;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.common.locale.LocaleUtils;
import com.cplerings.core.infrastructure.service.email.EmailServiceImpl;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.account.AccountTestConstant;
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

import java.util.UUID;

class RegisterCustomerUseCaseIT extends AbstractIT {

    @Autowired
    private EmailHelper emailHelper;

    @Autowired
    private EmailService emailService;

    private GreenMail greenMail;

    @BeforeEach
    public void startEmailService() {
        final Pair<GreenMail, JavaMailSender> mailPair = emailHelper.startServer();
        this.greenMail = mailPair.getLeft();
        if (emailService instanceof EmailServiceImpl emailServiceImpl) {
            emailServiceImpl.setJavaMailSender(mailPair.getRight());
        }
    }

    @AfterEach
    public void stopEmailService() {
        this.greenMail.stop();
    }

    @Test
    void givenRegisterCustomer_whenRegisterNewEmailAndUsername() throws Exception {
        final String username = UUID.randomUUID().toString();
        final RegisterCustomerRequest request = RegisterCustomerRequest.builder()
                .email(EmailHelper.TEST_EMAIL)
                .password(AccountTestConstant.PASSWORD)
                .username(username)
                .build();
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.REGISTER_CUSTOMER_PATH)
                .method(RequestBuilder.Method.POST)
                .body(request)
                .send();

        thenResponseIsOk(response);
        thenResponseContainsRegistrationEmail(response);
        thenExistsEmailWithVerificationCode();
    }

    private void thenResponseContainsRegistrationEmail(WebTestClient.ResponseSpec response) {
        final CustomerEmailInfoResponse customerRegistrationResponse = response.expectBody(CustomerEmailInfoResponse.class)
                .returnResult()
                .getResponseBody();
        assertThat(customerRegistrationResponse).isNotNull();
        assertThat(customerRegistrationResponse.getType()).isEqualTo(DATA);

        final CustomerEmailInfo customerRegistration = customerRegistrationResponse.getData();
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

        final Object body = email.getContent();
        assertThat(body).isInstanceOf(String.class);
        final String verificationCode = (String) body;
        assertThat(verificationCode).hasSize(6)
                .containsOnlyDigits();
    }
}
