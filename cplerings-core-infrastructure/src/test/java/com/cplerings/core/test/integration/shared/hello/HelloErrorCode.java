package com.cplerings.core.test.integration.shared.hello;

import com.cplerings.core.application.shared.errorcode.ErrorCode;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum HelloErrorCode implements ErrorCode {

    BAD_HELLO("001", "hello.error.badHello", Type.BUSINESS);

    private final String code;
    private final String descriptionLocale;
    private final Type type;
}
