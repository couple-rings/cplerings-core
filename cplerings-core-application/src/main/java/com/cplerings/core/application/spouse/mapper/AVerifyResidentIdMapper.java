package com.cplerings.core.application.spouse.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.application.spouse.output.VerifyResidentIdOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;
import com.cplerings.core.domain.spouse.Spouse;

@Mapper(config = SpringMapperConfiguration.class)
public interface AVerifyResidentIdMapper {

    VerifyResidentIdOutput toOutput(Spouse spouse);
}
