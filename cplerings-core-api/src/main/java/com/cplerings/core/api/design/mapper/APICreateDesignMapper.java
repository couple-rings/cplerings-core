package com.cplerings.core.api.design.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cplerings.core.api.design.request.CreateDesignRequest;
import com.cplerings.core.api.design.response.CreateDesignResponse;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.application.design.input.CreateDesignInput;
import com.cplerings.core.application.design.output.CreateDesignOutput;
import com.cplerings.core.application.shared.entity.design.ADesign;
import com.cplerings.core.application.shared.mapper.ADesignMapper;

@Mapper(config = APIMapper.class,
uses = {
        ADesignMapper.class,
})
public interface APICreateDesignMapper extends APIMapper<CreateDesignInput, CreateDesignOutput, ADesign, CreateDesignRequest, CreateDesignResponse> {

    @Mapping(target = ".", source = "design")
    ADesign toData(CreateDesignOutput createDesignOutput);
}
