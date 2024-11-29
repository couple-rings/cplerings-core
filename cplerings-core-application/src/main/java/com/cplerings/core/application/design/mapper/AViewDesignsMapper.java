package com.cplerings.core.application.design.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cplerings.core.application.design.datasource.result.Designs;
import com.cplerings.core.application.design.output.ViewDesignsOutput;
import com.cplerings.core.application.shared.mapper.ADesignMapper;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class,
        uses = {
                ADesignMapper.class,
        })
public interface AViewDesignsMapper {

        @Mapping(target = "items", source = "designs")
        ViewDesignsOutput toOutput(Designs designs);
}
