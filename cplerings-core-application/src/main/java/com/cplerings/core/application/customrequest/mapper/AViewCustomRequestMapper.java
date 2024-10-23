package com.cplerings.core.application.customrequest.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.application.customrequest.output.ViewCustomRequestOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;
import com.cplerings.core.domain.design.request.CustomRequest;

@Mapper(config = SpringMapperConfiguration.class)
public interface AViewCustomRequestMapper {

    ViewCustomRequestOutput toOutput(CustomRequest customRequest);
}
