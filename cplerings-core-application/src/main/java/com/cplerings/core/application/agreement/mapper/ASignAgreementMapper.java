package com.cplerings.core.application.agreement.mapper;

import com.cplerings.core.application.agreement.output.SignAgreementOutput;
import com.cplerings.core.application.shared.mapper.AAgreementMapper;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;
import com.cplerings.core.domain.spouse.Agreement;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        config = SpringMapperConfiguration.class,
        uses = {
                AAgreementMapper.class,
        }
)
public interface ASignAgreementMapper {

    @Mapping(target = "agreement", source = ".")
    SignAgreementOutput toOutput(Agreement agreement);
}
