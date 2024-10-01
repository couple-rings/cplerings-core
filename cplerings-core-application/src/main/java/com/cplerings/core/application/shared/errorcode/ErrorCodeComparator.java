package com.cplerings.core.application.shared.errorcode;

import java.util.Comparator;
import java.util.Objects;

public class ErrorCodeComparator implements Comparator<ErrorCode> {

    @Override
    public int compare(ErrorCode errorCode1, ErrorCode errorCode2) {
        if (errorCode1 == errorCode2) {
            return 0;
        }
        if (errorCode1 == null) {
            return 1;
        }
        if (errorCode2 == null) {
            return -1;
        }
        final int codeCompare = Objects.compare(errorCode1.getCode(), errorCode2.getCode(), String::compareTo);
        if (codeCompare != 0) {
            return codeCompare;
        }
        final int descriptionLocaleCompare = Objects.compare(errorCode1.getDescriptionLocale(), errorCode2.getDescriptionLocale(),
                String::compareTo);
        if (descriptionLocaleCompare != 0) {
            return descriptionLocaleCompare;
        }
        return Objects.compare(errorCode1.getType(), errorCode2.getType(), Enum::compareTo);
    }
}
