package com.cplerings.core.api.transport;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.api.shared.openapi.OrderTag;
import com.cplerings.core.api.transport.data.TransportationOrder;
import com.cplerings.core.api.transport.mapper.APIViewTransportationOrderMapper;
import com.cplerings.core.api.transport.request.ViewTransportationOrderRequest;
import com.cplerings.core.api.transport.response.ViewTransportationOrderResponse;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.application.transport.ViewTransportationOrderUseCase;
import com.cplerings.core.application.transport.input.ViewTransportationOrderInput;
import com.cplerings.core.application.transport.output.ViewTransportationOrderOutput;
import com.cplerings.core.common.api.APIConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ViewTransportationOrderController extends AbstractController<ViewTransportationOrderInput, ViewTransportationOrderOutput, TransportationOrder, ViewTransportationOrderRequest, ViewTransportationOrderResponse> {

    private final APIViewTransportationOrderMapper apiViewTransportationOrderMapper;
    private final ViewTransportationOrderUseCase viewTransportationOrderUseCase;

    @Override
    protected UseCase<ViewTransportationOrderInput, ViewTransportationOrderOutput> getUseCase() {
        return viewTransportationOrderUseCase;
    }

    @Override
    protected APIMapper<ViewTransportationOrderInput, ViewTransportationOrderOutput, TransportationOrder, ViewTransportationOrderRequest, ViewTransportationOrderResponse> getMapper() {
        return apiViewTransportationOrderMapper;
    }

    @GetMapping(APIConstant.VIEW_TRANSPORTATION_ORDER_DETAIL)
    @OrderTag
    @Operation(summary = "Get Transportation Order detail")
    @ApiResponse(
            description = "Transportation Order information",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = ViewTransportationOrderResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> view(@PathVariable("transportationOrderId") Long transportationOrderId) {
        ViewTransportationOrderRequest request = ViewTransportationOrderRequest.builder().transportationOrderId(transportationOrderId).build();
        return handleRequest(request);
    }
}
