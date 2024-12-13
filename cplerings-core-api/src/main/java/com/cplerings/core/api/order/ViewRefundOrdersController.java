package com.cplerings.core.api.order;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.order.data.RefundsData;
import com.cplerings.core.api.order.mapper.APIViewRefundOrdersMapper;
import com.cplerings.core.api.order.request.ViewRefundOrdersRequest;
import com.cplerings.core.api.order.response.ViewRefundOrdersResponse;
import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.api.shared.openapi.OrderTag;
import com.cplerings.core.application.order.ViewRefundOrdersUseCase;
import com.cplerings.core.application.order.input.ViewRefundOrdersInput;
import com.cplerings.core.application.order.output.ViewRefundOrdersOutput;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.api.APIConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ViewRefundOrdersController extends AbstractController<ViewRefundOrdersInput, ViewRefundOrdersOutput, RefundsData, ViewRefundOrdersRequest, ViewRefundOrdersResponse> {

    private final APIViewRefundOrdersMapper apiViewRefundOrdersMapper;
    private final ViewRefundOrdersUseCase viewRefundOrdersUseCase;

    @Override
    protected UseCase<ViewRefundOrdersInput, ViewRefundOrdersOutput> getUseCase() {
        return viewRefundOrdersUseCase;
    }

    @Override
    protected APIMapper<ViewRefundOrdersInput, ViewRefundOrdersOutput, RefundsData, ViewRefundOrdersRequest, ViewRefundOrdersResponse> getMapper() {
        return apiViewRefundOrdersMapper;
    }

    @GetMapping(APIConstant.REFUNDS_PATH)
    @OrderTag
    @Operation(summary = "View refund orders")
    @ApiResponse(
            description = "Refund orders information",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = ViewRefundOrdersResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> view(ViewRefundOrdersRequest request) {
        return handleRequest(request);
    }
}
