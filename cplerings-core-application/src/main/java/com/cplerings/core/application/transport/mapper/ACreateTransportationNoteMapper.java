package com.cplerings.core.application.transport.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.application.shared.mapper.ATransportationNoteMapper;
import com.cplerings.core.application.transport.output.CreateTransportationNoteOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;
import com.cplerings.core.domain.order.status.TransportationNote;

@Mapper(config = SpringMapperConfiguration.class,
        uses = {
                ATransportationNoteMapper.class
        })
public interface ACreateTransportationNoteMapper {

        CreateTransportationNoteOutput toOutput(TransportationNote transportationNote);
}
