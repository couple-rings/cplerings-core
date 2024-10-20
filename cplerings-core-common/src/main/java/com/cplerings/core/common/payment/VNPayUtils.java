package com.cplerings.core.common.payment;

import com.cplerings.core.common.temporal.TemporalUtils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import org.apache.commons.lang3.StringUtils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class VNPayUtils {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    public static String toDate(LocalDateTime time) {
        if (time == null) {
            return StringUtils.EMPTY;
        }
        return DateTimeFormatter.ofPattern("yyyyMMddHHmmss").format(time);
    }

    public static String toDate(Instant time) {
        if (time == null) {
            return StringUtils.EMPTY;
        }
        return DateTimeFormatter.ofPattern("yyyyMMddHHmmss").format(TemporalUtils.getCurrentDateTimeInVietnam(time));
    }

    public static Instant toInstant(String vnPayDate) {
        return LocalDateTime.parse(vnPayDate, DATE_TIME_FORMATTER)
                .atZone(ZoneOffset.ofHours(7))
                .toInstant();
    }
}
