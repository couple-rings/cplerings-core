package com.cplerings.core.application.design.mapper;

import com.cplerings.core.application.design.output.ViewCustomRequestOutput;
import com.cplerings.core.application.shared.entity.design.ADesign;
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
public interface AViewCustomRequestMapper {

    @Mapping(target = "designs", source = "designCustomRequests")
    ViewCustomRequestOutput toOutput(CustomRequest customRequest);

    default Set<ADesign> mapDesigns(Set<DesignCustomRequest> designCustomRequests) {
        return designCustomRequests.stream()
                .map(designCustomRequest -> mapToADesign(designCustomRequest.getDesign()))
                .collect(Collectors.toSet());
    }

    ADesign mapToADesign(Design design);
}
