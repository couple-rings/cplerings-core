package com.cplerings.core.application.branch.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cplerings.core.application.branch.datasource.result.Branches;
import com.cplerings.core.application.branch.output.ViewBranchesOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface AViewBranchesMapper {

    @Mapping(target = "items", source = "branches")
    ViewBranchesOutput toOutput(Branches branch);
}
