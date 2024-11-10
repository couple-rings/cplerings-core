package com.cplerings.core.application.design.mapper;

import com.cplerings.core.application.design.datasource.result.CustomRequests;
import com.cplerings.core.application.design.output.ViewCustomRequestsOutput;
import com.cplerings.core.application.shared.entity.design.ADesign;
import com.cplerings.core.application.shared.entity.design.request.ACustomRequest;
import com.cplerings.core.application.shared.mapper.DesignSizeMapper;
import com.cplerings.core.application.shared.mapper.MoneyMapper;
import com.cplerings.core.application.shared.mapper.WeightMapper;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;
import com.cplerings.core.domain.design.Design;
import com.cplerings.core.domain.design.request.CustomRequest;
import com.cplerings.core.domain.design.request.DesignCustomRequest;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(config = SpringMapperConfiguration.class,
        uses = {
                WeightMapper.class,
                DesignSizeMapper.class,
                MoneyMapper.class
        })
public interface AViewCustomRequestsMapper {

    @Mapping(source = "customRequests", target = "items")
    ViewCustomRequestsOutput toOutput(CustomRequests customRequests);

    @Mapping(target = "designs", expression = "java(mapDesigns(customRequest.getDesignCustomRequests()))")
    ACustomRequest toACustomRequest(CustomRequest customRequest);

    default Set<ADesign> mapDesigns(Set<DesignCustomRequest> designCustomRequests) {
        return designCustomRequests.stream()
                .map(DesignCustomRequest::getDesign) // Extract Design from each DesignCustomRequest
                .map(this::toADesign) // Map Design to ADesign
                .collect(Collectors.toSet()); // Collect into a Set
    }

    ADesign toADesign(Design design);
}
