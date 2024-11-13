package com.cplerings.core.api.transport;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.api.shared.openapi.OrderTag;
import com.cplerings.core.api.shared.openapi.PaymentTag;
import com.cplerings.core.api.transport.data.TransportationOrder;
import com.cplerings.core.api.transport.mapper.APIAssignTransportationOrderMapper;
import com.cplerings.core.api.transport.request.AssignTransportOrderRequest;
import com.cplerings.core.api.transport.request.data.AssignTransportOrderRequestData;
import com.cplerings.core.api.transport.response.AssignTransportOrderResponse;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.application.transport.AssignTransportOrderUseCase;
import com.cplerings.core.application.transport.input.AssignTransportOrderInput;
import com.cplerings.core.application.transport.output.AssignTransportOrderOutput;
import com.cplerings.core.common.api.APIConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class AssignTransportOrderController extends AbstractController<AssignTransportOrderInput, AssignTransportOrderOutput, TransportationOrder, AssignTransportOrderRequest, AssignTransportOrderResponse> {

    private final AssignTransportOrderUseCase assignTransportOrderUseCase;
    private final APIAssignTransportationOrderMapper apiAssignTransportationOrderMapper;

    @Override
    protected UseCase<AssignTransportOrderInput, AssignTransportOrderOutput> getUseCase() {
        return assignTransportOrderUseCase;
    }

    @Override
    protected APIMapper<AssignTransportOrderInput, AssignTransportOrderOutput, TransportationOrder, AssignTransportOrderRequest, AssignTransportOrderResponse> getMapper() {
        return apiAssignTransportationOrderMapper;
    }

    @PostMapping(APIConstant.ASSIGN_TRANSPORTATION_ORDER_PATH)
    @OrderTag
    @Operation(summary = "Assign order to transporter")
    @ApiResponse(
            description = "Transportation Order information",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = AssignTransportOrderResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> assign(@PathVariable("transportationOrderId") Long transportationOrderId, @RequestBody AssignTransportOrderRequestData requestData) {
        AssignTransportOrderRequest request = AssignTransportOrderRequest.builder()
                .transportOrderId(transportationOrderId)
                .transporterId(requestData.transporterId())
                .build();
        return handleRequest(request);
    }
}
