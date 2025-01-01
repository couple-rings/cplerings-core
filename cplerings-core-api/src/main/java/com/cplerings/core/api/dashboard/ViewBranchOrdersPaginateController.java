package com.cplerings.core.api.dashboard;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.dashboard.data.ViewBranchOrdersPaginateData;
import com.cplerings.core.api.dashboard.mapper.APIViewBranchOrdersPaginateMapper;
import com.cplerings.core.api.dashboard.request.ViewBranchOrdersPaginateRequest;
import com.cplerings.core.api.dashboard.response.ViewBranchOrdersPaginateResponse;
import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.DashboardTag;
import com.cplerings.core.api.shared.openapi.DevTag;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.application.dashboard.ViewBranchOrdersPaginateUseCase;
import com.cplerings.core.application.dashboard.input.ViewBranchOrdersPaginateInput;
import com.cplerings.core.application.dashboard.output.ViewBranchOrdersPaginateOutput;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.api.APIConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ViewBranchOrdersPaginateController extends AbstractController<ViewBranchOrdersPaginateInput, ViewBranchOrdersPaginateOutput, ViewBranchOrdersPaginateData, ViewBranchOrdersPaginateRequest, ViewBranchOrdersPaginateResponse> {

    private final ViewBranchOrdersPaginateUseCase useCase;
    private final APIViewBranchOrdersPaginateMapper mapper;

    @Override
    protected UseCase<ViewBranchOrdersPaginateInput, ViewBranchOrdersPaginateOutput> getUseCase() {
        return useCase;
    }

    @Override
    protected APIMapper<ViewBranchOrdersPaginateInput, ViewBranchOrdersPaginateOutput, ViewBranchOrdersPaginateData, ViewBranchOrdersPaginateRequest, ViewBranchOrdersPaginateResponse> getMapper() {
        return mapper;
    }

    @GetMapping(APIConstant.ORDERS_STATISTIC_PAGINATION_PATH)
    @DevTag
    @Operation(summary = "View orders statistic")
    @ApiResponse(
            description = "The orders",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = ViewBranchOrdersPaginateRequest.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> view(ViewBranchOrdersPaginateRequest request) {
        return handleRequest(request);
    }
}
