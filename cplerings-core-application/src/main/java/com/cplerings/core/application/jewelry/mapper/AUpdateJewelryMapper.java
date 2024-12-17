package com.cplerings.core.application.jewelry.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.application.jewelry.output.UpdateJewelryOutput;
import com.cplerings.core.application.shared.mapper.AJewelryMapper;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;
import com.cplerings.core.domain.jewelry.Jewelry;

@Mapper(config = SpringMapperConfiguration.class,
        uses = {
                AJewelryMapper.class,
        })
public interface AUpdateJewelryMapper {

        UpdateJewelryOutput toOutput(Jewelry jewelry);
}
