package com.cplerings.core.application.shared.usecase;

import java.util.Comparator;
import java.util.Objects;

public class ErrorCodeComparator implements Comparator<ErrorCode> {

    @Override
    public int compare(ErrorCode o1, ErrorCode o2) {
        if (o1 == o2) {
            return 0;
        }
        if (o1 == null) {
            return 1;
        }
        if (o2 == null) {
            return -1;
        }
        final int codeCompare = Objects.compare(o1.getCode(), o2.getCode(), String::compareTo);
        if (codeCompare != 0) {
            return codeCompare;
        }
        final int descriptionLocaleCompare = Objects.compare(o1.getDescriptionLocale(), o2.getDescriptionLocale(),
                String::compareTo);
        if (descriptionLocaleCompare != 0) {
            return descriptionLocaleCompare;
        }
        return Objects.compare(o1.getType(), o2.getType(), Enum::compareTo);
    }
}
