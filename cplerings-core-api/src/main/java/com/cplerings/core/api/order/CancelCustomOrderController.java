package com.cplerings.core.api.order;

import com.cplerings.core.api.order.mapper.APICancelCustomOrderMapper;
import com.cplerings.core.api.order.request.CancelCustomOrderRequest;
import com.cplerings.core.api.shared.AbstractDataController;
import com.cplerings.core.api.shared.NoData;
import com.cplerings.core.api.shared.NoResponse;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.api.shared.openapi.OrderTag;
import com.cplerings.core.application.order.CancelCustomOrderUseCase;
import com.cplerings.core.application.order.input.CancelCustomOrderInput;
import com.cplerings.core.application.shared.output.NoOutput;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.api.APIConstant;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequiredArgsConstructor
public class CancelCustomOrderController extends AbstractDataController<CancelCustomOrderInput, NoOutput, NoData, CancelCustomOrderRequest, NoResponse> {

    private final CancelCustomOrderUseCase cancelCustomOrderUseCase;
    private final APICancelCustomOrderMapper apiCancelCustomOrderMapper;

    @Override
    protected UseCase<CancelCustomOrderInput, NoOutput> getUseCase() {
        return cancelCustomOrderUseCase;
    }

    @Override
    protected APIMapper<CancelCustomOrderInput, NoOutput, NoData, CancelCustomOrderRequest, NoResponse> getMapper() {
        return apiCancelCustomOrderMapper;
    }

    @DeleteMapping(APIConstant.CANCEL_CUSTOM_ORDER_PATH)
    @OrderTag
    @Operation(summary = "Cancel custom order")
    @ApiResponse(
            description = "No response",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = NoResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> cancel(@PathVariable("customOrderId") Long customOrderId) {
        CancelCustomOrderRequest request = CancelCustomOrderRequest.builder()
                .customOrderId(customOrderId)
                .build();
        return handleRequest(request);
    }
}
