package com.cplerings.core.api.order;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.order.data.StandardOrderData;
import com.cplerings.core.api.order.mapper.APICreateStandardOrderMapper;
import com.cplerings.core.api.order.request.CreateStandardOrderRequest;
import com.cplerings.core.api.order.response.CreateStandardOrderResponse;
import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.api.shared.openapi.OrderTag;
import com.cplerings.core.application.order.CreateStandardOrderUseCase;
import com.cplerings.core.application.order.input.CreateStandardOrderInput;
import com.cplerings.core.application.order.output.CreateStandardOrderOutput;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.api.APIConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class CreateStandardOrderController extends AbstractController<CreateStandardOrderInput, CreateStandardOrderOutput, StandardOrderData, CreateStandardOrderRequest, CreateStandardOrderResponse> {

    private final CreateStandardOrderUseCase createStandardOrderUseCase;
    private final APICreateStandardOrderMapper apiCreateStandardOrderMapper;

    @Override
    protected UseCase<CreateStandardOrderInput, CreateStandardOrderOutput> getUseCase() {
        return createStandardOrderUseCase;
    }

    @Override
    protected APIMapper<CreateStandardOrderInput, CreateStandardOrderOutput, StandardOrderData, CreateStandardOrderRequest, CreateStandardOrderResponse> getMapper() {
        return apiCreateStandardOrderMapper;
    }

    @PostMapping(APIConstant.STANDARD_ORDER_PATH)
    @OrderTag
    @Operation(summary = "create standard order")
    @ApiResponse(
            description = "Standard Order information",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = CreateStandardOrderResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> create(@RequestBody CreateStandardOrderRequest request) {
        return handleRequest(request);
    }
}
