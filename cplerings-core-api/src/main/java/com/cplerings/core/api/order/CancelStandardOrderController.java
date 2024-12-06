package com.cplerings.core.api.order;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.order.data.StandardOrderData;
import com.cplerings.core.api.order.mapper.APICancelStandardOrderMapper;
import com.cplerings.core.api.order.request.CancelStandardOrderRequest;
import com.cplerings.core.api.order.response.CancelStandardOrderResponse;
import com.cplerings.core.api.order.response.ViewStandardOrderResponse;
import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.api.shared.openapi.OrderTag;
import com.cplerings.core.application.order.CancelStandardOrderUseCase;
import com.cplerings.core.application.order.input.CancelStandardOrderInput;
import com.cplerings.core.application.order.output.CancelStandardOrderOutput;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.api.APIConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class CancelStandardOrderController extends AbstractController<CancelStandardOrderInput, CancelStandardOrderOutput, StandardOrderData, CancelStandardOrderRequest, CancelStandardOrderResponse> {

    private final APICancelStandardOrderMapper apiCancelStandardOrderMapper;
    private final CancelStandardOrderUseCase cancelStandardOrderUseCase;

    @Override
    protected UseCase<CancelStandardOrderInput, CancelStandardOrderOutput> getUseCase() {
        return cancelStandardOrderUseCase;
    }

    @Override
    protected APIMapper<CancelStandardOrderInput, CancelStandardOrderOutput, StandardOrderData, CancelStandardOrderRequest, CancelStandardOrderResponse> getMapper() {
        return apiCancelStandardOrderMapper;
    }

    @PutMapping(APIConstant.CANCEL_STANDARD_ORDER_PATH)
    @OrderTag
    @Operation(summary = "Cancel standard order")
    @ApiResponse(
            description = "order information",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = ViewStandardOrderResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> cancel(@PathVariable("standardOrderId") Long standardOrderId) {
        CancelStandardOrderRequest request = CancelStandardOrderRequest.builder().standardOrderId(standardOrderId).build();
        return handleRequest(request);
    }
}
