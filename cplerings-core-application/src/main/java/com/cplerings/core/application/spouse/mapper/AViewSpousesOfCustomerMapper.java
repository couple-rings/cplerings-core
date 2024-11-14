package com.cplerings.core.application.spouse.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.application.spouse.datasource.result.SpouseList;
import com.cplerings.core.application.spouse.output.ViewSpousesOfCustomerOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface AViewSpousesOfCustomerMapper {

    ViewSpousesOfCustomerOutput toOutput(SpouseList spouses);
}
