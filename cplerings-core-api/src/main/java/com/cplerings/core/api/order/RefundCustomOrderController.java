package com.cplerings.core.api.order;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.order.mapper.APIRefundCustomOrderMapper;
import com.cplerings.core.api.order.request.RefundCustomOrderRequest;
import com.cplerings.core.api.order.response.RefundCustomOrderResponse;
import com.cplerings.core.api.shared.AbstractDataController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.api.shared.openapi.OrderTag;
import com.cplerings.core.application.order.RefundCustomOrderUseCase;
import com.cplerings.core.application.order.input.RefundCustomOrderInput;
import com.cplerings.core.application.order.output.RefundCustomOrderOutput;
import com.cplerings.core.application.shared.entity.order.ARefund;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.api.APIConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class RefundCustomOrderController extends AbstractDataController<RefundCustomOrderInput, RefundCustomOrderOutput, ARefund, RefundCustomOrderRequest, RefundCustomOrderResponse> {

    private final RefundCustomOrderUseCase refundCustomOrderUseCase;
    private final APIRefundCustomOrderMapper apiRefundCustomOrderMapper;

    @Override
    protected UseCase<RefundCustomOrderInput, RefundCustomOrderOutput> getUseCase() {
        return refundCustomOrderUseCase;
    }

    @Override
    protected APIMapper<RefundCustomOrderInput, RefundCustomOrderOutput, ARefund, RefundCustomOrderRequest, RefundCustomOrderResponse> getMapper() {
        return apiRefundCustomOrderMapper;
    }

    @PostMapping(APIConstant.REFUND_CUSTOM_ORDER_PATH)
    @OrderTag
    @Operation(summary = "Refund custom order")
    @ApiResponse(
            description = "Custom order information",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = RefundCustomOrderResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> refund(@PathVariable("customOrderId") Long customOrderId,
                                         @RequestBody RefundCustomOrderRequest request) {
        request.setCustomOrderId(customOrderId);
        return handleRequest(request);
    }
}
