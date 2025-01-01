package com.cplerings.core.api.dashboard;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.dashboard.data.ViewRefundOrdersWithDateData;
import com.cplerings.core.api.dashboard.mapper.APIViewRefundOrdersWithDateMapper;
import com.cplerings.core.api.dashboard.request.ViewRefundOrdersWithDateRequest;
import com.cplerings.core.api.dashboard.response.ViewRefundOrdersWithDateResponse;
import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.DashboardTag;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.application.dashboard.ViewRefundOrdersWithDateUseCase;
import com.cplerings.core.application.dashboard.input.ViewRefundOrdersWithDateInput;
import com.cplerings.core.application.dashboard.output.ViewRefundOrdersWithDateOutput;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.api.APIConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ViewRefundOrdersWithDateController extends AbstractController<ViewRefundOrdersWithDateInput, ViewRefundOrdersWithDateOutput, ViewRefundOrdersWithDateData, ViewRefundOrdersWithDateRequest, ViewRefundOrdersWithDateResponse> {

    private final ViewRefundOrdersWithDateUseCase useCase;
    private final APIViewRefundOrdersWithDateMapper apiViewRefundOrdersWithDateMapper;

    @Override
    protected UseCase<ViewRefundOrdersWithDateInput, ViewRefundOrdersWithDateOutput> getUseCase() {
        return useCase;
    }

    @Override
    protected APIMapper<ViewRefundOrdersWithDateInput, ViewRefundOrdersWithDateOutput, ViewRefundOrdersWithDateData, ViewRefundOrdersWithDateRequest, ViewRefundOrdersWithDateResponse> getMapper() {
        return apiViewRefundOrdersWithDateMapper;
    }

    @GetMapping(APIConstant.REFUND_ORDERS_STATISTIC_PAGINATION_PATH)
    @DashboardTag
    @Operation(summary = "View refund orders")
    @ApiResponse(
            description = "The refund orders",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = ViewRefundOrdersWithDateResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> view(ViewRefundOrdersWithDateRequest request) {
        return handleRequest(request);
    }
}
