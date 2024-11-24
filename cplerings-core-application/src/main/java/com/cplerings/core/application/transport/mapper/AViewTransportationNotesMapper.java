package com.cplerings.core.application.transport.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cplerings.core.application.transport.datasource.result.TransportationNotes;
import com.cplerings.core.application.transport.output.ViewTransportationNotesOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface AViewTransportationNotesMapper {

    @Mapping(target = "items", source = "transportationNotes")
    ViewTransportationNotesOutput toOutput(TransportationNotes transportationNotes);
}
