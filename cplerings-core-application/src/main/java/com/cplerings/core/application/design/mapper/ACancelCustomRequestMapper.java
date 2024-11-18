package com.cplerings.core.application.design.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.application.design.output.CancelCustomRequestOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;
import com.cplerings.core.domain.design.request.CustomRequest;

@Mapper(config = SpringMapperConfiguration.class)
public interface ACancelCustomRequestMapper {

    CancelCustomRequestOutput toOutput(CustomRequest customRequest);
}
