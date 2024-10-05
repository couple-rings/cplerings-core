package com.cplerings.core.infrastructure.email;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class EmailConfiguration {

    private static final String PROTOCOL = "mail.transport.protocol";
    private static final String AUTHENTICATION = "mail.smtp.auth";
    private static final String ENABLE = "mail.smtp.starttls.enable";
    private static final String REQUIRED = "mail.smtp.starttls.required";
    private static final String SMTP = "smtp";
    private static final int PORT = 587;

    @Value("${spring.mail.username}")
    private String email;

    @Value("${spring.mail.password}")
    private String password;

    @Value("${spring.mail.host}")
    private String host;

    @Value("${spring.mail.properties.mail.smtp.auth}")
    private String authentication;

    @Value("${spring.mail.properties.mail.smtp.starttls.enable}")
    private String starttlsEnable;

    @Value("${spring.mail.properties.mail.smtp.starttls.required}")
    private String starttlsRequire;

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(PORT);
        mailSender.setUsername(email);
        mailSender.setPassword(password);

        Properties props = mailSender.getJavaMailProperties();
        props.put(PROTOCOL, SMTP);
        props.put(AUTHENTICATION, authentication);
        props.put(ENABLE, starttlsEnable);
        props.put(REQUIRED, starttlsRequire);

        return mailSender;
    }
}
