package com.cplerings.core.application.shared.errorcode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public interface ErrorCode {

    String getCode();

    String getDescriptionLocale();

    Type getType();

    enum Type {

        VALIDATION, BUSINESS, SYSTEM
    }

    @Getter
    @RequiredArgsConstructor
    enum System implements ErrorCode {

        ERROR("000", "system.error", Type.SYSTEM),
        INPUT_REQUIRED("001", "system.error.inputRequired", Type.VALIDATION),
        ;

        private final String code;
        private final String descriptionLocale;
        private final Type type;
    }
}
