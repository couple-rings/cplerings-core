package com.cplerings.core.api.design;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.design.data.DesignCollectionsData;
import com.cplerings.core.api.design.mapper.APIViewDesignCollectionsMapper;
import com.cplerings.core.api.design.request.ViewDesignCollectionsRequest;
import com.cplerings.core.api.design.response.ViewCustomRequestsResponse;
import com.cplerings.core.api.design.response.ViewDesignCollectionsResponse;
import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.DesignTag;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.application.design.ViewDesignCollectionsUseCase;
import com.cplerings.core.application.design.input.ViewDesignCollectionsInput;
import com.cplerings.core.application.design.output.ViewDesignCollectionsOutput;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.api.APIConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ViewDesignCollectionsController extends AbstractController<ViewDesignCollectionsInput, ViewDesignCollectionsOutput, DesignCollectionsData, ViewDesignCollectionsRequest, ViewDesignCollectionsResponse> {

    private final APIViewDesignCollectionsMapper apiViewDesignCollectionsMapper;
    private final ViewDesignCollectionsUseCase viewDesignCollectionsUseCase;

    @Override
    protected UseCase<ViewDesignCollectionsInput, ViewDesignCollectionsOutput> getUseCase() {
        return viewDesignCollectionsUseCase;
    }

    @Override
    protected APIMapper<ViewDesignCollectionsInput, ViewDesignCollectionsOutput, DesignCollectionsData, ViewDesignCollectionsRequest, ViewDesignCollectionsResponse> getMapper() {
        return apiViewDesignCollectionsMapper;
    }

    @GetMapping(APIConstant.VIEW_DESIGN_COLLECTION_PATH)
    @DesignTag
    @Operation(summary = "View design collections")
    @ApiResponse(
            description = "Design Collections infor",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = ViewCustomRequestsResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> view(ViewDesignCollectionsRequest request) {
        return handleRequest(request);
    }
}
