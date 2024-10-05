package com.cplerings.core.test.unit.email;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.javamail.JavaMailSender;

import com.cplerings.core.common.dto.EmailInfo;
import com.cplerings.core.infrastructure.email.EmailServiceImpl;

class EmailServiceTest {

    @InjectMocks
    private EmailServiceImpl emailService;

    @Mock
    private JavaMailSender javaMailSender;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void givenSystem_whenSendEmailAccurately() {
        String recipient = "nguyendocaolinh@gmail.com";
        String subject = "Test Email";
        String body = "<h1>This is a test email</h1>";
        EmailInfo emailInfo = EmailInfo.builder()
                .recipient(recipient)
                .subject(subject)
                .body(body)
                .build();

        // Act
        thenNoExceptionIsThrown(emailInfo);
    }

    private void thenNoExceptionIsThrown(EmailInfo emailInfo) {
        try {
            emailService.sendMail(emailInfo);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
}
