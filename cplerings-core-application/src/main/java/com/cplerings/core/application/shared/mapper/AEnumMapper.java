package com.cplerings.core.application.shared.mapper;

import com.cplerings.core.application.shared.entity.account.ARole;
import com.cplerings.core.application.shared.entity.crafting.ACraftingStageStatus;
import com.cplerings.core.application.shared.entity.design.ADesignCharacteristic;
import com.cplerings.core.application.shared.entity.design.ADiamondClarity;
import com.cplerings.core.application.shared.entity.design.ADiamondColor;
import com.cplerings.core.application.shared.entity.design.ADiamondShape;
import com.cplerings.core.application.shared.entity.design.AMetalColor;
import com.cplerings.core.application.shared.entity.design.request.ACustomRequestStatus;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;
import com.cplerings.core.domain.account.Role;
import com.cplerings.core.domain.crafting.CraftingStageStatus;
import com.cplerings.core.domain.design.DesignCharacteristic;
import com.cplerings.core.domain.design.request.CustomRequestStatus;
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

    ACustomRequestStatus toCustomRequestStatus(CustomRequestStatus customRequestStatus);

    ACraftingStageStatus toCraftingStageStatus(CraftingStageStatus craftingStageStatus);

    ARole toRole(Role role);
}
