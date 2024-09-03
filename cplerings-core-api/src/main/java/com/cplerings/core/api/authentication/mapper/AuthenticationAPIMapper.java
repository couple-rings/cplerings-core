package com.cplerings.core.api.authentication.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.api.authentication.request.LoginCredentialRequest;
import com.cplerings.core.api.authentication.response.AuthenticationTokenResponse;
import com.cplerings.core.application.authentication.input.LoginCredentialInput;
import com.cplerings.core.application.authentication.output.AuthenticationTokenOutput;
import com.cplerings.core.common.mapper.DefaultMapperConfiguration;

@Mapper(config = DefaultMapperConfiguration.class)
public interface AuthenticationAPIMapper {

    LoginCredentialInput toInput(LoginCredentialRequest loginCredentialRequest);

    AuthenticationTokenResponse toResponse(AuthenticationTokenOutput authenticationTokenOutput);
}
