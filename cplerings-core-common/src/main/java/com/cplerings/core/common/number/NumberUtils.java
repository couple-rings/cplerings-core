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

    public static boolean isPositive(Long number) {
        if (number == null) {
            return false;
        }
        return (number > 0);
    }

    public static boolean isLessThan(Long numberToBeCompared, Long numberToCompareAgainst) {
        if (numberToBeCompared == null || numberToCompareAgainst == null) {
            return false;
        }
        return (numberToBeCompared < numberToCompareAgainst);
    }

    public static boolean isLessThan(Integer numberToBeCompared, Integer numberToCompareAgainst) {
        if (numberToBeCompared == null || numberToCompareAgainst == null) {
            return false;
        }
        return (numberToBeCompared < numberToCompareAgainst);
    }
}
