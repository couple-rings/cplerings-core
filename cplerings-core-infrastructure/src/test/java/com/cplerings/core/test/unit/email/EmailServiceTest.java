package com.cplerings.core.test.unit.email;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.test.context.TestPropertySource;

import com.cplerings.core.infrastructure.email.EmailConfiguration;
import com.cplerings.core.infrastructure.email.EmailService;

@SpringBootTest(classes = {EmailService.class, EmailConfiguration.class})
@TestPropertySource(properties = {
        "spring.mail.host=smtp.gmail.com",
        "spring.mail.port=587",
        "spring.mail.username=caolinh1012003@gmail.com",
        "spring.mail.password=ijbr zavh ymuz gmlj",
        "spring.mail.properties.mail.smtp.auth=true",
        "spring.mail.properties.mail.smtp.starttls.enable=true"
})
class EmailServiceTest {

    @Autowired
    private EmailService emailService;  // Inject mocks into EmailService
    @Autowired
    private JavaMailSender javaMailSender;

    @BeforeEach
    void setUp() {
        // Initialize the mocks
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void givenSystem_whenSendEmailAccurately_thenShouldSendEmail() {
        String recipient = "nguyendocaolinh@gmail.com";
        String subject = "Test Email";
        String body = "<h1>This is a test email</h1>";

        // Act
        emailService.sendMail(recipient, body, subject);
    }
}
