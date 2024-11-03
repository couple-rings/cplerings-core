package com.cplerings.core.application.design.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.application.design.input.UpdateDesignVersionStatusInput;
import com.cplerings.core.application.design.output.UpdateDesignVersionStatusOutput;
import com.cplerings.core.application.shared.mapper.DesignSizeMapper;
import com.cplerings.core.application.shared.mapper.MoneyMapper;
import com.cplerings.core.application.shared.mapper.WeightMapper;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;
import com.cplerings.core.domain.design.DesignVersion;

@Mapper(
        config = SpringMapperConfiguration.class,
        uses = {
                WeightMapper.class,
                DesignSizeMapper.class,
                MoneyMapper.class
        }
)
public interface AUpdateDesignVersionStatusMapper {

        UpdateDesignVersionStatusOutput toOutput(DesignVersion designVersion);
}
