package com.cplerings.core.application.design.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.application.design.output.CreateCustomDesignOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;
import com.cplerings.core.domain.design.CustomDesign;

@Mapper(config = SpringMapperConfiguration.class)
public interface ACreateCustomDesignMapper {

    CreateCustomDesignOutput toOutput(CustomDesign customDesign);
}
