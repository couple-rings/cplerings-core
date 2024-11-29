package com.cplerings.core.api.design;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.design.data.DesignsData;
import com.cplerings.core.api.design.mapper.APIViewDesignsMapper;
import com.cplerings.core.api.design.request.ViewDesignsRequest;
import com.cplerings.core.api.design.response.ViewDesignsResponse;
import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.DesignTag;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.application.design.ViewDesignsUseCase;
import com.cplerings.core.application.design.input.ViewDesignsInput;
import com.cplerings.core.application.design.output.ViewDesignsOutput;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.api.APIConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ViewDesignsController extends AbstractController<ViewDesignsInput, ViewDesignsOutput, DesignsData, ViewDesignsRequest, ViewDesignsResponse> {

    private final ViewDesignsUseCase viewDesignsUseCase;
    private final APIViewDesignsMapper apiViewDesignsMapper;

    @Override
    protected UseCase<ViewDesignsInput, ViewDesignsOutput> getUseCase() {
        return viewDesignsUseCase;
    }

    @Override
    protected APIMapper<ViewDesignsInput, ViewDesignsOutput, DesignsData, ViewDesignsRequest, ViewDesignsResponse> getMapper() {
        return apiViewDesignsMapper;
    }

    @GetMapping(APIConstant.DESIGN_PATH)
    @DesignTag
    @Operation(summary = "View designs")
    @ApiResponse(
            description = "designs information",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = ViewDesignsResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> view(ViewDesignsRequest request) {
        return handleRequest(request);
    }
}
