package com.cplerings.core.application.dashboard.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cplerings.core.application.dashboard.datasource.data.CombinedOrders;
import com.cplerings.core.application.dashboard.output.ViewRefundOrdersWithDateOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface AViewRefundOrdersWithDateMapper {

    @Mapping(target = "items", source = "orders")
    ViewRefundOrdersWithDateOutput toOutput(CombinedOrders orders);
}
