package com.cplerings.core.application.shared.mapper;

import com.cplerings.core.application.shared.entity.spouse.ASpouse;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;
import com.cplerings.core.domain.spouse.Spouse;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = SpringMapperConfiguration.class)
public interface ASpouseMapper {

    @Mapping(target = "customerId", expression = "java(toCustomerId(spouse))")
    ASpouse toSpouse(Spouse spouse);

    default Long toCustomerId(Spouse spouse) {
        if (spouse == null || spouse.getSpouseAccount() == null || spouse.getSpouseAccount().getCustomer() == null) {
            return null;
        }
        return spouse.getSpouseAccount().getCustomer().getId();
    }
}
