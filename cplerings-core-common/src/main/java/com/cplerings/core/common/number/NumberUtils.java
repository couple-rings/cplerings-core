package com.cplerings.core.common.number;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class NumberUtils {

    public static boolean isLessThanOrEqual(BigDecimal first, BigDecimal second) {
        if (first == null || second == null) {
            return false;
        }
        return (first.compareTo(second) <= 0);
    }

    public static boolean isLessThan(BigDecimal first, BigDecimal second) {
        if (first == null || second == null) {
            return false;
        }
        return (first.compareTo(second) < 0);
    }

    public static boolean isZeroOrPositive(BigDecimal number) {
        if (number == null) {
            return false;
        }
        return number.compareTo(BigDecimal.ZERO) >= 0;
    }
}
