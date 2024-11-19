package com.cplerings.core.application.crafting.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.cplerings.core.application.crafting.output.ViewCraftingRequestOutput;
import com.cplerings.core.application.shared.entity.crafting.ACraftingRequest;
import com.cplerings.core.application.shared.entity.design.ACustomDesign;
import com.cplerings.core.application.shared.entity.design.ADiamondSpecification;
import com.cplerings.core.application.shared.entity.design.AMetalSpecification;
import com.cplerings.core.application.shared.mapper.DesignSizeMapper;
import com.cplerings.core.application.shared.mapper.MoneyMapper;
import com.cplerings.core.application.shared.mapper.WeightMapper;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;
import com.cplerings.core.domain.design.CustomDesign;
import com.cplerings.core.domain.design.CustomDesignDiamondSpecification;
import com.cplerings.core.domain.design.CustomDesignMetalSpecification;
import com.cplerings.core.domain.design.crafting.CraftingRequest;
import com.cplerings.core.domain.diamond.DiamondSpecification;
import com.cplerings.core.domain.metal.MetalSpecification;

@Mapper(
        config = SpringMapperConfiguration.class,
        uses = {
                WeightMapper.class,
                DesignSizeMapper.class,
                MoneyMapper.class
        }
)
public interface AViewCraftingRequestMapper {

    ViewCraftingRequestOutput toOutput(CraftingRequest craftingRequest);

    @Named("toACustomDesignWithSpecifications")
    default ACustomDesign toACustomDesignWithSpecifications(CustomDesign customDesign) {
        ACustomDesign aCustomDesign = toACustomDesign(customDesign);

        enrichWithSpecifications(customDesign, aCustomDesign);

        return aCustomDesign;
    }

    default void enrichWithSpecifications(CustomDesign customDesign, ACustomDesign aCustomDesign) {
        List<MetalSpecification> metalSpecifications = customDesign.getCustomDesignMetalSpecifications().stream()
                .map(CustomDesignMetalSpecification::getMetalSpecification)
                .collect(Collectors.toList());
        aCustomDesign.setMetalSpecifications(toAMetalSpecificationList(metalSpecifications));

        List<DiamondSpecification> diamondSpecifications = customDesign.getCustomDesignDiamondSpecifications().stream()
                .map(CustomDesignDiamondSpecification::getDiamondSpecification)
                .collect(Collectors.toList());
        aCustomDesign.setDiamondSpecifications(toADiamondSpecificationList(diamondSpecifications));
    }

    ACustomDesign toACustomDesign(CustomDesign customDesign);

    List<AMetalSpecification> toAMetalSpecificationList(List<MetalSpecification> metalSpecifications);

    List<ADiamondSpecification> toADiamondSpecificationList(List<DiamondSpecification> diamondSpecifications);

    @Mapping(target = "customDesign", source = "customDesign", qualifiedByName = "toACustomDesignWithSpecifications")
    ACraftingRequest toCraftingRequest(CraftingRequest craftingRequest);
}
