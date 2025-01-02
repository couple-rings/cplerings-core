package com.cplerings.core.api.dashboard;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.dashboard.data.ViewTotalTransactionOfBranchData;
import com.cplerings.core.api.dashboard.mapper.APIViewTotalTransactionOfBranchMapper;
import com.cplerings.core.api.dashboard.response.ViewTotalTransactionOfBranchResponse;
import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.NoRequest;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.DashboardTag;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.application.dashboard.ViewTotalTransactionsOfBranchUseCase;
import com.cplerings.core.application.dashboard.output.ViewTotalTransactionsOfBranchOutput;
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
public class ViewTotalTransactionsOfBranchController extends AbstractController<NoInput, ViewTotalTransactionsOfBranchOutput, ViewTotalTransactionOfBranchData, NoRequest, ViewTotalTransactionOfBranchResponse> {

    private final APIViewTotalTransactionOfBranchMapper mapper;
    private final ViewTotalTransactionsOfBranchUseCase useCase;

    @Override
    protected UseCase<NoInput, ViewTotalTransactionsOfBranchOutput> getUseCase() {
        return useCase;
    }

    @Override
    protected APIMapper<NoInput, ViewTotalTransactionsOfBranchOutput, ViewTotalTransactionOfBranchData, NoRequest, ViewTotalTransactionOfBranchResponse> getMapper() {
        return mapper;
    }

    @GetMapping(APIConstant.TOTAL_TRANSACTION_BRANCH_PATH)
    @DashboardTag
    @Operation(summary = "View total Transaction")
    @ApiResponse(
            description = "The total Transaction",
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
