package com.cplerings.core.application.shared.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.application.shared.entity.configuration.AConfiguration;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;
import com.cplerings.core.domain.configuration.Configuration;

@Mapper(config = SpringMapperConfiguration.class)
public interface AConfigurationMapper {

    AConfiguration toAConfiguration(Configuration configuration);
}
