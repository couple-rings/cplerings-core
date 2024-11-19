package com.cplerings.core.api.fingersize;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.branch.request.ViewBranchesRequest;
import com.cplerings.core.api.branch.response.ViewBranchesResponse;
import com.cplerings.core.api.fingersize.data.FingerSizesData;
import com.cplerings.core.api.fingersize.mapper.APIViewFingerSizesMapper;
import com.cplerings.core.api.fingersize.request.ViewFingerSizesRequest;
import com.cplerings.core.api.fingersize.response.ViewFingerSizesResponse;
import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.BranchTag;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.application.fingersize.ViewFingerSizesUseCase;
import com.cplerings.core.application.fingersize.input.ViewFingerSizesInput;
import com.cplerings.core.application.fingersize.output.ViewFingerSizesOutput;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.api.APIConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ViewFingerSizesController extends AbstractController<ViewFingerSizesInput, ViewFingerSizesOutput, FingerSizesData, ViewFingerSizesRequest, ViewFingerSizesResponse> {

    private final ViewFingerSizesUseCase viewFingerSizesUseCase;
    private final APIViewFingerSizesMapper apiViewFingerSizesMapper;

    @Override
    protected UseCase<ViewFingerSizesInput, ViewFingerSizesOutput> getUseCase() {
        return viewFingerSizesUseCase;
    }

    @Override
    protected APIMapper<ViewFingerSizesInput, ViewFingerSizesOutput, FingerSizesData, ViewFingerSizesRequest, ViewFingerSizesResponse> getMapper() {
        return apiViewFingerSizesMapper;
    }

    @GetMapping(APIConstant.FINGER_SIZES_PATH)
    @BranchTag
    @Operation(summary = "View finger sizes")
    @ApiResponse(
            description = "View finger sizes",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = ViewFingerSizesResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> view(ViewFingerSizesRequest request) {
        return handleRequest(request);
    }
}
