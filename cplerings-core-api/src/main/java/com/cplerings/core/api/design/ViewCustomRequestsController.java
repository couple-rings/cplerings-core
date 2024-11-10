package com.cplerings.core.api.design;

import com.cplerings.core.api.design.data.CustomRequestsData;
import com.cplerings.core.api.design.mapper.APIViewCustomRequestsMapper;
import com.cplerings.core.api.design.request.ViewCustomRequestsRequest;
import com.cplerings.core.api.design.response.ViewCustomRequestsResponse;
import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.CustomRequestTag;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.application.design.ViewCustomRequestsUseCase;
import com.cplerings.core.application.design.input.ViewCustomRequestsInput;
import com.cplerings.core.application.design.output.ViewCustomRequestsOutput;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.api.APIConstant;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequiredArgsConstructor
public class ViewCustomRequestsController extends AbstractController<ViewCustomRequestsInput, ViewCustomRequestsOutput, CustomRequestsData, ViewCustomRequestsRequest, ViewCustomRequestsResponse> {

    private final APIViewCustomRequestsMapper apiViewCustomRequestsMapper;
    private final ViewCustomRequestsUseCase viewCustomRequestsUseCase;

    @GetMapping(APIConstant.VIEW_CUSTOM_REQUESTS_PATH)
    @CustomRequestTag
    @Operation(summary = "View custom requests")
    @ApiResponse(
            description = "View custom requests",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = ViewCustomRequestsResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> view(ViewCustomRequestsRequest request) {
        return handleRequest(request);
    }

    @Override
    protected UseCase<ViewCustomRequestsInput, ViewCustomRequestsOutput> getUseCase() {
        return viewCustomRequestsUseCase;
    }

    @Override
    protected APIMapper<ViewCustomRequestsInput, ViewCustomRequestsOutput, CustomRequestsData, ViewCustomRequestsRequest, ViewCustomRequestsResponse> getMapper() {
        return apiViewCustomRequestsMapper;
    }
}
