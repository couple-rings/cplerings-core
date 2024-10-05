package com.cplerings.core.infrastructure.email;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import com.cplerings.core.application.shared.service.email.EmailService;
import com.cplerings.core.common.dto.EmailInfo;

import jakarta.mail.Message;
import jakarta.mail.internet.InternetAddress;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String emailSender;

    public void sendMail(EmailInfo emailInfo) {
        final MimeMessagePreparator messagePreparator = mimeMessage -> {
            mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(emailInfo.getRecipient()));
            mimeMessage.setFrom(new InternetAddress(emailSender));
            mimeMessage.setSubject(emailInfo.getSubject());
            mimeMessage.setContent(emailInfo.getBody(), "text/html; charset=utf-8");
        };
        javaMailSender.send(messagePreparator);
    }
}
