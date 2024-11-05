package com.cplerings.core.api.design;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.design.data.DesignVersionsData;
import com.cplerings.core.api.design.mapper.APIViewDesignVersionsMapper;
import com.cplerings.core.api.design.request.ViewDesignVersionsRequest;
import com.cplerings.core.api.design.response.ViewDesignVersionsResponse;
import com.cplerings.core.api.shared.AbstractPaginatedController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.DesignTag;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.application.design.ViewDesignVersionsUseCase;
import com.cplerings.core.application.design.input.ViewDesignVersionsInput;
import com.cplerings.core.application.design.output.ViewDesignVersionsOutput;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.api.APIConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ViewDesignVersionsController extends AbstractPaginatedController<ViewDesignVersionsInput, ViewDesignVersionsOutput, DesignVersionsData, ViewDesignVersionsRequest, ViewDesignVersionsResponse> {

    private final APIViewDesignVersionsMapper apiViewDesignVersionsMapper;
    private final ViewDesignVersionsUseCase viewDesignVersionsUseCase;

    @GetMapping(APIConstant.VIEW_CURRENT_DESIGN_VERSIONS_PATH)
    @DesignTag
    @Operation(summary = "View design versions")
    @ApiResponse(
            description = "View View design versions",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = ViewDesignVersionsResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> viewDesign(ViewDesignVersionsRequest request) {
        return handleRequest(request);
    }

    @Override
    protected UseCase<ViewDesignVersionsInput, ViewDesignVersionsOutput> getUseCase() {
        return viewDesignVersionsUseCase;
    }

    @Override
    protected APIMapper<ViewDesignVersionsInput, ViewDesignVersionsOutput, DesignVersionsData, ViewDesignVersionsRequest, ViewDesignVersionsResponse> getMapper() {
        return apiViewDesignVersionsMapper;
    }
}
