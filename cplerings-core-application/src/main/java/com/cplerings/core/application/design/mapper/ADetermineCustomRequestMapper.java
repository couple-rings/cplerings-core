package com.cplerings.core.application.design.mapper;

import java.util.Set;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cplerings.core.application.design.output.DetermineCustomRequestOutput;
import com.cplerings.core.application.shared.entity.design.ADesign;
import com.cplerings.core.application.shared.entity.design.request.ACustomRequest;
import com.cplerings.core.application.shared.mapper.DesignSizeMapper;
import com.cplerings.core.application.shared.mapper.MoneyMapper;
import com.cplerings.core.application.shared.mapper.WeightMapper;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;
import com.cplerings.core.domain.design.Design;
import com.cplerings.core.domain.design.request.CustomRequest;
import com.cplerings.core.domain.design.request.DesignCustomRequest;

@Mapper(
        config = SpringMapperConfiguration.class,
        uses = {
                WeightMapper.class,
                DesignSizeMapper.class,
                MoneyMapper.class
        }
)
public interface ADetermineCustomRequestMapper {

    DetermineCustomRequestOutput toOutput(CustomRequest customRequest);

    @Mapping(target = "designs", expression = "java(mapDesigns(customRequest.getDesignCustomRequests()))")
    ACustomRequest toACustomRequest(CustomRequest customRequest);

    default Set<ADesign> mapDesigns(Set<DesignCustomRequest> designCustomRequests) {
        return designCustomRequests.stream()
                .map(DesignCustomRequest::getDesign)
                .map(this::toADesign)
                .collect(Collectors.toSet());
    }

    ADesign toADesign(Design design);
}
