package com.cplerings.core.application.dashboard.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cplerings.core.application.dashboard.output.ViewCustomOrdersWithDateOutput;
import com.cplerings.core.application.order.datasource.result.CustomOrders;
import com.cplerings.core.application.shared.mapper.ARingMapper;
import com.cplerings.core.application.shared.mapper.DesignSizeMapper;
import com.cplerings.core.application.shared.mapper.MoneyMapper;
import com.cplerings.core.application.shared.mapper.WeightMapper;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class,
uses = {
        WeightMapper.class,
        DesignSizeMapper.class,
        MoneyMapper.class,
        ARingMapper.class,
})
public interface AViewCustomOrdersWithDateMapper {

    @Mapping(target = "items", source = "customOrders")
    ViewCustomOrdersWithDateOutput toOutput(CustomOrders orders);
}
