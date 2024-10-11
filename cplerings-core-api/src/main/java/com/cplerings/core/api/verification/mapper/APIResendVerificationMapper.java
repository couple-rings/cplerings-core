package com.cplerings.core.api.verification.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.api.account.data.CustomerEmailInfo;
import com.cplerings.core.api.account.response.CustomerEmailInfoResponse;
import com.cplerings.core.api.mapper.APIMapper;
import com.cplerings.core.api.verification.request.ResendVerificationRequest;
import com.cplerings.core.application.verification.input.ResendVerificationInput;
import com.cplerings.core.application.verification.output.ResendVerificationOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIResendVerificationMapper extends APIMapper<ResendVerificationInput, ResendVerificationOutput, CustomerEmailInfo, ResendVerificationRequest, CustomerEmailInfoResponse> {
}
