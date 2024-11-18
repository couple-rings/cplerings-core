package com.cplerings.core.application.agreement.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cplerings.core.application.agreement.datasource.result.Agreements;
import com.cplerings.core.application.agreement.output.ViewAgreementsOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface AViewAgreementsMapper {

    @Mapping(target = "items", source = "agreements")
    ViewAgreementsOutput toOutput(Agreements agreements);
}
