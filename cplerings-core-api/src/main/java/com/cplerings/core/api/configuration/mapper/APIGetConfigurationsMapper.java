package com.cplerings.core.api.configuration.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.api.configuration.data.GetConfigurationsData;
import com.cplerings.core.api.configuration.request.GetConfigurationsRequest;
import com.cplerings.core.api.configuration.response.GetConfigurationsResponse;
import com.cplerings.core.api.shared.mapper.APIPaginatedMapper;
import com.cplerings.core.application.configuration.input.GetConfigurationsInput;
import com.cplerings.core.application.configuration.output.GetConfigurationsOutput;
import com.cplerings.core.application.shared.entity.configuration.AConfiguration;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIGetConfigurationsMapper extends APIPaginatedMapper<GetConfigurationsInput, GetConfigurationsOutput, GetConfigurationsData, AConfiguration, GetConfigurationsRequest, GetConfigurationsResponse> {
}
