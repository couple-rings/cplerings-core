package com.cplerings.core.application.account.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cplerings.core.application.account.datasource.result.Transporters;
import com.cplerings.core.application.account.output.ViewTransportersOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface AViewTransportersMapper {

    @Mapping(target = "items", source = "transporters")
    ViewTransportersOutput toOutput(Transporters transporters);
}
