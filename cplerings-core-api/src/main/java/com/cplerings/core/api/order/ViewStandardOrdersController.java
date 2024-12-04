package com.cplerings.core.api.order;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.order.data.StandardOrdersData;
import com.cplerings.core.api.order.mapper.APIViewStandardOrdersMapper;
import com.cplerings.core.api.order.request.ViewStandardOrdersRequest;
import com.cplerings.core.api.order.response.ViewStandardOrdersResponse;
import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.api.shared.openapi.OrderTag;
import com.cplerings.core.application.order.ViewStandardOrdersUseCase;
import com.cplerings.core.application.order.input.ViewStandardOrdersInput;
import com.cplerings.core.application.order.output.ViewStandardOrdersOutput;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.api.APIConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ViewStandardOrdersController extends AbstractController<ViewStandardOrdersInput, ViewStandardOrdersOutput, StandardOrdersData, ViewStandardOrdersRequest, ViewStandardOrdersResponse> {

    private final APIViewStandardOrdersMapper apiViewStandardOrdersMapper;
    private final ViewStandardOrdersUseCase viewStandardOrdersUseCase;


    @Override
    protected UseCase<ViewStandardOrdersInput, ViewStandardOrdersOutput> getUseCase() {
        return viewStandardOrdersUseCase;
    }

    @Override
    protected APIMapper<ViewStandardOrdersInput, ViewStandardOrdersOutput, StandardOrdersData, ViewStandardOrdersRequest, ViewStandardOrdersResponse> getMapper() {
        return apiViewStandardOrdersMapper;
    }

    @GetMapping(APIConstant.STANDARD_ORDER_PATH)
    @OrderTag
    @Operation(summary = "View standard orders")
    @ApiResponse(
            description = "Standard orders information",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = ViewStandardOrdersResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> view(ViewStandardOrdersRequest request) {
        return handleRequest(request);
    }
}
