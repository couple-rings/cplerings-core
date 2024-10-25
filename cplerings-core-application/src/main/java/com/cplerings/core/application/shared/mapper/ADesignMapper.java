package com.cplerings.core.application.shared.mapper;

import com.cplerings.core.application.shared.entity.design.ADesign;
import com.cplerings.core.application.shared.entity.design.ADesignCollection;
import com.cplerings.core.application.shared.entity.design.ADesignCouple;
import com.cplerings.core.application.shared.entity.design.ADesignDiamondSpecification;
import com.cplerings.core.application.shared.entity.design.ADesignMetalSpecification;
import com.cplerings.core.application.shared.entity.design.ADiamondSpecification;
import com.cplerings.core.application.shared.entity.design.AMetalSpecification;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;
import com.cplerings.core.domain.design.Design;
import com.cplerings.core.domain.design.DesignCollection;
import com.cplerings.core.domain.design.DesignCouple;
import com.cplerings.core.domain.design.DesignDiamondSpecification;
import com.cplerings.core.domain.design.DesignMetalSpecification;
import com.cplerings.core.domain.diamond.DiamondSpecification;
import com.cplerings.core.domain.metal.MetalSpecification;

import org.mapstruct.Mapper;

@Mapper(
        config = SpringMapperConfiguration.class,
        uses = {
                AImageMapper.class,
                ADocumentMapper.class,
                AEnumMapper.class,
                MoneyMapper.class,
                DesignSizeMapper.class,
                WeightMapper.class,
        }
)
public interface ADesignMapper {

    ADesignCouple toDesignCouple(DesignCouple designCouple);

    ADesign toDesign(Design design);

    ADesignMetalSpecification toDesignMetalSpecification(DesignMetalSpecification designMetalSpecification);

    ADesignDiamondSpecification toDesignDiamondSpecification(DesignDiamondSpecification designDiamondSpecification);

    ADesignCollection toDesignCollection(DesignCollection designCollection);

    AMetalSpecification toMetalSpecification(MetalSpecification metalSpecification);

    ADiamondSpecification toDiamondSpecification(DiamondSpecification diamondSpecification);
}
