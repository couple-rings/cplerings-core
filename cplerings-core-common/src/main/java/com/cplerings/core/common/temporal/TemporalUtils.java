package com.cplerings.core.common.temporal;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TemporalUtils {

    public static LocalDateTime getCurrentDateTimeInVietnam() {
        return getCurrentDateTimeUTC().atZone(ZoneOffset.UTC)
                .withZoneSameInstant(ZoneOffset.ofHours(7))
                .toLocalDateTime();
    }

    public static LocalDateTime getCurrentDateTimeUTC() {
        return LocalDateTime.ofInstant(getCurrentInstantUTC(), ZoneOffset.UTC);
    }

    public static Instant getCurrentInstantUTC() {
        return Instant.now();
    }

    public static LocalDateTime getCurrentDateTimeInVietnam(Instant time) {
        if (time == null) {
            return null;
        }
        return LocalDateTime.ofInstant(time, ZoneOffset.UTC)
                .atZone(ZoneOffset.UTC)
                .withZoneSameInstant(ZoneOffset.ofHours(7))
                .toLocalDateTime();
    }

    public static boolean isAfterOrEqual(Instant instantToCheck, Instant instantToCheckAgainst) {
        if (instantToCheck == null || instantToCheckAgainst == null) {
            return false;
        }
        return !instantToCheck.isBefore(instantToCheckAgainst);
    }
}
