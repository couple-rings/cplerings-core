package com.cplerings.core.api.design.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.api.design.data.CustomDesignData;
import com.cplerings.core.api.design.data.CustomDesigns;
import com.cplerings.core.api.design.request.ViewCustomDesignsRequest;
import com.cplerings.core.api.design.response.ViewCustomDesignsResponse;
import com.cplerings.core.api.shared.mapper.APIPaginatedMapper;
import com.cplerings.core.application.design.input.ViewCustomDesignsInput;
import com.cplerings.core.application.design.output.ViewCustomDesignsOutput;
import com.cplerings.core.application.shared.entity.design.ACustomDesign;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIViewCustomDesignsMapper extends APIPaginatedMapper<ViewCustomDesignsInput, ViewCustomDesignsOutput, CustomDesigns, ACustomDesign, ViewCustomDesignsRequest, ViewCustomDesignsResponse> {
}
