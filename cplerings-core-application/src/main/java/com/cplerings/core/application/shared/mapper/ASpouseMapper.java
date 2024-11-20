package com.cplerings.core.application.shared.mapper;

import com.cplerings.core.application.shared.entity.spouse.ASpouse;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;
import com.cplerings.core.domain.spouse.Spouse;

import org.mapstruct.Mapper;

@Mapper(config = SpringMapperConfiguration.class)
public interface ASpouseMapper {

    ASpouse toSpouse(Spouse spouse);
}
