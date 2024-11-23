package com.cplerings.core.application.spouse.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.cplerings.core.application.shared.entity.spouse.ASpouse;
import com.cplerings.core.application.spouse.datasource.result.SpouseList;
import com.cplerings.core.application.spouse.output.ViewSpousesOfCustomerOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;
import com.cplerings.core.domain.spouse.Spouse;

@Mapper(config = SpringMapperConfiguration.class)
public interface AViewSpousesOfCustomerMapper {

    ViewSpousesOfCustomerOutput toOutput(SpouseList spouses);

    default List<ASpouse> mapSpouses(List<Spouse> spouses) {
        return spouses.stream().map(spouse -> ASpouse.builder()
                        .fullName(spouse.getFullName())
                        .id(spouse.getId())
                        .customerId(spouse.getSpouseAccount() != null && spouse.getSpouseAccount().getCustomer() != null
                                ? spouse.getSpouseAccount().getCustomer().getId()
                                : null)
                        .coupleId(spouse.getCoupleId())
                        .createdAt(spouse.getCreatedAt())
                        .build())
                .toList();
    }
}
