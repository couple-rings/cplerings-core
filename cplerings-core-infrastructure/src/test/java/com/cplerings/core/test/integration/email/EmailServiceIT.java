package com.cplerings.core.test.integration.email;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.cplerings.core.test.integration.shared.AbstractIT;
import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;

import jakarta.mail.internet.MimeMessage;

class EmailServiceIT extends AbstractIT {

    @Autowired
    private JavaMailSender javaMailSender;

    private GreenMail greenMail;

    @BeforeEach
    public void setUp() {
        // Set up GreenMail SMTP server for testing
        greenMail = new GreenMail(new ServerSetup(3025, null, "smtp"));
        greenMail.start();

        // Configure JavaMailSender
        JavaMailSenderImpl mailSender = (JavaMailSenderImpl) javaMailSender;
        mailSender.setHost("localhost");
        mailSender.setPort(3025);

        mailSender.getJavaMailProperties().put("mail.smtp.starttls.enable", "false");
        mailSender.getJavaMailProperties().put("mail.smtp.ssl.enable", "false");
        mailSender.getJavaMailProperties().put("mail.smtp.auth", "false");
        mailSender.getJavaMailProperties().put("mail.transport.protocol", "smtp");

        mailSender.setUsername(null);
        mailSender.setPassword(null);
    }

    @AfterEach
    public void tearDown() {
        // Stop GreenMail server after each test
        greenMail.stop();
        greenMail = null;
    }

    @Test
    void givenSystem_whenSendingEmail() throws Exception {
        // Arrange
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setTo("test@example.com");
        helper.setSubject("Test Subject");
        helper.setText("Test Body", true);

        thenTheEmailIsSendWithNoFault(mimeMessage);
    }

    private void thenTheEmailIsSendWithNoFault(MimeMessage mimeMessage) throws Exception{
        // Act
        javaMailSender.send(mimeMessage);

        // Assert
        MimeMessage[] receivedMessages = greenMail.getReceivedMessages();
        Assertions.assertThat(receivedMessages).hasSize(1);
        MimeMessage receivedMessage = greenMail.getReceivedMessages()[0];
        Assertions.assertThat(receivedMessage.getSubject()).isEqualTo("Test Subject");
        Assertions.assertThat(receivedMessage.getAllRecipients()[0].toString()).isEqualTo("test@example.com");
    }
}
