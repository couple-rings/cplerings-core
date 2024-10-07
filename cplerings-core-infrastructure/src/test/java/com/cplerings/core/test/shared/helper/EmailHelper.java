package com.cplerings.core.test.shared.helper;

import lombok.RequiredArgsConstructor;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.context.ApplicationContext;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;
import com.icegreen.greenmail.util.ServerSetupTest;

@TestComponent
@RequiredArgsConstructor
public class EmailHelper {

    public static final String TEST_EMAIL = "localhost";

    private final ApplicationContext applicationContext;

    public Pair<GreenMail, JavaMailSender> startServer() {
        final ServerSetup serverSetup = ServerSetupTest.SMTP.dynamicPort();
        final GreenMail greenMail = new GreenMail(serverSetup);
        greenMail.start();

        final JavaMailSender javaMailSender = applicationContext.getBean(JavaMailSender.class);
        if (javaMailSender instanceof JavaMailSenderImpl impl) {
            impl.setPort(greenMail.getSmtp().getPort());
            impl.setUsername(null);
            impl.setPassword(null);
        }
        return Pair.of(greenMail, javaMailSender);
    }
}
