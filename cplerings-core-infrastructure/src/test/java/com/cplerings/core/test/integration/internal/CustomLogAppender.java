package com.cplerings.core.test.integration.internal;

import lombok.Getter;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;

import java.util.ArrayList;
import java.util.List;

@Getter
public final class CustomLogAppender extends AppenderBase<ILoggingEvent> {

    private final List<ILoggingEvent> loggingEvents = new ArrayList<>();

    @Override
    protected void append(ILoggingEvent iLoggingEvent) {
        loggingEvents.add(iLoggingEvent);
    }
}
