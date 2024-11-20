package com.cplerings.core.application.shared.mapper;

import com.cplerings.core.application.shared.entity.design.ADiamond;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;
import com.cplerings.core.domain.diamond.Diamond;

import org.mapstruct.Mapper;

@Mapper(
        config = SpringMapperConfiguration.class,
        uses = {
                WeightMapper.class,
                MoneyMapper.class,
                AEnumMapper.class,
                ADocumentMapper.class,
                ABranchMapper.class,
                ADiamondMetalSpecificationMapper.class,
        }
)
public interface ADiamondMapper {

    ADiamond toDiamond(Diamond diamond);
}
