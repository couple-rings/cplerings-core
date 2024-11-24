package com.cplerings.core.application.shared.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.application.shared.entity.transport.ATransportationNote;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;
import com.cplerings.core.domain.order.status.TransportationNote;

@Mapper(config = SpringMapperConfiguration.class)
public interface ATransportationNoteMapper {

    ATransportationNote toTransportationNote(TransportationNote transportationNote);
}
