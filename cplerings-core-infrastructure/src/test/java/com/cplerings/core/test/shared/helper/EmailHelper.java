package com.cplerings.core.test.shared.helper;

import java.util.concurrent.atomic.AtomicBoolean;

import org.springframework.boot.test.context.TestComponent;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;

import lombok.RequiredArgsConstructor;

@TestComponent
@RequiredArgsConstructor
public class EmailHelper {

    public static final String TEST_EMAIL = "localhost";

    private static final int PORT = 3025;
    private static final String PROTOCOL = "smtp";
    private static final String HOST = "localhost";
    private static final String START_TLS = "mail.smtp.starttls.enable";
    private static final String SSL = "mail.smtp.ssl.enable";
    private static final String SMTP_AUTH = "mail.smtp.auth";
    private static final String TRANSPORT_PROTOCOL = "mail.transport.protocol";

    private static final AtomicBoolean JAVA_MAIL_IS_SET_UP = new AtomicBoolean(false);

    private final JavaMailSender javaMailSender;

    public GreenMail startServer() {
        final GreenMail greenMail = new GreenMail(new ServerSetup(PORT, null, PROTOCOL));
        greenMail.start();

        if (JAVA_MAIL_IS_SET_UP.compareAndSet(false, true)) {
            JavaMailSenderImpl mailSender = (JavaMailSenderImpl) javaMailSender;
            mailSender.setHost(HOST);
            mailSender.setPort(PORT);

            mailSender.getJavaMailProperties().put(START_TLS, "false");
            mailSender.getJavaMailProperties().put(SSL, "false");
            mailSender.getJavaMailProperties().put(SMTP_AUTH, "false");
            mailSender.getJavaMailProperties().put(TRANSPORT_PROTOCOL, PROTOCOL);

            mailSender.setUsername(null);
            mailSender.setPassword(null);
        }
        return greenMail;
    }
}
