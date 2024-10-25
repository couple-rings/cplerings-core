package com.cplerings.core.application.shared.mapper;

import com.cplerings.core.application.shared.entity.design.ADesignCharacteristic;
import com.cplerings.core.application.shared.entity.design.ADiamondClarity;
import com.cplerings.core.application.shared.entity.design.ADiamondColor;
import com.cplerings.core.application.shared.entity.design.ADiamondShape;
import com.cplerings.core.application.shared.entity.design.AMetalColor;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;
import com.cplerings.core.domain.design.DesignCharacteristic;
import com.cplerings.core.domain.diamond.DiamondClarity;
import com.cplerings.core.domain.diamond.DiamondColor;
import com.cplerings.core.domain.diamond.DiamondShape;
import com.cplerings.core.domain.metal.MetalColor;

import org.mapstruct.Mapper;

@Mapper(config = SpringMapperConfiguration.class)
public interface AEnumMapper {

    ADesignCharacteristic toDesignCharacteristic(DesignCharacteristic designCharacteristic);

    ADiamondClarity toDiamondClarity(DiamondClarity diamondClarity);

    ADiamondColor toDiamondColor(DiamondColor diamondColor);

    ADiamondShape toDiamondShape(DiamondShape diamondShape);

    AMetalColor toMetalColor(MetalColor metalColor);
}
