package com.cplerings.core.api.design.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.api.design.data.DesignCollectionsData;
import com.cplerings.core.api.design.request.ViewDesignCollectionsRequest;
import com.cplerings.core.api.design.response.ViewDesignCollectionsResponse;
import com.cplerings.core.api.shared.mapper.APIPaginatedMapper;
import com.cplerings.core.application.design.input.ViewDesignCollectionsInput;
import com.cplerings.core.application.design.output.ViewDesignCollectionsOutput;
import com.cplerings.core.application.shared.entity.design.ADesignCollection;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIViewDesignCollectionsMapper extends APIPaginatedMapper<ViewDesignCollectionsInput, ViewDesignCollectionsOutput, DesignCollectionsData, ADesignCollection, ViewDesignCollectionsRequest, ViewDesignCollectionsResponse> {
}
