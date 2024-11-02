package com.cplerings.core.application.design.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.application.design.output.ViewCustomDesignOutput;
import com.cplerings.core.application.shared.mapper.DesignSizeMapper;
import com.cplerings.core.application.shared.mapper.MoneyMapper;
import com.cplerings.core.application.shared.mapper.WeightMapper;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;
import com.cplerings.core.domain.design.CustomDesign;

@Mapper(
        config = SpringMapperConfiguration.class,
        uses = {
                WeightMapper.class,
                DesignSizeMapper.class,
                MoneyMapper.class
        }
)
public interface AViewCustomDesignMapper {

    ViewCustomDesignOutput toOutput(CustomDesign customDesign);
}