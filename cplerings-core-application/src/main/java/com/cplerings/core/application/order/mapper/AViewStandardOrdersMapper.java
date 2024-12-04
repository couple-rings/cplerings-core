package com.cplerings.core.application.order.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cplerings.core.application.order.datasource.result.StandardOrders;
import com.cplerings.core.application.order.output.ViewStandardOrdersOutput;
import com.cplerings.core.application.shared.mapper.AStandardOrderMapper;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class,
        uses = {
                AStandardOrderMapper.class,
        })
public interface AViewStandardOrdersMapper {

    @Mapping(target = "items", source = "standardOrders")
    ViewStandardOrdersOutput toOutput(StandardOrders standardOrders);
}
