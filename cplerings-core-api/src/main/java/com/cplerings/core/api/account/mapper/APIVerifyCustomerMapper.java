package com.cplerings.core.api.account.mapper;

import com.cplerings.core.api.account.request.VerifyCustomerRequest;
import com.cplerings.core.api.authentication.data.AuthenticationToken;
import com.cplerings.core.api.authentication.response.AuthenticationTokenResponse;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.application.account.input.VerifyCustomerInput;
import com.cplerings.core.application.authentication.output.AuthenticationTokenOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

import org.mapstruct.Mapper;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIVerifyCustomerMapper extends APIMapper<VerifyCustomerInput, AuthenticationTokenOutput, AuthenticationToken, VerifyCustomerRequest, AuthenticationTokenResponse> {

}
