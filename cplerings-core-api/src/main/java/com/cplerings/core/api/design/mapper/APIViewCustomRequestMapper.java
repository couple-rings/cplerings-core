package com.cplerings.core.api.design.mapper;

import com.cplerings.core.api.design.data.CustomRequest;
import com.cplerings.core.api.design.data.CustomRequestData;
import com.cplerings.core.api.design.request.ViewCustomRequestRequest;
import com.cplerings.core.api.design.response.ViewCustomRequestResponse;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.application.design.input.ViewCustomRequestInput;
import com.cplerings.core.application.design.output.ViewCustomRequestOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

import org.mapstruct.Mapper;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIViewCustomRequestMapper extends APIMapper<ViewCustomRequestInput, ViewCustomRequestOutput, CustomRequest, ViewCustomRequestRequest, ViewCustomRequestResponse> {

}
