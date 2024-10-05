package com.cplerings.core.api.authentication.mapper;

import com.cplerings.core.api.authentication.data.AuthenticationToken;
import com.cplerings.core.api.authentication.request.RefreshTokenRequest;
import com.cplerings.core.api.authentication.response.AuthenticationTokenResponse;
import com.cplerings.core.api.mapper.APIMapper;
import com.cplerings.core.application.authentication.input.RefreshTokenInput;
import com.cplerings.core.application.authentication.output.AuthenticationTokenOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

import org.mapstruct.Mapper;

@Mapper(config = SpringMapperConfiguration.class)
public interface RefreshTokenAPIMapper extends APIMapper<RefreshTokenInput, AuthenticationTokenOutput, AuthenticationToken, RefreshTokenRequest, AuthenticationTokenResponse> {

}
