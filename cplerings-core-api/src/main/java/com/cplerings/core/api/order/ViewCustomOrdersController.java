package com.cplerings.core.api.order;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.metal.request.ViewMetalSpecificationRequest;
import com.cplerings.core.api.metal.response.ViewMetalSpecificationResponse;
import com.cplerings.core.api.order.data.CustomOrdersData;
import com.cplerings.core.api.order.mapper.APIViewCustomOrdersMapper;
import com.cplerings.core.api.order.request.ViewCustomOrdersRequest;
import com.cplerings.core.api.order.response.ViewCustomOrdersResponse;
import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.api.shared.openapi.MetalSpecificationTag;
import com.cplerings.core.api.shared.openapi.OrderTag;
import com.cplerings.core.application.order.ViewCustomOrdersUseCase;
import com.cplerings.core.application.order.input.ViewCustomOrdersInput;
import com.cplerings.core.application.order.output.ViewCustomOrdersOutput;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.api.APIConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ViewCustomOrdersController extends AbstractController<ViewCustomOrdersInput, ViewCustomOrdersOutput, CustomOrdersData, ViewCustomOrdersRequest, ViewCustomOrdersResponse> {

    private final APIViewCustomOrdersMapper apiViewCustomOrdersMapper;
    private final ViewCustomOrdersUseCase viewCustomOrdersUseCase;

    @Override
    protected UseCase<ViewCustomOrdersInput, ViewCustomOrdersOutput> getUseCase() {
        return viewCustomOrdersUseCase;
    }

    @Override
    protected APIMapper<ViewCustomOrdersInput, ViewCustomOrdersOutput, CustomOrdersData, ViewCustomOrdersRequest, ViewCustomOrdersResponse> getMapper() {
        return apiViewCustomOrdersMapper;
    }

    @GetMapping(APIConstant.CUSTOM_ORDERS_PATH)
    @OrderTag
    @Operation(summary = "View custom orders")
    @ApiResponse(
            description = "Custom orders information",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = ViewCustomOrdersResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> view(ViewCustomOrdersRequest request) {
        return handleRequest(request);
    }
}
