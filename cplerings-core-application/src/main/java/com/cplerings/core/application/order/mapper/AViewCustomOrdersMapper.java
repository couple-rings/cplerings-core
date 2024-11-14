package com.cplerings.core.application.order.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cplerings.core.application.order.datasource.result.CustomOrders;
import com.cplerings.core.application.order.output.ViewCustomOrdersOutput;
import com.cplerings.core.application.shared.mapper.DesignSizeMapper;
import com.cplerings.core.application.shared.mapper.MoneyMapper;
import com.cplerings.core.application.shared.mapper.WeightMapper;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class,
        uses = {
                WeightMapper.class,
                DesignSizeMapper.class,
                MoneyMapper.class
        })
public interface AViewCustomOrdersMapper {

    @Mapping(target = "items", source = "customOrders")
    ViewCustomOrdersOutput toOutput(CustomOrders customOrders);
}
