package com.cplerings.core.api.order;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.order.data.ResellOrdersData;
import com.cplerings.core.api.order.mapper.APIViewResellOrdersMapper;
import com.cplerings.core.api.order.request.ViewResellOrdersRequest;
import com.cplerings.core.api.order.response.ViewResellOrdersResponse;
import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.api.shared.openapi.OrderTag;
import com.cplerings.core.application.order.ViewResellOrdersUseCase;
import com.cplerings.core.application.order.input.ViewResellOrdersInput;
import com.cplerings.core.application.order.output.ViewResellOrdersOutput;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.api.APIConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ViewResellOrdersController extends AbstractController<ViewResellOrdersInput, ViewResellOrdersOutput, ResellOrdersData, ViewResellOrdersRequest, ViewResellOrdersResponse> {

    private final APIViewResellOrdersMapper apiViewResellOrdersMapper;
    private final ViewResellOrdersUseCase viewResellOrdersUseCase;

    @Override
    protected UseCase<ViewResellOrdersInput, ViewResellOrdersOutput> getUseCase() {
        return viewResellOrdersUseCase;
    }

    @Override
    protected APIMapper<ViewResellOrdersInput, ViewResellOrdersOutput, ResellOrdersData, ViewResellOrdersRequest, ViewResellOrdersResponse> getMapper() {
        return apiViewResellOrdersMapper;
    }

    @GetMapping(APIConstant.RESELL_ORDERS_PATH)
    @OrderTag
    @Operation(summary = "View resell orders")
    @ApiResponse(
            description = "Resell orders information",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = ViewResellOrdersResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> view(ViewResellOrdersRequest request) {
        return handleRequest(request);
    }
}
