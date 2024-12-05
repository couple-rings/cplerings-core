package com.cplerings.core.api.order;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.order.data.StandardOrderPaymentLinkData;
import com.cplerings.core.api.order.mapper.APIPayStandardOrderMapper;
import com.cplerings.core.api.order.request.PayStandardOrderRequest;
import com.cplerings.core.api.order.response.PayStandardOrderResponse;
import com.cplerings.core.api.shared.AbstractDataController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.api.shared.openapi.OrderTag;
import com.cplerings.core.application.order.PayStandardOrderUseCase;
import com.cplerings.core.application.order.input.PayStandardOrderInput;
import com.cplerings.core.application.order.output.PayStandardOrderOutput;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.api.APIConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PayStandardOrderController extends AbstractDataController<PayStandardOrderInput, PayStandardOrderOutput, StandardOrderPaymentLinkData, PayStandardOrderRequest, PayStandardOrderResponse> {

    private final PayStandardOrderUseCase payStandardOrderUseCase;
    private final APIPayStandardOrderMapper apiPayStandardOrderMapper;

    @Override
    protected UseCase<PayStandardOrderInput, PayStandardOrderOutput> getUseCase() {
        return payStandardOrderUseCase;
    }

    @Override
    protected APIMapper<PayStandardOrderInput, PayStandardOrderOutput, StandardOrderPaymentLinkData, PayStandardOrderRequest, PayStandardOrderResponse> getMapper() {
        return apiPayStandardOrderMapper;
    }

    @PostMapping(APIConstant.PAY_STANDARD_ORDER_PATH)
    @OrderTag
    @Operation(summary = "Pay standard order")
    @ApiResponse(
            description = "Payment link",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = PayStandardOrderResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> view(@RequestBody PayStandardOrderRequest request) {
        return handleRequest(request);
    }
}
