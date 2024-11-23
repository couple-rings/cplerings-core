package com.cplerings.core.application.transport.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.application.shared.mapper.ATransportationMapper;
import com.cplerings.core.application.transport.output.GetTransportationOrderByCustomOrderOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;
import com.cplerings.core.domain.order.TransportationOrder;

@Mapper(config = SpringMapperConfiguration.class,
        uses = {
            ATransportationMapper.class
        }
)
public interface AGetTransportationOrderByCustomOrderMapper {

    GetTransportationOrderByCustomOrderOutput toOutput(TransportationOrder transportationOrder);
}
