package com.cplerings.core.application.design.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.application.design.output.ViewCoupleDesignOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface AViewCoupleDesignMapper {

    ViewCoupleDesignOutput toOutput(Design design);
}
