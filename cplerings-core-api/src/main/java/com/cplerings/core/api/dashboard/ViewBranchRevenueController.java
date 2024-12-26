package com.cplerings.core.api.dashboard;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.crafting.request.ViewCraftingRequestRequest;
import com.cplerings.core.api.crafting.response.ViewCraftingRequestResponse;
import com.cplerings.core.api.dashboard.data.ViewBranchRevenueData;
import com.cplerings.core.api.dashboard.mapper.APIViewBranchRevenueMapper;
import com.cplerings.core.api.dashboard.request.ViewBranchRevenueRequest;
import com.cplerings.core.api.dashboard.response.ViewBranchRevenueResponse;
import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.CraftingRequestTag;
import com.cplerings.core.api.shared.openapi.DashboardTag;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.application.dashboard.ViewBranchRevenueUseCase;
import com.cplerings.core.application.dashboard.input.ViewBranchRevenueInput;
import com.cplerings.core.application.dashboard.output.ViewBranchRevenueOutput;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.api.APIConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ViewBranchRevenueController extends AbstractController<ViewBranchRevenueInput, ViewBranchRevenueOutput, ViewBranchRevenueData, ViewBranchRevenueRequest, ViewBranchRevenueResponse> {

    private final APIViewBranchRevenueMapper apiViewBranchRevenueMapper;
    private final ViewBranchRevenueUseCase viewBranchRevenueUseCase;

    @Override
    protected UseCase<ViewBranchRevenueInput, ViewBranchRevenueOutput> getUseCase() {
        return viewBranchRevenueUseCase;
    }

    @Override
    protected APIMapper<ViewBranchRevenueInput, ViewBranchRevenueOutput, ViewBranchRevenueData, ViewBranchRevenueRequest, ViewBranchRevenueResponse> getMapper() {
        return apiViewBranchRevenueMapper;
    }

    @GetMapping(APIConstant.REVENUE_PATH)
    @DashboardTag
    @Operation(summary = "View revenue")
    @ApiResponse(
            description = "The revenue",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = ViewBranchRevenueResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> view(ViewBranchRevenueRequest request) {
        return handleRequest(request);
    }
}
