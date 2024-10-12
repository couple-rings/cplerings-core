package com.cplerings.core.api.account.mapper;

import com.cplerings.core.api.account.data.CustomerEmailInfo;
import com.cplerings.core.api.account.request.RegisterCustomerRequest;
import com.cplerings.core.api.account.response.CustomerEmailInfoResponse;
import com.cplerings.core.api.mapper.APIMapper;
import com.cplerings.core.application.account.input.RegisterCustomerInput;
import com.cplerings.core.application.account.output.CustomerRegistrationOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

import org.mapstruct.Mapper;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIRegisterAccountMapper extends APIMapper<RegisterCustomerInput, CustomerRegistrationOutput, CustomerEmailInfo, RegisterCustomerRequest, CustomerEmailInfoResponse> {

}
