package com.cplerings.core.api.design.mapper;

import com.cplerings.core.api.design.data.DesignVersionsData;
import com.cplerings.core.api.design.request.ViewDesignVersionsRequest;
import com.cplerings.core.api.design.response.ViewDesignVersionsResponse;
import com.cplerings.core.api.shared.mapper.APIPaginatedMapper;
import com.cplerings.core.application.design.input.ViewDesignVersionsInput;
import com.cplerings.core.application.design.output.ViewDesignVersionsOutput;
import com.cplerings.core.application.shared.entity.design.ADesignVersion;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

import org.mapstruct.Mapper;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIViewDesignVersionsMapper extends APIPaginatedMapper<ViewDesignVersionsInput, ViewDesignVersionsOutput, DesignVersionsData, ADesignVersion, ViewDesignVersionsRequest, ViewDesignVersionsResponse> {
}
