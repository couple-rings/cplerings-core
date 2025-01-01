package com.cplerings.core.api.order;

import com.cplerings.core.api.order.data.PaymentInfosData;
import com.cplerings.core.api.order.mapper.APIViewCustomOrderPaymentsMapper;
import com.cplerings.core.api.order.request.ViewCustomOrderPaymentsRequest;
import com.cplerings.core.api.order.response.ViewCustomOrderPaymentsResponse;
import com.cplerings.core.api.shared.AbstractDataController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.api.shared.openapi.OrderTag;
import com.cplerings.core.application.order.ViewCustomOrderPaymentsUseCase;
import com.cplerings.core.application.order.input.ViewCustomOrderPaymentsInput;
import com.cplerings.core.application.order.output.ViewCustomOrderPaymentsOutput;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.api.APIConstant;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequiredArgsConstructor
public class ViewCustomOrderPaymentsController extends AbstractDataController<ViewCustomOrderPaymentsInput, ViewCustomOrderPaymentsOutput, PaymentInfosData, ViewCustomOrderPaymentsRequest, ViewCustomOrderPaymentsResponse> {

    private final ViewCustomOrderPaymentsUseCase viewCustomOrderPaymentsUseCase;
    private final APIViewCustomOrderPaymentsMapper apiViewCustomOrderPaymentsMapper;

    @Override
    protected UseCase<ViewCustomOrderPaymentsInput, ViewCustomOrderPaymentsOutput> getUseCase() {
        return viewCustomOrderPaymentsUseCase;
    }

    @Override
    protected APIMapper<ViewCustomOrderPaymentsInput, ViewCustomOrderPaymentsOutput, PaymentInfosData, ViewCustomOrderPaymentsRequest, ViewCustomOrderPaymentsResponse> getMapper() {
        return apiViewCustomOrderPaymentsMapper;
    }

    @GetMapping(APIConstant.CUSTOM_ORDER_PAYMENTS_PATH)
    @OrderTag
    @Operation(summary = "View custom order's payments")
    @ApiResponse(
            description = "Custom order's payments",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = ViewCustomOrderPaymentsResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> view(@PathVariable("customOrderId") Long customOrderId) {
        final ViewCustomOrderPaymentsRequest request = ViewCustomOrderPaymentsRequest.builder()
                .customOrderId(customOrderId)
                .build();
        return handleRequest(request);
    }
}
