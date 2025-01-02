package com.cplerings.core.api.dashboard;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.dashboard.data.ViewTotalOrdersOfBranchData;
import com.cplerings.core.api.dashboard.mapper.APIViewTotalOrdersOfBranchMapper;
import com.cplerings.core.api.dashboard.response.ViewTotalOrdersOfBranchResponse;
import com.cplerings.core.api.dashboard.response.ViewTotalTransactionOfBranchResponse;
import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.NoRequest;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.DashboardTag;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.application.dashboard.ViewTotalOrdersOfBranchUseCase;
import com.cplerings.core.application.dashboard.output.ViewTotalOrdersOfBranchOutput;
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
public class ViewTotalOrdersOfBranchController extends AbstractController<NoInput, ViewTotalOrdersOfBranchOutput, ViewTotalOrdersOfBranchData, NoRequest, ViewTotalOrdersOfBranchResponse> {

    private final ViewTotalOrdersOfBranchUseCase useCase;
    private final APIViewTotalOrdersOfBranchMapper mapper;

    @Override
    protected UseCase<NoInput, ViewTotalOrdersOfBranchOutput> getUseCase() {
        return useCase;
    }

    @Override
    protected APIMapper<NoInput, ViewTotalOrdersOfBranchOutput, ViewTotalOrdersOfBranchData, NoRequest, ViewTotalOrdersOfBranchResponse> getMapper() {
        return mapper;
    }

    @GetMapping(APIConstant.TOTAL_ORDERS_BRANCH_PATH)
    @DashboardTag
    @Operation(summary = "View total Orders")
    @ApiResponse(
            description = "The total Orders",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = ViewTotalTransactionOfBranchResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> view(NoRequest request) {
        return handleRequest(request);
    }
}
