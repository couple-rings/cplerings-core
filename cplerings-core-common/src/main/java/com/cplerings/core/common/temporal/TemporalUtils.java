package com.cplerings.core.common.temporal;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TemporalUtils {

    public static LocalDateTime getCurrentDateTimeUTC() {
        return LocalDateTime.ofInstant(getCurrentInstantUTC(), ZoneOffset.UTC);
    }

    public static Instant getCurrentInstantUTC() {
        return Instant.now();
    }
}
