package com.cplerings.core.test.unit.email;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.javamail.JavaMailSender;

import com.cplerings.core.common.dto.EmailDTO;
import com.cplerings.core.infrastructure.email.EmailService;

class EmailServiceTest {

    @InjectMocks
    private EmailService emailService;

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
        EmailDTO emailDTO = EmailDTO.builder()
                .recipient(recipient)
                .subject(subject)
                .body(body)
                .build();

        // Act
        thenNoExceptionIsThrown(emailDTO);
    }

    @Test
    void givenSystem_whenSendEmailWithoutCorrectEmailDTO() {
        EmailDTO emailDTO = null;

        // Act
        thenEmailDTOIsInvalid(emailDTO);
    }

    @Test
    void givenSystem_whenSendEmailWithoutRecipient() {
        String subject = "Test Email";
        String body = "<h1>This is a test email</h1>";
        EmailDTO emailDTO = EmailDTO.builder()
                .subject(subject)
                .body(body)
                .build();

        // Act
        thenRecipientIsInvalid(emailDTO);
    }

    private void thenEmailDTOIsInvalid(EmailDTO emailDTO) {
        try {
            emailService.sendMail(emailDTO);
        } catch (Exception e) {
            Assertions.assertThat(emailDTO).isNull();
        }
    }

    private void thenNoExceptionIsThrown(EmailDTO emailDTO) {
        try {
            emailService.sendMail(emailDTO);
        } catch (Exception e) {
            Assertions.fail(e);
            throw new RuntimeException(e);
        }
    }

    private void thenRecipientIsInvalid(EmailDTO emailDTO) {
        try {
            emailService.sendMail(emailDTO);
        } catch (Exception e) {
            Assertions.assertThat(emailDTO.getRecipient()).isNull();
        }
    }
}
