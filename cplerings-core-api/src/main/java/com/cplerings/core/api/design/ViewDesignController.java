package com.cplerings.core.api.design;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.design.mapper.APIViewDesignMapper;
import com.cplerings.core.api.design.request.ViewDesignRequest;
import com.cplerings.core.api.design.request.ViewDesignVersionRequest;
import com.cplerings.core.api.design.response.ViewDesignResponse;
import com.cplerings.core.api.design.response.ViewDesignVersionResponse;
import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.DesignTag;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.application.design.ViewDesignUseCase;
import com.cplerings.core.application.design.input.ViewDesignInput;
import com.cplerings.core.application.design.output.ViewDesignOutput;
import com.cplerings.core.application.shared.entity.design.ADesign;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.api.APIConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ViewDesignController extends AbstractController<ViewDesignInput, ViewDesignOutput, ADesign, ViewDesignRequest, ViewDesignResponse> {

    private final APIViewDesignMapper apiViewDesignMapper;
    private final ViewDesignUseCase viewDesignUseCase;

    @Override
    protected UseCase<ViewDesignInput, ViewDesignOutput> getUseCase() {
        return viewDesignUseCase;
    }

    @Override
    protected APIMapper<ViewDesignInput, ViewDesignOutput, ADesign, ViewDesignRequest, ViewDesignResponse> getMapper() {
        return apiViewDesignMapper;
    }

    @GetMapping(APIConstant.VIEW_DESIGN_PATH)
    @DesignTag
    @Operation(summary = "View design detail")
    @ApiResponse(
            description = "Design information",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = ViewDesignResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> viewDesign(@PathVariable("designId") Long designId) {
        ViewDesignRequest request = ViewDesignRequest.builder().designId(designId).build();
        return handleRequest(request);
    }
}
