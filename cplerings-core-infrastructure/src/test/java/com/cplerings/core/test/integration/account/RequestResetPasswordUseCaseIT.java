package com.cplerings.core.test.integration.account;

import static com.cplerings.core.api.shared.AbstractResponse.Type.DATA;
import static org.assertj.core.api.Assertions.assertThat;

import com.cplerings.core.api.account.data.CustomerEmailInfo;
import com.cplerings.core.api.account.request.RequestResetPasswordRequest;
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

class RequestResetPasswordUseCaseIT extends AbstractIT {

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
    void givenAnyone_whenRequestResetPassword() throws Exception {
        final RequestResetPasswordRequest request = RequestResetPasswordRequest.builder()
                .email(AccountTestConstant.CUSTOMER_EMAIL)
                .build();
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.REQUEST_RESET_PASSWORD_PATH)
                .method(RequestBuilder.Method.POST)
                .body(request)
                .send();

        thenResponseIsOk(response);
        thenResponseContainsResetPasswordEmail(response);
        thenExistsEmailWithVerificationCode();
    }

    private void thenResponseContainsResetPasswordEmail(WebTestClient.ResponseSpec response) {
        final CustomerEmailInfoResponse customerEmailInfoResponse = response.expectBody(CustomerEmailInfoResponse.class)
                .returnResult()
                .getResponseBody();
        assertThat(customerEmailInfoResponse).isNotNull();
        assertThat(customerEmailInfoResponse.getType()).isEqualTo(DATA);

        final CustomerEmailInfo customerEmailInfo = customerEmailInfoResponse.getData();
        assertThat(customerEmailInfo).isNotNull();
        assertThat(customerEmailInfo.email()).isEqualTo(AccountTestConstant.CUSTOMER_EMAIL);
    }

    private void thenExistsEmailWithVerificationCode() throws Exception {
        MimeMessage[] emails = greenMail.getReceivedMessages();
        assertThat(emails).hasSize(1);

        final MimeMessage email = emails[0];
        assertThat(email).isNotNull();
        assertThat(email.getSubject()).isEqualTo(LocaleUtils.translateLocale("requestResetPassword.text.subject"),
                AccountTestConstant.CUSTOMER_EMAIL);

        final Object body = email.getContent();
        assertThat(body).isInstanceOf(String.class);
        final String verificationCode = (String) body;
        assertThat(verificationCode).hasSize(6)
                .containsOnlyDigits();
    }
}
