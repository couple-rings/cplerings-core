package com.cplerings.core.api.verification.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.api.mapper.APIMapper;
import com.cplerings.core.api.verification.data.ResendVerification;
import com.cplerings.core.api.verification.request.ResendVerificationRequest;
import com.cplerings.core.api.verification.response.ResendVerificationResponse;
import com.cplerings.core.application.verification.input.ResendVerificationInput;
import com.cplerings.core.application.verification.output.ResendVerificationOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIResendVerificationMapper extends APIMapper<ResendVerificationInput, ResendVerificationOutput, ResendVerification, ResendVerificationRequest, ResendVerificationResponse> {
}
