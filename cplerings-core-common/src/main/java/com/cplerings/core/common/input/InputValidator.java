package com.cplerings.core.common.input;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import org.apache.commons.lang3.StringUtils;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class InputValidator {

    private static final String EMAIL_REGEX = "^[\\w-]+(?:.[\\w-]+)*@[\\w-]+(?:.[\\w-]+)*$";

    public static boolean emailIsValid(String email) {
        if (StringUtils.isBlank(email)) {
            return false;
        }
        return email.matches(EMAIL_REGEX);
    }

    public static boolean numberIsPositive(Long number) {
        return ((number != null) && (number > 0));
    }
}
