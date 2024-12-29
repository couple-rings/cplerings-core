package com.cplerings.core.application.spouse.mapper;

import com.cplerings.core.application.shared.mapper.ASpouseMapper;
import com.cplerings.core.application.spouse.datasource.result.SpouseList;
import com.cplerings.core.application.spouse.output.ViewSpousesOfCustomerOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

import org.mapstruct.Mapper;

@Mapper(
        config = SpringMapperConfiguration.class,
        uses = {
                ASpouseMapper.class,
        }
)
public interface AViewSpousesOfCustomerMapper {

    ViewSpousesOfCustomerOutput toOutput(SpouseList spouses);
}
