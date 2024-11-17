package com.cplerings.core.application.design.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.application.design.output.ViewDesignSessionsLeftOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface AViewDesignSessionsLeftMapper {

    ViewDesignSessionsLeftOutput toOutput(Integer numOfSessions);
}
