package com.cplerings.core.application.design.mapper;

import java.util.UUID;

import org.mapstruct.Mapper;

import com.cplerings.core.application.design.output.CreateDesignSessionOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface ACreateDesignSessionMapper {

    CreateDesignSessionOutput toOutput(UUID sessionId);
}
