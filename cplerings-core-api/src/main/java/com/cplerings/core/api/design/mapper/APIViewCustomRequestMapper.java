package com.cplerings.core.api.design.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cplerings.core.api.design.request.ViewCustomRequestRequest;
import com.cplerings.core.api.design.response.ViewCustomRequestResponse;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.application.design.input.ViewCustomRequestInput;
import com.cplerings.core.application.design.output.ViewCustomRequestOutput;
import com.cplerings.core.application.shared.entity.design.request.ACustomRequest;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIViewCustomRequestMapper extends APIMapper<ViewCustomRequestInput, ViewCustomRequestOutput, ACustomRequest, ViewCustomRequestRequest, ViewCustomRequestResponse> {

    @Override
    @Mapping(target = ".", source = "customRequest")
    ACustomRequest toData(ViewCustomRequestOutput output);
}
