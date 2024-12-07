package com.cplerings.core.application.order.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.application.order.output.GetStandardOrderByOrderNoOutput;
import com.cplerings.core.application.shared.mapper.AStandardOrderMapper;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;
import com.cplerings.core.domain.order.StandardOrder;

@Mapper(config = SpringMapperConfiguration.class,
        uses = {
                AStandardOrderMapper.class
        })
public interface AGetStandardOrderByOrderNoMapper {

        GetStandardOrderByOrderNoOutput toOutput(StandardOrder standardOrder);
}
