package com.cplerings.core.application.design.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.application.design.output.CreateDesignVersionOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;
import com.cplerings.core.domain.design.DesignVersion;

@Mapper(config = SpringMapperConfiguration.class)
public interface ACreateDesignVersionMapper {

    CreateDesignVersionOutput toOutput(DesignVersion designVersion);
}
