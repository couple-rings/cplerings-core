package com.cplerings.core.application.design.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.cplerings.core.application.design.output.CreateCustomDesignOutput;
import com.cplerings.core.application.shared.entity.design.ACustomDesign;
import com.cplerings.core.application.shared.entity.design.ADiamondSpecification;
import com.cplerings.core.application.shared.entity.design.AMetalSpecification;
import com.cplerings.core.application.shared.mapper.DesignSizeMapper;
import com.cplerings.core.application.shared.mapper.MoneyMapper;
import com.cplerings.core.application.shared.mapper.WeightMapper;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;
import com.cplerings.core.domain.design.CustomDesign;
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
public interface ACreateCustomDesignMapper {

    default CreateCustomDesignOutput toOutput(CustomDesign customDesign,
                                              List<MetalSpecification> metalSpecifications,
                                              List<DiamondSpecification> diamondSpecifications) {
        ACustomDesign aCustomDesign = toACustomDesign(customDesign);

        aCustomDesign.setMetalSpecifications(toAMetalSpecificationList(metalSpecifications));
        aCustomDesign.setDiamondSpecifications(toADiamondSpecificationList(diamondSpecifications));

        return new CreateCustomDesignOutput(aCustomDesign);
    }

    ACustomDesign toACustomDesign(CustomDesign customDesign);

    List<AMetalSpecification> toAMetalSpecificationList(List<MetalSpecification> metalSpecifications);

    List<ADiamondSpecification> toADiamondSpecificationList(List<DiamondSpecification> diamondSpecifications);
}
