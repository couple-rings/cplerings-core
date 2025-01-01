package com.cplerings.core.api.dashboard;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.dashboard.data.ViewTotalRevenueOfBranchData;
import com.cplerings.core.api.dashboard.mapper.APIViewTotalRevenueOfBranchMapper;
import com.cplerings.core.api.dashboard.response.ViewTotalRevenueResponse;
import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.NoRequest;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.DashboardTag;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.application.dashboard.ViewTotalRevenueUseCase;
import com.cplerings.core.application.dashboard.output.ViewTotalRevenueOutput;
import com.cplerings.core.application.shared.input.NoInput;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.api.APIConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ViewTotalRevenueOfBranchController extends AbstractController<NoInput, ViewTotalRevenueOutput, ViewTotalRevenueOfBranchData, NoRequest, ViewTotalRevenueResponse> {

    private final APIViewTotalRevenueOfBranchMapper mapper;
    private final ViewTotalRevenueUseCase useCase;

    @Override
    protected UseCase<NoInput, ViewTotalRevenueOutput> getUseCase() {
        return useCase;
    }

    @Override
    protected APIMapper<NoInput, ViewTotalRevenueOutput, ViewTotalRevenueOfBranchData, NoRequest, ViewTotalRevenueResponse> getMapper() {
        return mapper;
    }

    @GetMapping(APIConstant.TOTAL_REVENUE_BRANCH_PATH)
    @DashboardTag
    @Operation(summary = "View total Revenue")
    @ApiResponse(
            description = "The total Revenue",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = ViewTotalRevenueResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> view(NoRequest request) {
        return handleRequest(request);
    }
}
