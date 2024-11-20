package com.cplerings.core.application.shared.mapper;

import com.cplerings.core.application.shared.entity.design.ACustomDesign;
import com.cplerings.core.application.shared.entity.design.ADesign;
import com.cplerings.core.application.shared.entity.design.ADesignCollection;
import com.cplerings.core.application.shared.entity.design.ADesignCouple;
import com.cplerings.core.application.shared.entity.design.ADesignDiamondSpecification;
import com.cplerings.core.application.shared.entity.design.ADesignMetalSpecification;
import com.cplerings.core.application.shared.entity.design.ADiamondSpecification;
import com.cplerings.core.application.shared.entity.design.AMetalSpecification;
import com.cplerings.core.application.shared.entity.design.request.ACustomRequest;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;
import com.cplerings.core.domain.design.CustomDesign;
import com.cplerings.core.domain.design.CustomDesignDiamondSpecification;
import com.cplerings.core.domain.design.CustomDesignMetalSpecification;
import com.cplerings.core.domain.design.Design;
import com.cplerings.core.domain.design.DesignCollection;
import com.cplerings.core.domain.design.DesignCouple;
import com.cplerings.core.domain.design.DesignDiamondSpecification;
import com.cplerings.core.domain.design.DesignMetalSpecification;
import com.cplerings.core.domain.design.request.CustomRequest;
import com.cplerings.core.domain.design.request.DesignCustomRequest;
import com.cplerings.core.domain.metal.MetalSpecification;

import org.apache.commons.collections4.CollectionUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Mapper(
        config = SpringMapperConfiguration.class,
        uses = {
                AImageMapper.class,
                ADocumentMapper.class,
                AEnumMapper.class,
                MoneyMapper.class,
                DesignSizeMapper.class,
                WeightMapper.class,
                AAccountMapper.class,
                ADiamondMapper.class,
                ASpouseMapper.class,
        }
)
public abstract class ADesignMapper {

    @Autowired
    private ADiamondMetalSpecificationMapper diamondMetalSpecificationMapper;

    public abstract ADesignCouple toDesignCouple(DesignCouple designCouple);

    public abstract ADesign toDesign(Design design);

    public abstract ADesignMetalSpecification toDesignMetalSpecification(DesignMetalSpecification designMetalSpecification);

    public abstract ADesignDiamondSpecification toDesignDiamondSpecification(DesignDiamondSpecification designDiamondSpecification);

    public abstract ADesignCollection toDesignCollection(DesignCollection designCollection);

    public abstract AMetalSpecification toMetalSpecification(MetalSpecification metalSpecification);

    @Mapping(target = "designs", source = "designCustomRequests")
    public abstract ACustomRequest toCustomRequest(CustomRequest customRequest);

    @Mapping(target = "diamondSpecifications", source = "customDesignDiamondSpecifications")
    @Mapping(target = "metalSpecifications", source = "customDesignMetalSpecifications")
    public abstract ACustomDesign toCustomDesign(CustomDesign customDesign);

    public final ADesign toDesign(DesignCustomRequest designCustomRequest) {
        if (designCustomRequest == null) {
            return null;
        }
        return toDesign(designCustomRequest.getDesign());
    }

    public final List<ADiamondSpecification> toDiamondSpecifications(Collection<CustomDesignDiamondSpecification> customDesignDiamondSpecifications) {
        if (CollectionUtils.isEmpty(customDesignDiamondSpecifications)) {
            return new ArrayList<>();
        }
        return customDesignDiamondSpecifications.stream()
                .filter(Objects::nonNull)
                .map(spec -> diamondMetalSpecificationMapper.toDiamondSpecification(spec.getDiamondSpecification()))
                .toList();
    }

    public final List<AMetalSpecification> toMetalSpecifications(Collection<CustomDesignMetalSpecification> customDesignMetalSpecifications) {
        if (CollectionUtils.isEmpty(customDesignMetalSpecifications)) {
            return new ArrayList<>();
        }
        return customDesignMetalSpecifications.stream()
                .filter(Objects::nonNull)
                .map(spec -> diamondMetalSpecificationMapper.toMetalSpecification(spec.getMetalSpecification()))
                .toList();
    }
}
