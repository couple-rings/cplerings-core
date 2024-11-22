package com.cplerings.core.api.spouse.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.spouse.data.SpouseData;
import com.cplerings.core.api.spouse.request.VerifyResidentIdRequest;
import com.cplerings.core.api.spouse.response.VerifyResidentIdResponse;
import com.cplerings.core.application.spouse.input.VerifyResidentIdInput;
import com.cplerings.core.application.spouse.output.VerifyResidentIdOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIVerifyResidentIdMapper extends APIMapper<VerifyResidentIdInput, VerifyResidentIdOutput, SpouseData, VerifyResidentIdRequest, VerifyResidentIdResponse> {
}
