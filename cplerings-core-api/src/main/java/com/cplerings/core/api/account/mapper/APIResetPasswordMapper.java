package com.cplerings.core.api.account.mapper;

import com.cplerings.core.api.account.request.ResetPasswordRequest;
import com.cplerings.core.api.mapper.APINoMapper;
import com.cplerings.core.application.account.input.ResetPasswordInput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

import org.mapstruct.Mapper;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIResetPasswordMapper extends APINoMapper<ResetPasswordInput, ResetPasswordRequest> {

}
