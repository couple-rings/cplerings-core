package com.cplerings.core.api.transport.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.api.shared.mapper.APIPaginatedMapper;
import com.cplerings.core.api.transport.data.TransportationNotesData;
import com.cplerings.core.api.transport.request.ViewTransportationNotesRequest;
import com.cplerings.core.api.transport.response.ViewTransportationNotesResponse;
import com.cplerings.core.application.shared.entity.transport.ATransportationNote;
import com.cplerings.core.application.transport.input.ViewTransportationNotesInput;
import com.cplerings.core.application.transport.output.ViewTransportationNotesOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIViewTransportationNotesMapper extends APIPaginatedMapper<ViewTransportationNotesInput, ViewTransportationNotesOutput, TransportationNotesData, ATransportationNote, ViewTransportationNotesRequest, ViewTransportationNotesResponse> {
}
