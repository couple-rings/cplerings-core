package com.cplerings.core.test.component.email;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

import com.cplerings.core.application.shared.service.email.EmailInfo;
import com.cplerings.core.application.shared.service.email.EmailService;
import com.cplerings.core.infrastructure.service.email.EmailServiceImpl;
import com.cplerings.core.test.shared.AbstractCT;
import com.cplerings.core.test.shared.account.AccountTestConstant;
import com.cplerings.core.test.shared.helper.EmailHelper;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;

import com.icegreen.greenmail.util.GreenMail;

import jakarta.mail.internet.MimeMessage;

class EmailServiceCT extends AbstractCT {

    private static final String TEST_BODY = "Hello, World!";

    @Autowired
    private EmailHelper emailHelper;

    @Autowired
    private EmailService emailService;

    private GreenMail greenMail;

    @BeforeEach
    public void startGreenMail() {
        final Pair<GreenMail, JavaMailSender> mailPair = emailHelper.startServer();
        this.greenMail = mailPair.getLeft();
        if (emailService instanceof EmailServiceImpl emailServiceImpl) {
            emailServiceImpl.setJavaMailSender(mailPair.getRight());
        }
    }

    @AfterEach
    public void stopGreenMail() {
        greenMail.stop();
    }

    @Test
    void givenSystem_whenSendingEmail() throws Exception {
        final EmailInfo emailInfo = EmailInfo.builder()
                .recipient(EmailHelper.TEST_EMAIL)
                .subject(AccountTestConstant.CUSTOMER_EMAIL)
                .body(TEST_BODY)
                .build();

        thenEmailIsSentWithoutFailure(emailInfo);
        thenEmailContentIsCorrect();
    }

    private void thenEmailIsSentWithoutFailure(EmailInfo emailInfo) {
        assertThatNoException().isThrownBy(() -> emailService.sendMail(emailInfo));
    }

    private void thenEmailContentIsCorrect() throws Exception {
        MimeMessage[] receivedMessages = greenMail.getReceivedMessages();
        assertThat(receivedMessages).hasSize(1);

        MimeMessage receivedMessage = greenMail.getReceivedMessages()[0];
        assertThat(receivedMessage).isNotNull();
        assertThat(receivedMessage.getSubject()).isEqualTo(AccountTestConstant.CUSTOMER_EMAIL);

        final Object content = receivedMessage.getContent();
        assertThat(content).isInstanceOf(String.class);
        final String body = (String) content;
        assertThat(body).isEqualTo(TEST_BODY);
    }
}
