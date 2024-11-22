package com.cplerings.core.application.shared.mapper;

import com.cplerings.core.application.shared.entity.agreement.AAgreement;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;
import com.cplerings.core.domain.spouse.Agreement;

import org.mapstruct.Mapper;

@Mapper(
        config = SpringMapperConfiguration.class,
        uses = {
                AAccountMapper.class,
                AImageMapper.class,
        }
)
public interface AAgreementMapper {

    AAgreement toAgreement(Agreement agreement);
}
