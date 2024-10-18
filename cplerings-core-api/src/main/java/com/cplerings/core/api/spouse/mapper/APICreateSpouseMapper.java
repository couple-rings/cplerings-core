package com.cplerings.core.api.spouse.mapper;

import com.cplerings.core.api.shared.mapper.APINoResponseMapper;
import com.cplerings.core.api.spouse.request.CreateSpouseRequest;
import com.cplerings.core.application.spouse.input.CreateSpouseInput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

import org.mapstruct.Mapper;

@Mapper(config = SpringMapperConfiguration.class)
public interface APICreateSpouseMapper extends APINoResponseMapper<CreateSpouseInput, CreateSpouseRequest> {

}
