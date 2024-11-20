package com.cplerings.core.application.shared.mapper;

import com.cplerings.core.application.shared.entity.branch.ABranch;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;
import com.cplerings.core.domain.branch.Branch;

import org.mapstruct.Mapper;

@Mapper(
        config = SpringMapperConfiguration.class,
        uses = {
                AImageMapper.class,
        }
)
public interface ABranchMapper {

    ABranch toBranch(Branch branch);
}
