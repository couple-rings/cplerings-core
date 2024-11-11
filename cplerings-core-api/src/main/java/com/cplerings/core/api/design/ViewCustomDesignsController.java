package com.cplerings.core.api.design;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.design.data.CustomDesignData;
import com.cplerings.core.api.design.data.CustomDesigns;
import com.cplerings.core.api.design.mapper.APIViewCustomDesignsMapper;
import com.cplerings.core.api.design.request.ViewCustomDesignsRequest;
import com.cplerings.core.api.design.request.ViewCustomRequestsRequest;
import com.cplerings.core.api.design.response.ViewCoupleDesignResponse;
import com.cplerings.core.api.design.response.ViewCustomDesignsResponse;
import com.cplerings.core.api.shared.AbstractPaginatedController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.DesignTag;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.application.design.ViewCustomDesignsUseCase;
import com.cplerings.core.application.design.input.ViewCustomDesignsInput;
import com.cplerings.core.application.design.output.ViewCustomDesignsOutput;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.api.APIConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ViewCustomDesignsController extends AbstractPaginatedController<ViewCustomDesignsInput, ViewCustomDesignsOutput, CustomDesigns, ViewCustomDesignsRequest, ViewCustomDesignsResponse> {

    private final APIViewCustomDesignsMapper viewCustomDesignsMapper;
    private final ViewCustomDesignsUseCase viewCustomDesignsUseCase;

    @GetMapping(APIConstant.VIEW_CUSTOM_DESIGNS_PATH)
    @DesignTag
    @Operation(summary = "View custom designs")
    @ApiResponse(
            description = "View custom designs",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = ViewCustomDesignsResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> view(ViewCustomDesignsRequest request) {
        return handleRequest(request);
    }

    @Override
    protected UseCase<ViewCustomDesignsInput, ViewCustomDesignsOutput> getUseCase() {
        return viewCustomDesignsUseCase;
    }

    @Override
    protected APIMapper<ViewCustomDesignsInput, ViewCustomDesignsOutput, CustomDesigns, ViewCustomDesignsRequest, ViewCustomDesignsResponse> getMapper() {
        return viewCustomDesignsMapper;
    }
}
