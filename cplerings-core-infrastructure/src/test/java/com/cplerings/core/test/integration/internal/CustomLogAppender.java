package com.cplerings.core.test.integration.internal;

import java.util.ArrayList;
import java.util.List;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import lombok.Getter;

@Getter
public final class CustomLogAppender extends AppenderBase<ILoggingEvent> {

    private final List<ILoggingEvent> loggingEvents = new ArrayList<>();

    @Override
    protected void append(ILoggingEvent iLoggingEvent) {
        loggingEvents.add(iLoggingEvent);
    }
}
