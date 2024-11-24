package com.cplerings.core.application.transport.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cplerings.core.application.shared.mapper.ARingMapper;
import com.cplerings.core.application.shared.mapper.DesignSizeMapper;
import com.cplerings.core.application.shared.mapper.MoneyMapper;
import com.cplerings.core.application.shared.mapper.WeightMapper;
import com.cplerings.core.application.transport.datasource.result.TransportationOrders;
import com.cplerings.core.application.transport.output.ViewTransportationOrdersOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class,
        uses = {
                WeightMapper.class,
                DesignSizeMapper.class,
                MoneyMapper.class,
                ARingMapper.class,
        })
public interface AViewTransportationOrdersMapper {

    @Mapping(target = "items", source = "transportationOrders")
    ViewTransportationOrdersOutput toOutput(TransportationOrders transportationOrders);
}
