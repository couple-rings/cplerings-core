package com.cplerings.core.api.design;

import com.cplerings.core.api.design.data.CustomRequestData;
import com.cplerings.core.api.design.mapper.APIViewCustomRequestMapper;
import com.cplerings.core.api.design.request.ViewCustomRequestRequest;
import com.cplerings.core.api.design.response.ViewCustomRequestResponse;
import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.CustomRequestTag;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.application.design.ViewCustomRequestUseCase;
import com.cplerings.core.application.design.input.ViewCustomRequestInput;
import com.cplerings.core.application.design.output.ViewCustomRequestOutput;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.api.APIConstant;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequiredArgsConstructor
public class ViewSingleCustomRequestController extends AbstractController<ViewCustomRequestInput, ViewCustomRequestOutput, CustomRequestData, ViewCustomRequestRequest, ViewCustomRequestResponse> {

    private final APIViewCustomRequestMapper apiViewCustomRequestMapper;
    private final ViewCustomRequestUseCase viewCustomRequestUseCase;

    @GetMapping(APIConstant.CUSTOM_SINGLE_REQUEST_PATH)
    @CustomRequestTag
    @Operation(summary = "View custom request detail")
    @Parameter(
            name = "customRequestId",
            description = "custom request ID"
    )
    @ApiResponse(
            description = "The custom request information",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = ViewCustomRequestResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> view(@PathVariable("customRequestId") Long customRequestId) {
        ViewCustomRequestRequest viewCustomRequestRequest = new ViewCustomRequestRequest(customRequestId);
        return handleRequest(viewCustomRequestRequest);
    }

    @Override
    protected UseCase<ViewCustomRequestInput, ViewCustomRequestOutput> getUseCase() {
        return viewCustomRequestUseCase;
    }

    @Override
    protected APIMapper<ViewCustomRequestInput, ViewCustomRequestOutput, CustomRequestData, ViewCustomRequestRequest, ViewCustomRequestResponse> getMapper() {
        return apiViewCustomRequestMapper;
    }
}
