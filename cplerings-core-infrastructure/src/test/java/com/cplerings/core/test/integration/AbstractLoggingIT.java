package com.cplerings.core.test.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.slf4j.LoggerFactory;

import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.test.integration.internal.CustomLogAppender;

import ch.qos.logback.classic.Logger;
import lombok.AccessLevel;
import lombok.Getter;

public class AbstractLoggingIT extends AbstractIT {

    @Getter(AccessLevel.PROTECTED)
    private CustomLogAppender useCaseLogAppender;

    @BeforeEach
    protected void setUpUseCaseLogAppender() {
        this.useCaseLogAppender = new CustomLogAppender();
        final Logger logger = (Logger) LoggerFactory.getLogger(AbstractUseCase.class);
        logger.addAppender(this.useCaseLogAppender);
        useCaseLogAppender.start();
    }

    @AfterEach
    protected void resetUseCaseLogAppender() {
        useCaseLogAppender.stop();
        useCaseLogAppender.getLoggingEvents().clear();
        final Logger logger = (Logger) LoggerFactory.getLogger(AbstractUseCase.class);
        logger.detachAppender(useCaseLogAppender);
    }
}
