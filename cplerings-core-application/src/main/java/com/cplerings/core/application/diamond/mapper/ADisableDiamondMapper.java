package com.cplerings.core.application.diamond.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.application.diamond.output.DisableDiamondOutput;
import com.cplerings.core.application.shared.mapper.ADiamondMapper;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;
import com.cplerings.core.domain.diamond.Diamond;

@Mapper(config = SpringMapperConfiguration.class,
        uses = {
                ADiamondMapper.class,
        })
public interface ADisableDiamondMapper {

    DisableDiamondOutput toOutput(Diamond diamond);
}