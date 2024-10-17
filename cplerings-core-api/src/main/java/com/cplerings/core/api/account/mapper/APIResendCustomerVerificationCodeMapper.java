package com.cplerings.core.api.account.mapper;

import com.cplerings.core.api.account.data.CustomerEmailInfo;
import com.cplerings.core.api.account.request.ResendCustomerVerificationCodeRequest;
import com.cplerings.core.api.account.response.CustomerEmailInfoResponse;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.application.account.input.ResendCustomerVerificationCodeInput;
import com.cplerings.core.application.account.output.ResendCustomerVerificationCodeOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

import org.mapstruct.Mapper;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIResendCustomerVerificationCodeMapper extends APIMapper<ResendCustomerVerificationCodeInput, ResendCustomerVerificationCodeOutput, CustomerEmailInfo, ResendCustomerVerificationCodeRequest, CustomerEmailInfoResponse> {

}
