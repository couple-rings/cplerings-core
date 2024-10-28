package com.cplerings.core.application.customrequest.mapper;

import java.util.Set;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cplerings.core.application.customrequest.output.ViewCustomRequestOutput;
import com.cplerings.core.application.shared.entity.design.ADesign;
import com.cplerings.core.application.shared.mapper.DesignSizeMapper;
import com.cplerings.core.application.shared.mapper.MoneyMapper;
import com.cplerings.core.application.shared.mapper.WeightMapper;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;
import com.cplerings.core.domain.design.Design;
import com.cplerings.core.domain.design.request.CustomRequest;
import com.cplerings.core.domain.design.request.DesignCustomRequest;

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
