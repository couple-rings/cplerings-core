package com.cplerings.core.application.transport.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.application.shared.mapper.DesignSizeMapper;
import com.cplerings.core.application.shared.mapper.MoneyMapper;
import com.cplerings.core.application.shared.mapper.WeightMapper;
import com.cplerings.core.application.transport.output.UpdateTransportationOrdersToOngoingOutput;
import com.cplerings.core.application.transport.output.data.TransportationOrderList;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class,
        uses = {
                WeightMapper.class,
                DesignSizeMapper.class,
                MoneyMapper.class
        })
public interface AUpdateTransportationOrdersToOngoingMapper {

    UpdateTransportationOrdersToOngoingOutput toOutput(TransportationOrderList transportationOrders);
}
