package com.cplerings.core.api.design;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.design.data.CustomDesignData;
import com.cplerings.core.api.design.mapper.APIViewCustomDesignMapper;
import com.cplerings.core.api.design.request.ViewCustomDesignRequest;
import com.cplerings.core.api.design.response.ViewCustomDesignResponse;
import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.DesignTag;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.application.design.ViewCustomDesignUseCase;
import com.cplerings.core.application.design.input.ViewCustomDesignInput;
import com.cplerings.core.application.design.output.ViewCustomDesignOutput;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.api.APIConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ViewCustomDesignController extends AbstractController<ViewCustomDesignInput, ViewCustomDesignOutput, CustomDesignData, ViewCustomDesignRequest, ViewCustomDesignResponse> {

    private final APIViewCustomDesignMapper apiViewCustomDesignMapper;
    private final ViewCustomDesignUseCase viewCustomDesignUseCase;

    @GetMapping(APIConstant.VIEW_CUSTOM_DESIGN_PATH)
    @DesignTag
    @Operation(summary = "View a custom design")
    @ApiResponse(
            description = "View a custom design",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = ViewCustomDesignResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> viewDesign(@PathVariable("customDesignId") Long customDesignId) {
        ViewCustomDesignRequest request = new ViewCustomDesignRequest(customDesignId);
        return handleRequest(request);
    }

    @Override
    protected UseCase<ViewCustomDesignInput, ViewCustomDesignOutput> getUseCase() {
        return viewCustomDesignUseCase;
    }

    @Override
    protected APIMapper<ViewCustomDesignInput, ViewCustomDesignOutput, CustomDesignData, ViewCustomDesignRequest, ViewCustomDesignResponse> getMapper() {
        return apiViewCustomDesignMapper;
    }
}
