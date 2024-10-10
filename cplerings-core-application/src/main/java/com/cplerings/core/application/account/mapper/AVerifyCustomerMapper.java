package com.cplerings.core.application.account.mapper;

import com.cplerings.core.application.authentication.output.AuthenticationTokenOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

import org.mapstruct.Mapper;

@Mapper(config = SpringMapperConfiguration.class)
public interface AVerifyCustomerMapper {

    AuthenticationTokenOutput toOutput(String token, String refreshToken);
}
