package com.cplerings.core.api.order;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.order.data.CustomOrderData;
import com.cplerings.core.api.order.mapper.APIAssignJewelerToCustomOrderMapper;
import com.cplerings.core.api.order.request.AssignJewelerToCustomOrderRequest;
import com.cplerings.core.api.order.request.data.AssignJewelerToCustomOrderRequestData;
import com.cplerings.core.api.order.response.AssignJewelerToCustomOrderResponse;
import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.api.shared.openapi.OrderTag;
import com.cplerings.core.api.transport.request.AssignTransportOrderRequest;
import com.cplerings.core.api.transport.request.data.AssignTransportOrderRequestData;
import com.cplerings.core.api.transport.response.AssignTransportOrderResponse;
import com.cplerings.core.application.order.AssignJewelerToCustomOrderUseCase;
import com.cplerings.core.application.order.input.AssignJewelerToCustomOrderInput;
import com.cplerings.core.application.order.output.AssignJewelerToCustomOrderOutput;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.api.APIConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class AssignJewelerToCustomOrderController extends AbstractController<AssignJewelerToCustomOrderInput, AssignJewelerToCustomOrderOutput, CustomOrderData, AssignJewelerToCustomOrderRequest, AssignJewelerToCustomOrderResponse> {

    private final AssignJewelerToCustomOrderUseCase assignJewelerToCustomOrderUseCase;
    private final APIAssignJewelerToCustomOrderMapper apiAssignJewelerToCustomOrderMapper;

    @Override
    protected UseCase<AssignJewelerToCustomOrderInput, AssignJewelerToCustomOrderOutput> getUseCase() {
        return assignJewelerToCustomOrderUseCase;
    }

    @Override
    protected APIMapper<AssignJewelerToCustomOrderInput, AssignJewelerToCustomOrderOutput, CustomOrderData, AssignJewelerToCustomOrderRequest, AssignJewelerToCustomOrderResponse> getMapper() {
        return apiAssignJewelerToCustomOrderMapper;
    }

    @PostMapping(APIConstant.ASSIGN_CUSTOM_ORDER_TO_JEWELER_PATH)
    @OrderTag
    @Operation(summary = "Assign order to jeweler")
    @ApiResponse(
            description = "Custom Order information",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = AssignJewelerToCustomOrderResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> assign(@PathVariable("customOrderId") Long customOrderId, @RequestBody AssignJewelerToCustomOrderRequestData requestData) {
        AssignJewelerToCustomOrderRequest request = AssignJewelerToCustomOrderRequest.builder()
                .customOrderId(customOrderId)
                .jewelerId(requestData.jewelerId())
                .build();
        return handleRequest(request);
    }
}
