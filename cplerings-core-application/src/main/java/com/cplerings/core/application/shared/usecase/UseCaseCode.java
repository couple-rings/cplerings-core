package com.cplerings.core.application.shared.usecase;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum UseCaseCode {

    CPLERINGS_1("As Anyone, I can login to my corresponding role.");

    private final String description;
}
