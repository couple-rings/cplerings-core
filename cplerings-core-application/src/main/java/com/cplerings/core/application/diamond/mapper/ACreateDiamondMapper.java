package com.cplerings.core.application.diamond.mapper;

import com.cplerings.core.application.diamond.output.CreateDiamondOutput;
import com.cplerings.core.application.shared.mapper.ADiamondMapper;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;
import com.cplerings.core.domain.diamond.Diamond;

import org.mapstruct.Mapper;

@Mapper(
        config = SpringMapperConfiguration.class,
        uses = {
                ADiamondMapper.class,
        }
)
public interface ACreateDiamondMapper {

    CreateDiamondOutput toOutput(Diamond diamond);
}
