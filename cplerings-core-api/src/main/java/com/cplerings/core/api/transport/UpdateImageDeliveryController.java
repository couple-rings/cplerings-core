package com.cplerings.core.api.transport;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.api.shared.openapi.OrderTag;
import com.cplerings.core.api.transport.data.TransportationOrder;
import com.cplerings.core.api.transport.mapper.APIUpdateImageDeliveryMapper;
import com.cplerings.core.api.transport.mapper.APIUpdateImageDeliveryMapperImpl;
import com.cplerings.core.api.transport.mapper.APIViewTransportationOrderMapper;
import com.cplerings.core.api.transport.request.UpdateImageDeliveryRequest;
import com.cplerings.core.api.transport.request.data.UpdateImageDeliveryRequestData;
import com.cplerings.core.api.transport.response.UpdateImageDeliveryResponse;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.application.transport.UpdateImageDeliveryUseCase;
import com.cplerings.core.application.transport.input.UpdateImageDeliveryInput;
import com.cplerings.core.application.transport.output.UpdateImageDeliveryOutput;
import com.cplerings.core.common.api.APIConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class UpdateImageDeliveryController extends AbstractController<UpdateImageDeliveryInput, UpdateImageDeliveryOutput, TransportationOrder, UpdateImageDeliveryRequest, UpdateImageDeliveryResponse> {

    private final UpdateImageDeliveryUseCase updateImageDeliveryUseCase;
    private final APIUpdateImageDeliveryMapper apiUpdateImageDeliveryMapper;

    @Override
    protected UseCase<UpdateImageDeliveryInput, UpdateImageDeliveryOutput> getUseCase() {
        return updateImageDeliveryUseCase;
    }

    @Override
    protected APIMapper<UpdateImageDeliveryInput, UpdateImageDeliveryOutput, TransportationOrder, UpdateImageDeliveryRequest, UpdateImageDeliveryResponse> getMapper() {
        return apiUpdateImageDeliveryMapper;
    }

    @PutMapping(APIConstant.UPDATE_TRANSPORTATION_DELIVERY_IMAGE_ORDER_PATH)
    @OrderTag
    @Operation(summary = "Update delivery image")
    @ApiResponse(
            description = "Transportation Orders information",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = UpdateImageDeliveryResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> update(@PathVariable("transportationOrderId") Long transportationOrderId, @RequestBody UpdateImageDeliveryRequestData updateImageDeliveryRequestData) {
        UpdateImageDeliveryRequest request = UpdateImageDeliveryRequest.builder()
                .imageId(updateImageDeliveryRequestData.imageId())
                .transportationOrderId(transportationOrderId)
                .build();
        return handleRequest(request);
    }
}
