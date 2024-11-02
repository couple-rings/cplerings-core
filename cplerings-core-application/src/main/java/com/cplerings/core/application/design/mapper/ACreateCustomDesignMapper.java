package com.cplerings.core.application.design.mapper;

import com.cplerings.core.application.design.output.CreateCustomDesignOutput;
import com.cplerings.core.application.shared.mapper.DesignSizeMapper;
import com.cplerings.core.application.shared.mapper.MoneyMapper;
import com.cplerings.core.application.shared.mapper.WeightMapper;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;
import com.cplerings.core.domain.design.CustomDesign;

import org.mapstruct.Mapper;

@Mapper(
        config = SpringMapperConfiguration.class,
        uses = {
                WeightMapper.class,
                DesignSizeMapper.class,
                MoneyMapper.class
        }
)
public interface ACreateCustomDesignMapper {

    CreateCustomDesignOutput toOutput(CustomDesign customDesign);
}
