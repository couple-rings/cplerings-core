package com.cplerings.core.application.order.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cplerings.core.application.order.datasource.result.ResellOrders;
import com.cplerings.core.application.order.output.ViewResellOrdersOutput;
import com.cplerings.core.application.shared.mapper.AResellOrderMapper;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class,
        uses = {
                AResellOrderMapper.class,
        })
public interface AViewResellOrdersMapper {

    @Mapping(target = "items", source = "resellOrders")
    ViewResellOrdersOutput toOutput(ResellOrders resellOrders);
}
