package com.cplerings.core.api.dashboard;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.dashboard.data.ViewBranchOrdersData;
import com.cplerings.core.api.dashboard.mapper.APIViewBranchOrdersMapper;
import com.cplerings.core.api.dashboard.request.ViewBranchOrdersRequest;
import com.cplerings.core.api.dashboard.response.ViewBranchOrdersResponse;
import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.DashboardTag;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.application.dashboard.ViewBranchOrdersUseCase;
import com.cplerings.core.application.dashboard.input.ViewBranchOrdersInput;
import com.cplerings.core.application.dashboard.output.ViewBranchOrdersOutput;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.api.APIConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ViewBranchOrdersController extends AbstractController<ViewBranchOrdersInput, ViewBranchOrdersOutput, ViewBranchOrdersData, ViewBranchOrdersRequest, ViewBranchOrdersResponse> {

    private final ViewBranchOrdersUseCase viewBranchOrdersUseCase;
    private final APIViewBranchOrdersMapper apiViewBranchOrdersMapper;

    @Override
    protected UseCase<ViewBranchOrdersInput, ViewBranchOrdersOutput> getUseCase() {
        return viewBranchOrdersUseCase;
    }

    @Override
    protected APIMapper<ViewBranchOrdersInput, ViewBranchOrdersOutput, ViewBranchOrdersData, ViewBranchOrdersRequest, ViewBranchOrdersResponse> getMapper() {
        return apiViewBranchOrdersMapper;
    }

    @GetMapping(APIConstant.ORDERS_STATISTIC_PATH)
    @DashboardTag
    @Operation(summary = "View orders statistic")
    @ApiResponse(
            description = "The orders",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = ViewBranchOrdersResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> view(ViewBranchOrdersRequest request) {
        return handleRequest(request);
    }
}
