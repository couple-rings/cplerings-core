package com.cplerings.core.api.transport;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.api.shared.openapi.OrderTag;
import com.cplerings.core.api.transport.data.TransportationOrdersData;
import com.cplerings.core.api.transport.mapper.APIViewTransportationOrdersMapper;
import com.cplerings.core.api.transport.request.ViewTransportationOrdersRequest;
import com.cplerings.core.api.transport.response.ViewTransportationOrdersResponse;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.application.transport.ViewTransportationOrdersUseCase;
import com.cplerings.core.application.transport.input.ViewTransportationOrdersInput;
import com.cplerings.core.application.transport.output.ViewTransportationOrdersOutput;
import com.cplerings.core.common.api.APIConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ViewTransportationOrdersController extends AbstractController<ViewTransportationOrdersInput, ViewTransportationOrdersOutput, TransportationOrdersData, ViewTransportationOrdersRequest, ViewTransportationOrdersResponse> {

    private final APIViewTransportationOrdersMapper apiViewTransportationOrdersMapper;
    private final ViewTransportationOrdersUseCase viewTransportationOrdersUseCase;

    @Override
    protected UseCase<ViewTransportationOrdersInput, ViewTransportationOrdersOutput> getUseCase() {
        return viewTransportationOrdersUseCase;
    }

    @Override
    protected APIMapper<ViewTransportationOrdersInput, ViewTransportationOrdersOutput, TransportationOrdersData, ViewTransportationOrdersRequest, ViewTransportationOrdersResponse> getMapper() {
        return apiViewTransportationOrdersMapper;
    }

    @GetMapping(APIConstant.TRANSPORTATION_ORDER_PATH)
    @OrderTag
    @Operation(summary = "view transportation orders")
    @ApiResponse(
            description = "Transportation Orders information",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = ViewTransportationOrdersResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> update(ViewTransportationOrdersRequest request) {
        return handleRequest(request);
    }
}
