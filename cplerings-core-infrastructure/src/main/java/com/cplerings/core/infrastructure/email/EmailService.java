package com.cplerings.core.infrastructure.email;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import com.cplerings.core.application.shared.service.email.IEmailService;
import com.cplerings.core.common.dto.EmailDTO;

import jakarta.mail.Message;
import jakarta.mail.internet.InternetAddress;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailService implements IEmailService {

    @NonNull
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String emailSender;

    public void sendMail(EmailDTO emailDTO) {
        if (emailDTO == null) {
            throw new IllegalArgumentException("EmailDTO cannot be null");
        }
        if (emailDTO.getRecipient().isEmpty()) {
            throw new IllegalArgumentException("Recipient cannot be empty");
        }

        final MimeMessagePreparator messagePreparator = mimeMessage -> {
            mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(emailDTO.getRecipient()));
            mimeMessage.setFrom(new InternetAddress(emailSender));
            mimeMessage.setSubject(emailDTO.getSubject());
            mimeMessage.setContent(emailDTO.getBody(), "text/html; charset=utf-8");
        };
        javaMailSender.send(messagePreparator);
    }
}
