package com.cplerings.core.api.transport.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.transport.request.CreateTransportationNoteRequest;
import com.cplerings.core.api.transport.response.CreateTransportationNoteResponse;
import com.cplerings.core.application.shared.entity.transport.ATransportationNote;
import com.cplerings.core.application.shared.mapper.ATransportationNoteMapper;
import com.cplerings.core.application.transport.input.CreateTransportationNoteInput;
import com.cplerings.core.application.transport.output.CreateTransportationNoteOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class,
        uses = {
                ATransportationNoteMapper.class
        })
public interface APICreateTransportationNoteMapper extends APIMapper<CreateTransportationNoteInput, CreateTransportationNoteOutput, ATransportationNote, CreateTransportationNoteRequest, CreateTransportationNoteResponse> {

        @Mapping(target = ".", source = "transportationNote")
        ATransportationNote toData(CreateTransportationNoteOutput output);
}
