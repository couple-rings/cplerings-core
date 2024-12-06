package com.cplerings.core.application.configuration.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cplerings.core.application.configuration.datasource.data.Configurations;
import com.cplerings.core.application.configuration.output.GetConfigurationsOutput;
import com.cplerings.core.application.shared.mapper.AConfigurationMapper;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class,
        uses = {
                AConfigurationMapper.class
        })
public interface AGetConfigurationsMapper {

        @Mapping(target = "items", source = "configurations")
        GetConfigurationsOutput toOutput(Configurations configurations);
}
