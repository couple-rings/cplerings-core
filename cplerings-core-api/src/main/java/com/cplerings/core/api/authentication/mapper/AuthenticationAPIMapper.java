package com.cplerings.core.api.authentication.mapper;

import com.cplerings.core.api.authentication.data.AuthenticationToken;
import com.cplerings.core.api.authentication.request.LoginCredentialRequest;
import com.cplerings.core.api.authentication.response.AuthenticationTokenResponse;
import com.cplerings.core.application.authentication.input.LoginCredentialInput;
import com.cplerings.core.application.authentication.output.AuthenticationTokenOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

import org.mapstruct.Mapper;

@Mapper(config = SpringMapperConfiguration.class)
public interface AuthenticationAPIMapper {

    LoginCredentialInput toInput(LoginCredentialRequest request);

    AuthenticationToken toData(AuthenticationTokenOutput output);

    AuthenticationTokenResponse toResponse(AuthenticationToken data);
}
