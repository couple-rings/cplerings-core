package com.cplerings.core.application.design.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.application.design.output.ViewDesignOutput;
import com.cplerings.core.application.shared.mapper.ADesignMapper;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;
import com.cplerings.core.domain.design.Design;

@Mapper(config = SpringMapperConfiguration.class,
        uses = {
                ADesignMapper.class
        }
)
public interface AViewDesignMapper {

        ViewDesignOutput toOutput(Design design);
}
