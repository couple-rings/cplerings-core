package com.cplerings.core.application.design.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cplerings.core.application.design.datasource.result.CustomDesigns;
import com.cplerings.core.application.design.output.ViewCustomDesignsOutput;
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
public interface AViewCustomDesignsMapper {

    default ViewCustomDesignsOutput toOutput(CustomDesigns customDesigns) {
        List<ACustomDesign> aCustomDesigns = customDesigns.getCustomDesigns().stream()
                .map(this::toACustomDesignWithSpecifications)
                .collect(Collectors.toList());

        return ViewCustomDesignsOutput.builder()
                .count(Integer.parseInt(String.valueOf(customDesigns.getCount())))
                .page(customDesigns.getPage())
                .pageSize(customDesigns.getPageSize())
                .items(aCustomDesigns)
                .build();
    }

    private ACustomDesign toACustomDesignWithSpecifications(CustomDesign customDesign) {
        ACustomDesign aCustomDesign = toACustomDesign(customDesign);

        List<MetalSpecification> metalSpecifications = customDesign.getCustomDesignMetalSpecifications().stream()
                .map(CustomDesignMetalSpecification::getMetalSpecification)
                .collect(Collectors.toList());
        aCustomDesign.setMetalSpecifications(toAMetalSpecificationList(metalSpecifications));

        List<DiamondSpecification> diamondSpecifications = customDesign.getCustomDesignDiamondSpecifications().stream()
                .map(CustomDesignDiamondSpecification::getDiamondSpecification)
                .collect(Collectors.toList());
        aCustomDesign.setDiamondSpecifications(toADiamondSpecificationList(diamondSpecifications));

        return aCustomDesign;
    }

    ACustomDesign toACustomDesign(CustomDesign customDesign);

    List<AMetalSpecification> toAMetalSpecificationList(List<MetalSpecification> metalSpecifications);

    List<ADiamondSpecification> toADiamondSpecificationList(List<DiamondSpecification> diamondSpecifications);
}
