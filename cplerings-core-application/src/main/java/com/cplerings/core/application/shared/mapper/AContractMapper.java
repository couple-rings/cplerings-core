package com.cplerings.core.application.shared.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.application.shared.entity.contract.AContract;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;
import com.cplerings.core.domain.contract.Contract;

@Mapper(
        config = SpringMapperConfiguration.class,
        uses = {
                AImageMapper.class,
                ADocumentMapper.class,
        }
)
public interface AContractMapper {

    AContract toContract(Contract contract);
}
