package com.cplerings.core.api.order;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.order.data.StandardOrderData;
import com.cplerings.core.api.order.mapper.APIViewStandardOrderMapper;
import com.cplerings.core.api.order.request.ViewStandardOrderRequest;
import com.cplerings.core.api.order.response.ViewStandardOrderResponse;
import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.api.shared.openapi.OrderTag;
import com.cplerings.core.application.order.ViewStandardOrderUseCase;
import com.cplerings.core.application.order.input.ViewStandardOrderInput;
import com.cplerings.core.application.order.output.ViewStandardOrderOutput;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.api.APIConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ViewStandardOrderController extends AbstractController<ViewStandardOrderInput, ViewStandardOrderOutput, StandardOrderData, ViewStandardOrderRequest, ViewStandardOrderResponse> {

    private final APIViewStandardOrderMapper viewStandardOrderMapper;
    private final ViewStandardOrderUseCase viewStandardOrderUseCase;

    @Override
    protected UseCase<ViewStandardOrderInput, ViewStandardOrderOutput> getUseCase() {
        return viewStandardOrderUseCase;
    }

    @Override
    protected APIMapper<ViewStandardOrderInput, ViewStandardOrderOutput, StandardOrderData, ViewStandardOrderRequest, ViewStandardOrderResponse> getMapper() {
        return viewStandardOrderMapper;
    }

    @GetMapping(APIConstant.SINGLE_STANDARD_ORDER_PATH)
    @OrderTag
    @Operation(summary = "View standard order")
    @ApiResponse(
            description = "Standard order information",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = ViewStandardOrderResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> view(@PathVariable("standardOrderId") Long standardOrderId) {
        ViewStandardOrderRequest request = ViewStandardOrderRequest.builder().standardOrderId(standardOrderId).build();
        return handleRequest(request);
    }
}
