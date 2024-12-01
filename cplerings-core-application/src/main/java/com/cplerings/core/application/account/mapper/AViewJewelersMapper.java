package com.cplerings.core.application.account.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cplerings.core.application.account.datasource.result.Jewelers;
import com.cplerings.core.application.account.output.ViewJewelersOutput;
import com.cplerings.core.application.account.output.result.JewelersOutputResult;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface AViewJewelersMapper {

    @Mapping(target = "items", source = "jewelers")
    ViewJewelersOutput toOutput(JewelersOutputResult jewelersOutputResult);

    JewelersOutputResult toJewelers(Jewelers jewelers);
}
