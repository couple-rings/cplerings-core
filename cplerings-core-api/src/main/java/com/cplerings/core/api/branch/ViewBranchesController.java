package com.cplerings.core.api.branch;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.branch.data.BranchesData;
import com.cplerings.core.api.branch.mapper.APIViewBranchesMapper;
import com.cplerings.core.api.branch.request.ViewBranchesRequest;
import com.cplerings.core.api.branch.response.ViewBranchesResponse;
import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.BranchTag;
import com.cplerings.core.api.shared.openapi.DiamondSpecificationTag;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.application.branch.ViewBranchesUseCase;
import com.cplerings.core.application.branch.input.ViewBranchesInput;
import com.cplerings.core.application.branch.output.ViewBranchesOutput;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.api.APIConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ViewBranchesController extends AbstractController<ViewBranchesInput, ViewBranchesOutput, BranchesData, ViewBranchesRequest, ViewBranchesResponse> {

    private final APIViewBranchesMapper apiViewBranchesMapper;
    private final ViewBranchesUseCase viewBranchesUseCase;

    @Override
    protected UseCase<ViewBranchesInput, ViewBranchesOutput> getUseCase() {
        return viewBranchesUseCase;
    }

    @Override
    protected APIMapper<ViewBranchesInput, ViewBranchesOutput, BranchesData, ViewBranchesRequest, ViewBranchesResponse> getMapper() {
        return apiViewBranchesMapper;
    }

    @GetMapping(APIConstant.BRANCHES_PATH)
    @BranchTag
    @Operation(summary = "View branches")
    @ApiResponse(
            description = "View branches",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = ViewBranchesResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> view(ViewBranchesRequest request) {
        return handleRequest(request);
    }
}
