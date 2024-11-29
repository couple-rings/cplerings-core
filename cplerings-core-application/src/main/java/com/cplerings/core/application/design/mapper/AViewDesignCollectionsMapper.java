package com.cplerings.core.application.design.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cplerings.core.application.design.datasource.result.DesignCollections;
import com.cplerings.core.application.design.output.ViewDesignCollectionsOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface AViewDesignCollectionsMapper {

    @Mapping(target = "items", source = "designCollections")
    ViewDesignCollectionsOutput toOutput(DesignCollections designCollections);
}
