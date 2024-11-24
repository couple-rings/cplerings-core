package com.cplerings.core.api.transport.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.transport.data.TransportationOrder;
import com.cplerings.core.api.transport.request.UpdateImageDeliveryRequest;
import com.cplerings.core.api.transport.response.UpdateImageDeliveryResponse;
import com.cplerings.core.application.transport.input.UpdateImageDeliveryInput;
import com.cplerings.core.application.transport.output.UpdateImageDeliveryOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIUpdateImageDeliveryMapper extends APIMapper<UpdateImageDeliveryInput, UpdateImageDeliveryOutput, TransportationOrder, UpdateImageDeliveryRequest, UpdateImageDeliveryResponse> {
}
