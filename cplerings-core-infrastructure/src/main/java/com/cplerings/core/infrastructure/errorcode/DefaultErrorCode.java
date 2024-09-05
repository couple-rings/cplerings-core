package com.cplerings.core.infrastructure.errorcode;

import com.cplerings.core.application.shared.errorcode.ErrorCode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class DefaultErrorCode implements ErrorCode {

    private String code;
    private String descriptionLocale;
    private Type type;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getDescriptionLocale() {
        return descriptionLocale;
    }

    @Override
    public Type getType() {
        return type;
    }
}
