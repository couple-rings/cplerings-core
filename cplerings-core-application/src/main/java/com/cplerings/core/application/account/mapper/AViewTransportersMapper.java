package com.cplerings.core.application.account.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cplerings.core.application.account.datasource.result.Jewelers;
import com.cplerings.core.application.account.datasource.result.Transporters;
import com.cplerings.core.application.account.output.ViewTransportersOutput;
import com.cplerings.core.application.account.output.result.JewelersOutputResult;
import com.cplerings.core.application.account.output.result.TransportersOutputResult;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface AViewTransportersMapper {

    @Mapping(target = "items", source = "transporters")
    ViewTransportersOutput toOutput(TransportersOutputResult transportersOutputResult);

    TransportersOutputResult toTransporters(Transporters transporters);
}
