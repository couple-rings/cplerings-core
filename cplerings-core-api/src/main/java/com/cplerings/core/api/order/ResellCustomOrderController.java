package com.cplerings.core.api.order;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.order.mapper.APIResellCustomOrderMapper;
import com.cplerings.core.api.order.request.ResellCustomOrderRequest;
import com.cplerings.core.api.order.request.data.ResellCustomOrderRequestData;
import com.cplerings.core.api.order.response.ResellCustomOrderResponse;
import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.api.shared.openapi.JewelryTag;
import com.cplerings.core.api.shared.openapi.OrderTag;
import com.cplerings.core.application.order.ResellCustomOrderUseCase;
import com.cplerings.core.application.order.input.ResellCustomOrderInput;
import com.cplerings.core.application.order.output.ResellCustomOrderOutput;
import com.cplerings.core.application.shared.entity.order.AResellOrder;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.api.APIConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ResellCustomOrderController extends AbstractController<ResellCustomOrderInput, ResellCustomOrderOutput, AResellOrder, ResellCustomOrderRequest, ResellCustomOrderResponse> {

    private final APIResellCustomOrderMapper mapper;
    private final ResellCustomOrderUseCase useCase;

    @Override
    protected UseCase<ResellCustomOrderInput, ResellCustomOrderOutput> getUseCase() {
        return useCase;
    }

    @Override
    protected APIMapper<ResellCustomOrderInput, ResellCustomOrderOutput, AResellOrder, ResellCustomOrderRequest, ResellCustomOrderResponse> getMapper() {
        return mapper;
    }

    @PostMapping(APIConstant.RESELL_CUSTOM_ORDER_PATH)
    @OrderTag
    @Operation(summary = "Resell custom order")
    @ApiResponse(
            description = "custom order information",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = ResellCustomOrderResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> create(@PathVariable("customOrderId") Long customOrderId,
                                         @RequestBody ResellCustomOrderRequestData resellCustomOrderRequestData) {
        ResellCustomOrderRequest request = ResellCustomOrderRequest.builder()
                .customOrderId(customOrderId)
                .note(resellCustomOrderRequestData.getNote())
                .customerId(resellCustomOrderRequestData.getCustomerId())
                .proofImageId(resellCustomOrderRequestData.getProofImageId())
                .paymentMethod(resellCustomOrderRequestData.getPaymentMethod())
                .build();
        return handleRequest(request);
    }
}
