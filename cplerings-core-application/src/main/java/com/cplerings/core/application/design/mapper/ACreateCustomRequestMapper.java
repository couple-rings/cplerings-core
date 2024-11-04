package com.cplerings.core.application.design.mapper;

import com.cplerings.core.application.design.output.CreateCustomRequestOutput;
import com.cplerings.core.application.shared.mapper.ADesignMapper;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;
import com.cplerings.core.domain.design.request.CustomRequest;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        config = SpringMapperConfiguration.class,
        uses = {
                ADesignMapper.class
        }
)
public interface ACreateCustomRequestMapper {

    @Mapping(target = "request", source = "customRequest")
    CreateCustomRequestOutput toOutput(CustomRequest customRequest);
}
