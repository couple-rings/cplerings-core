package com.cplerings.core.api.design.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.api.shared.mapper.APIPaginatedMapper;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public class APIViewDesignVersionsMapper extends APIPaginatedMapper<> {
}
