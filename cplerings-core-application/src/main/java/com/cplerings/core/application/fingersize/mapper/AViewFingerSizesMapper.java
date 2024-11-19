package com.cplerings.core.application.fingersize.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cplerings.core.application.fingersize.datasource.result.FingerSizes;
import com.cplerings.core.application.fingersize.output.ViewFingerSizesOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface AViewFingerSizesMapper {

    @Mapping(target = "items", source = "fingerSizes")
    ViewFingerSizesOutput toOutput(FingerSizes fingerSizes);
}
