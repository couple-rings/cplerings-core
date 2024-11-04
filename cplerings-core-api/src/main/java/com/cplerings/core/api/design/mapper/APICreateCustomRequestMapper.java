package com.cplerings.core.api.design.mapper;

import com.cplerings.core.api.design.request.CreateCustomRequestRequest;
import com.cplerings.core.api.design.response.CreateCustomRequestResponse;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.application.design.input.CreateCustomRequestInput;
import com.cplerings.core.application.design.output.CreateCustomRequestOutput;
import com.cplerings.core.application.shared.entity.design.request.ACustomRequest;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = SpringMapperConfiguration.class)
public interface APICreateCustomRequestMapper extends APIMapper<CreateCustomRequestInput, CreateCustomRequestOutput, ACustomRequest, CreateCustomRequestRequest, CreateCustomRequestResponse> {

    @Override
    @Mapping(target = ".", source = "request")
    ACustomRequest toData(CreateCustomRequestOutput output);
}
