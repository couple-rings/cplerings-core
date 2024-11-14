package com.cplerings.core.api.spouse.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.spouse.data.SpouseList;
import com.cplerings.core.api.spouse.request.ViewSpousesOfCustomerRequest;
import com.cplerings.core.api.spouse.response.ViewSpousesOfCustomerResponse;
import com.cplerings.core.application.spouse.input.ViewSpousesOfCustomerInput;
import com.cplerings.core.application.spouse.output.ViewSpousesOfCustomerOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIViewSpousesOfCustomerMapper extends APIMapper<ViewSpousesOfCustomerInput, ViewSpousesOfCustomerOutput, SpouseList, ViewSpousesOfCustomerRequest, ViewSpousesOfCustomerResponse> {
}
