package com.cplerings.core.application.transport.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.application.shared.mapper.ATransportationOrderMapper;
import com.cplerings.core.application.transport.output.UpdateImageDeliveryOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;
import com.cplerings.core.domain.order.TransportationOrder;

@Mapper(config = SpringMapperConfiguration.class,
        uses = {
                ATransportationOrderMapper.class
        })
public interface AUpdateImageDeliveryMapper {

        UpdateImageDeliveryOutput toOutput(TransportationOrder transportationOrder);
}