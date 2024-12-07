package com.cplerings.core.api.order;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.order.mapper.APICompleteOrderMapper;
import com.cplerings.core.api.order.request.CompleteOrderRequest;
import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.NoData;
import com.cplerings.core.api.shared.NoResponse;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.api.shared.openapi.OrderTag;
import com.cplerings.core.application.order.CompleteOrderUseCase;
import com.cplerings.core.application.order.input.CompleteOrderInput;
import com.cplerings.core.application.shared.output.NoOutput;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.api.APIConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class CompleteOrderController extends AbstractController<CompleteOrderInput, NoOutput, NoData, CompleteOrderRequest, NoResponse> {

    private final APICompleteOrderMapper apiCompleteOrderMapper;
    private final CompleteOrderUseCase completeOrderUseCase;

    @Override
    protected UseCase<CompleteOrderInput, NoOutput> getUseCase() {
        return completeOrderUseCase;
    }

    @Override
    protected APIMapper<CompleteOrderInput, NoOutput, NoData, CompleteOrderRequest, NoResponse> getMapper() {
        return apiCompleteOrderMapper;
    }

    @PutMapping(APIConstant.COMPLETE_ORDER_PATH)
    @OrderTag
    @Operation(summary = "Complete order")
    @ApiResponse(
            description = "no response",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = NoResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> cancel(@RequestBody CompleteOrderRequest request) {
        return handleRequest(request);
    }
}
