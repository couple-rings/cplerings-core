package com.cplerings.core.api.order;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.order.data.CustomOrderData;
import com.cplerings.core.api.order.mapper.APIViewCustomOrderMapper;
import com.cplerings.core.api.order.request.ViewCustomOrderRequest;
import com.cplerings.core.api.order.request.ViewCustomOrdersRequest;
import com.cplerings.core.api.order.response.ViewCustomOrderResponse;
import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.api.shared.openapi.OrderTag;
import com.cplerings.core.application.order.ViewCustomOrderUseCase;
import com.cplerings.core.application.order.input.ViewCustomOrderInput;
import com.cplerings.core.application.order.output.ViewCustomOrderOutput;
import com.cplerings.core.application.shared.entity.order.ACustomOrder;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.api.APIConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ViewCustomOrderController extends AbstractController<ViewCustomOrderInput, ViewCustomOrderOutput, CustomOrderData, ViewCustomOrderRequest, ViewCustomOrderResponse> {

    private final ViewCustomOrderUseCase viewCustomOrderUseCase;
    private final APIViewCustomOrderMapper apiViewCustomOrderMapper;

    @Override

    protected UseCase<ViewCustomOrderInput, ViewCustomOrderOutput> getUseCase() {
        return viewCustomOrderUseCase;
    }

    @Override
    protected APIMapper<ViewCustomOrderInput, ViewCustomOrderOutput, CustomOrderData, ViewCustomOrderRequest, ViewCustomOrderResponse> getMapper() {
        return apiViewCustomOrderMapper;
    }

    @GetMapping(APIConstant.VIEW_A_CUSTOM_ORDER_PATH)
    @OrderTag
    @Operation(summary = "View custom order")
    @ApiResponse(
            description = "Custom order information",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = ViewCustomOrderResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> view(@PathVariable("customOrderId") Long customOrderId) {
        ViewCustomOrderRequest request = ViewCustomOrderRequest.builder().customOrderId(customOrderId).build();
        return handleRequest(request);
    }
}
