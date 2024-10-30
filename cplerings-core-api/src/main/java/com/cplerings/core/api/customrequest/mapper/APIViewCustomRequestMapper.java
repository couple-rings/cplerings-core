package com.cplerings.core.api.customrequest.mapper;

import com.cplerings.core.api.customrequest.data.CustomRequest;
import com.cplerings.core.api.customrequest.request.ViewCustomRequestRequest;
import com.cplerings.core.api.customrequest.response.ViewCustomRequestResponse;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.application.customrequest.input.ViewCustomRequestInput;
import com.cplerings.core.application.customrequest.output.ViewCustomRequestOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

import org.mapstruct.Mapper;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIViewCustomRequestMapper extends APIMapper<ViewCustomRequestInput, ViewCustomRequestOutput, CustomRequest, ViewCustomRequestRequest, ViewCustomRequestResponse> {

}
