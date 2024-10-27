package com.cplerings.core.application.design.mapper;

import com.cplerings.core.application.design.output.CheckRemainingDesignSessionOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

import org.mapstruct.Mapper;

@Mapper(config = SpringMapperConfiguration.class)
public interface ACheckRemainingDesignSessionMapper {

    CheckRemainingDesignSessionOutput toOutput(Integer remainingCount);
}
