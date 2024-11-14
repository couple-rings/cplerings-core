package com.cplerings.core.api.transport;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.api.shared.openapi.OrderTag;
import com.cplerings.core.api.transport.data.TransportationOrderList;
import com.cplerings.core.api.transport.mapper.APIUpdateTransportationOrdersToOngoingMapper;
import com.cplerings.core.api.transport.request.UpdateTransportationOrdersToOngoingRequest;
import com.cplerings.core.api.transport.response.UpdateTransportationOrdersToOngoingResponse;
import com.cplerings.core.application.shared.entity.order.ATransportationOrder;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.application.transport.UpdateTransportationOrdersToOngoingUseCase;
import com.cplerings.core.application.transport.input.UpdateTransportationOrdersToOngoingInput;
import com.cplerings.core.application.transport.output.UpdateTransportationOrdersToOngoingOutput;
import com.cplerings.core.common.api.APIConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UpdateTransportationOrdersToOngoingControllers extends AbstractController<UpdateTransportationOrdersToOngoingInput, UpdateTransportationOrdersToOngoingOutput, TransportationOrderList, UpdateTransportationOrdersToOngoingRequest, UpdateTransportationOrdersToOngoingResponse> {

    private final APIUpdateTransportationOrdersToOngoingMapper apiUpdateTransportationOrdersToOngoingMapper;
    private final UpdateTransportationOrdersToOngoingUseCase updateTransportationOrdersToOngoingUseCase;

    @Override
    protected UseCase<UpdateTransportationOrdersToOngoingInput, UpdateTransportationOrdersToOngoingOutput> getUseCase() {
        return updateTransportationOrdersToOngoingUseCase;
    }

    @Override
    protected APIMapper<UpdateTransportationOrdersToOngoingInput, UpdateTransportationOrdersToOngoingOutput, TransportationOrderList, UpdateTransportationOrdersToOngoingRequest, UpdateTransportationOrdersToOngoingResponse> getMapper() {
        return apiUpdateTransportationOrdersToOngoingMapper;
    }

    @PutMapping(APIConstant.UPDATE_TRANSPORTATION_ORDER_TO_ONGOING_PATH)
    @OrderTag
    @Operation(summary = "Update orders to ongoing")
    @ApiResponse(
            description = "Transportation Orders information",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = UpdateTransportationOrdersToOngoingResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> update(@RequestBody UpdateTransportationOrdersToOngoingRequest request) {
        return handleRequest(request);
    }
}
