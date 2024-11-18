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
import com.cplerings.core.api.transport.request.data.UpdateTransportationOrderStatusRequestData;
import com.cplerings.core.api.transport.mapper.APIUpdateTransportationOrderStatusMapper;
import com.cplerings.core.api.transport.request.UpdateTransportationOrderStatusRequest;
import com.cplerings.core.api.transport.response.UpdateTransportationOrderStatusResponse;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.application.transport.UpdateTransportationOrderStatusUseCase;
import com.cplerings.core.application.transport.input.UpdateTransportationOrderStatusInput;
import com.cplerings.core.application.transport.output.UpdateTransportationOrderStatusOutput;
import com.cplerings.core.common.api.APIConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UpdateTransportationOrderStatusController extends AbstractController<UpdateTransportationOrderStatusInput, UpdateTransportationOrderStatusOutput, TransportationOrder, UpdateTransportationOrderStatusRequest, UpdateTransportationOrderStatusResponse> {

    private final UpdateTransportationOrderStatusUseCase updateTransportationOrderStatusUseCase;
    private final APIUpdateTransportationOrderStatusMapper apiUpdateTransportationOrderStatusMapper;

    @Override
    protected UseCase<UpdateTransportationOrderStatusInput, UpdateTransportationOrderStatusOutput> getUseCase() {
        return updateTransportationOrderStatusUseCase;
    }

    @Override
    protected APIMapper<UpdateTransportationOrderStatusInput, UpdateTransportationOrderStatusOutput, TransportationOrder, UpdateTransportationOrderStatusRequest, UpdateTransportationOrderStatusResponse> getMapper() {
        return apiUpdateTransportationOrderStatusMapper;
    }

    @PutMapping(APIConstant.UPDATE_TRANSPORTATION_ORDER_STATUS)
    @OrderTag
    @Operation(summary = "Update orders status")
    @ApiResponse(
            description = "Transportation Orders information",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = UpdateTransportationOrderStatusResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> update(@PathVariable("transportationOrderId") Long transportationOrderId, @RequestBody UpdateTransportationOrderStatusRequestData updateTransportationOrderStatusRequestData) {
        UpdateTransportationOrderStatusRequest request = UpdateTransportationOrderStatusRequest.builder()
                .status(updateTransportationOrderStatusRequestData.status())
                .transportationOrderId(transportationOrderId)
                .build();
        return handleRequest(request);
    }
}
