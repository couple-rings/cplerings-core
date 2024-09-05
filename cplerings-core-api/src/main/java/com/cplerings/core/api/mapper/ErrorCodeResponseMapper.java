package com.cplerings.core.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;

import com.cplerings.core.api.ErrorCodeResponse;
import com.cplerings.core.application.shared.errorcode.ErrorCode;
import com.cplerings.core.common.locale.LocaleHelper;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ErrorCodeResponseMapper {

    ErrorCodeResponseMapper INSTANCE = Mappers.getMapper(ErrorCodeResponseMapper.class);

    @Mapping(target = "description", expression = "java(toDescription(errorCode))")
    ErrorCodeResponse toResponse(ErrorCode errorCode);

    ErrorCodeResponse.Type toType(ErrorCode.Type type);

    default String toDescription(ErrorCode errorCode) {
        return LocaleHelper.translateLocale(errorCode.getDescriptionLocale());
    }
}
