package com.cplerings.core.application.order.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.application.order.output.GetCustomOrderByOrderNoOutput;
import com.cplerings.core.application.shared.mapper.ARingMapper;
import com.cplerings.core.application.shared.mapper.DesignSizeMapper;
import com.cplerings.core.application.shared.mapper.MoneyMapper;
import com.cplerings.core.application.shared.mapper.WeightMapper;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;
import com.cplerings.core.domain.order.CustomOrder;

@Mapper(config = SpringMapperConfiguration.class,
        uses = {
                WeightMapper.class,
                DesignSizeMapper.class,
                MoneyMapper.class,
                ARingMapper.class
        })
public interface AGetCustomOrderByOrderNoMapper {

        GetCustomOrderByOrderNoOutput toOutput(CustomOrder customOrder);
}
