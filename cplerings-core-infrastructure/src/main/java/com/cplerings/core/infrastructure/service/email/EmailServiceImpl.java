package com.cplerings.core.infrastructure.service.email;

import com.cplerings.core.application.shared.service.email.EmailInfo;
import com.cplerings.core.application.shared.service.email.EmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import jakarta.mail.Message;
import jakarta.mail.internet.InternetAddress;
import jakarta.transaction.Transactional;

@Service
@Transactional(rollbackOn = Exception.class)
public class EmailServiceImpl implements EmailService {

    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String emailSender;

    public void sendMail(EmailInfo emailInfo) {
        final MimeMessagePreparator messagePreparator = (mimeMessage -> {
            mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(emailInfo.getRecipient()));
            mimeMessage.setFrom(new InternetAddress(emailSender));
            mimeMessage.setSubject(emailInfo.getSubject());
            mimeMessage.setContent(emailInfo.getBody(), "text/html; charset=utf-8");
        });
        javaMailSender.send(messagePreparator);
    }

    @Autowired
    public void setJavaMailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
}
