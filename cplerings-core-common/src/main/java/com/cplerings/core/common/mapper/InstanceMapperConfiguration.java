package com.cplerings.core.common.mapper;

import org.mapstruct.MapperConfig;
import org.mapstruct.NullValueCheckStrategy;

@MapperConfig(
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface InstanceMapperConfiguration {

}
