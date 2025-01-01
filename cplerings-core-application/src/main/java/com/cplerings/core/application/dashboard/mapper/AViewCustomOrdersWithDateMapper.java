package com.cplerings.core.application.dashboard.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cplerings.core.application.dashboard.datasource.data.CombinedOrders;
import com.cplerings.core.application.dashboard.output.ViewCustomOrdersWithDateOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface AViewCustomOrdersWithDateMapper {

    @Mapping(target = "items", source = "orders")
    ViewCustomOrdersWithDateOutput toOutput(CombinedOrders orders);
}
