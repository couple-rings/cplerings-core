package com.cplerings.core.api.order;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.order.mapper.APIViewResellOrderMapper;
import com.cplerings.core.api.order.request.ViewResellOrderRequest;
import com.cplerings.core.api.order.request.ViewStandardOrderRequest;
import com.cplerings.core.api.order.response.ViewResellOrderResponse;
import com.cplerings.core.api.order.response.ViewStandardOrderResponse;
import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.api.shared.openapi.OrderTag;
import com.cplerings.core.application.order.ViewResellOrderUseCase;
import com.cplerings.core.application.order.input.ViewResellOrderInput;
import com.cplerings.core.application.order.output.ViewResellOrderOutput;
import com.cplerings.core.application.shared.entity.order.AResellOrder;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.api.APIConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ViewResellOrderController extends AbstractController<ViewResellOrderInput, ViewResellOrderOutput, AResellOrder, ViewResellOrderRequest, ViewResellOrderResponse> {

    private final ViewResellOrderUseCase useCase;
    private final APIViewResellOrderMapper viewResellOrderMapper;

    @Override
    protected UseCase<ViewResellOrderInput, ViewResellOrderOutput> getUseCase() {
        return useCase;
    }

    @Override
    protected APIMapper<ViewResellOrderInput, ViewResellOrderOutput, AResellOrder, ViewResellOrderRequest, ViewResellOrderResponse> getMapper() {
        return viewResellOrderMapper;
    }

    @GetMapping(APIConstant.RESELL_ORDER_PATH)
    @OrderTag
    @Operation(summary = "View resell order")
    @ApiResponse(
            description = "resell order information",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = ViewStandardOrderResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> view(@PathVariable("resellOrderId") Long resellOrderId) {
        ViewResellOrderRequest request = ViewResellOrderRequest.builder().resellOrderId(resellOrderId).build();
        return handleRequest(request);
    }
}
