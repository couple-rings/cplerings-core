package com.cplerings.core.api.account.mapper;

import com.cplerings.core.api.account.data.CustomerEmailInfo;
import com.cplerings.core.api.account.request.RequestResetPasswordRequest;
import com.cplerings.core.api.account.response.CustomerEmailInfoResponse;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.application.account.input.RequestResetPasswordInput;
import com.cplerings.core.application.account.output.RequestResetPasswordOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

import org.mapstruct.Mapper;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIRequestResetPasswordMapper extends APIMapper<RequestResetPasswordInput, RequestResetPasswordOutput, CustomerEmailInfo, RequestResetPasswordRequest, CustomerEmailInfoResponse> {

}
