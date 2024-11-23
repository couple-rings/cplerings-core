package com.cplerings.core.api.transport;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.api.shared.openapi.OrderTag;
import com.cplerings.core.api.transport.mapper.APIGetTransportationOrderByCustomOrderMapper;
import com.cplerings.core.api.transport.request.GetTransportationOrderByCustomOrderRequest;
import com.cplerings.core.api.transport.response.GetTransportationOrderByCustomOrderResponse;
import com.cplerings.core.application.shared.entity.order.ATransportationOrder;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.application.transport.GetTransportationOrderByCustomOrderUseCase;
import com.cplerings.core.application.transport.input.GetTransportationOrderByCustomOrderInput;
import com.cplerings.core.application.transport.output.GetTransportationOrderByCustomOrderOutput;
import com.cplerings.core.common.api.APIConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class GetTransportationOrderByCustomOrderController extends AbstractController<GetTransportationOrderByCustomOrderInput, GetTransportationOrderByCustomOrderOutput, ATransportationOrder, GetTransportationOrderByCustomOrderRequest, GetTransportationOrderByCustomOrderResponse> {

    private final APIGetTransportationOrderByCustomOrderMapper apiGetTransportationOrderByCustomOrderMapper;
    private final GetTransportationOrderByCustomOrderUseCase getTransportationOrderByCustomOrderUseCase;

    @Override
    protected UseCase<GetTransportationOrderByCustomOrderInput, GetTransportationOrderByCustomOrderOutput> getUseCase() {
        return getTransportationOrderByCustomOrderUseCase;
    }

    @Override
    protected APIMapper<GetTransportationOrderByCustomOrderInput, GetTransportationOrderByCustomOrderOutput, ATransportationOrder, GetTransportationOrderByCustomOrderRequest, GetTransportationOrderByCustomOrderResponse> getMapper() {
        return apiGetTransportationOrderByCustomOrderMapper;
    }

    @GetMapping(APIConstant.VIEW_TRANSPORTATION_ORDER_BY_CUSTOM_ORDER_ID)
    @OrderTag
    @Operation(summary = "Get Transportation Order by custom order")
    @ApiResponse(
            description = "Transportation Order information",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = GetTransportationOrderByCustomOrderResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> view(@PathVariable("customOrderId") Long customOrderId) {
        GetTransportationOrderByCustomOrderRequest request = GetTransportationOrderByCustomOrderRequest.builder().customOrderId(customOrderId).build();
        return handleRequest(request);
    }
}
