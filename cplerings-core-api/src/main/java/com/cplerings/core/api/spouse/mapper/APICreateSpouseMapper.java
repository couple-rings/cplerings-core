package com.cplerings.core.api.spouse.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.api.mapper.APINoMapper;
import com.cplerings.core.api.spouse.request.CreateSpouseRequest;
import com.cplerings.core.application.spouse.input.CreateSpouseInput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface APICreateSpouseMapper extends APINoMapper<CreateSpouseInput, CreateSpouseRequest> {
}
