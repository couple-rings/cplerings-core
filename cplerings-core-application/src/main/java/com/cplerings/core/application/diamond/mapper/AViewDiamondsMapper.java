package com.cplerings.core.application.diamond.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cplerings.core.application.diamond.datasource.result.Diamonds;
import com.cplerings.core.application.diamond.output.ViewDiamondsOutput;
import com.cplerings.core.application.shared.mapper.ADiamondMapper;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(
        config = SpringMapperConfiguration.class,
        uses = {
                ADiamondMapper.class,
        }
)
public interface AViewDiamondsMapper {

    @Mapping(target = "items", source = "diamonds")
    ViewDiamondsOutput toOutput(Diamonds diamonds);
}
