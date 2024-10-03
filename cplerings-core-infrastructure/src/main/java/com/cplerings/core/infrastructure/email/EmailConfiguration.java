package com.cplerings.core.infrastructure.email;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class EmailConfiguration {

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Value("${spring.mail.password}")
    private String fromPassword;

    @Value("${spring.mail.host}")
    private String fromHost;

    @Value("${spring.mail.properties.mail.smtp.auth}")
    private String getAuth;

    @Value("${spring.mail.properties.mail.smtp.starttls.enable}")
    private String getStarttlsEnable;

    @Value("${spring.mail.properties.mail.smtp.starttls.required}")
    private String getStarttlsRequire;

    private static final String protocol = "mail.transport.protocol";
    private static final String auth = "mail.smtp.auth";
    private static final String enable = "mail.smtp.starttls.enable";
    private static final String required = "mail.smtp.starttls.required";

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(fromHost);
        mailSender.setPort(587);
        mailSender.setUsername(fromEmail);
        mailSender.setPassword(fromPassword);

        Properties props = mailSender.getJavaMailProperties();
        props.put(protocol, "smtp");
        props.put(auth, getAuth);
        props.put(enable, getStarttlsEnable);
        props.put(required, getStarttlsRequire);

        return mailSender;
    }
}
