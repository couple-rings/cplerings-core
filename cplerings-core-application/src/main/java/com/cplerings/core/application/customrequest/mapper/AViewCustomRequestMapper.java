package com.cplerings.core.application.customrequest.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.application.customrequest.output.ViewCustomRequestOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface AViewCustomRequestMapper {

    ViewCustomRequestOutput toOutput(CustomRequest customRequest);
}
