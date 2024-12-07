package com.cplerings.core.api.order;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.order.data.RefundData;
import com.cplerings.core.api.order.mapper.APIRefundStandardOrderMapper;
import com.cplerings.core.api.order.request.RefundStandardOrderRequest;
import com.cplerings.core.api.order.request.data.RefundStandardOrderRequestData;
import com.cplerings.core.api.order.response.RefundStandardOrderResponse;
import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.api.shared.openapi.OrderTag;
import com.cplerings.core.application.order.RefundStandardOrderUseCase;
import com.cplerings.core.application.order.input.RefundStandardOrderInput;
import com.cplerings.core.application.order.output.RefundStandardOrderOutput;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.api.APIConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class RefundStandardOrderController extends AbstractController<RefundStandardOrderInput, RefundStandardOrderOutput, RefundData, RefundStandardOrderRequest, RefundStandardOrderResponse> {

    private final APIRefundStandardOrderMapper apiRefundStandardOrderMapper;
    private final RefundStandardOrderUseCase refundStandardOrderUseCase;

    @Override
    protected UseCase<RefundStandardOrderInput, RefundStandardOrderOutput> getUseCase() {
        return refundStandardOrderUseCase;
    }

    @Override
    protected APIMapper<RefundStandardOrderInput, RefundStandardOrderOutput, RefundData, RefundStandardOrderRequest, RefundStandardOrderResponse> getMapper() {
        return apiRefundStandardOrderMapper;
    }

    @PostMapping(APIConstant.REFUND_STANDARD_ORDER_PATH)
    @OrderTag
    @Operation(summary = "refund standard order")
    @ApiResponse(
            description = "Standard order information",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = RefundStandardOrderResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> refund(@PathVariable("standardOrderId") Long standardOrderId, @RequestBody RefundStandardOrderRequestData refundStandardOrderRequestData) {
        RefundStandardOrderRequest request = RefundStandardOrderRequest.builder()
                .standardOrderId(standardOrderId)
                .refundStandardOrderRequestData(refundStandardOrderRequestData)
                .build();
        return handleRequest(request);
    }
}
