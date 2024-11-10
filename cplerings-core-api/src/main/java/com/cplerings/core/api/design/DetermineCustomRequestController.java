package com.cplerings.core.api.design;

import com.cplerings.core.api.design.data.CustomRequest;
import com.cplerings.core.api.design.mapper.APIDetermineCustomRequestMapper;
import com.cplerings.core.api.design.request.DetermineCustomRequestRequest;
import com.cplerings.core.api.design.request.data.DetermineCustomRequestRequestData;
import com.cplerings.core.api.design.response.DetermineCustomRequestResponse;
import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.CustomRequestTag;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.application.design.DetermineCustomRequestUseCase;
import com.cplerings.core.application.design.input.DetermineCustomRequestInput;
import com.cplerings.core.application.design.output.DetermineCustomRequestOutput;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.api.APIConstant;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RequiredArgsConstructor
@RestController
public class DetermineCustomRequestController extends AbstractController<DetermineCustomRequestInput, DetermineCustomRequestOutput, CustomRequest, DetermineCustomRequestRequest, DetermineCustomRequestResponse> {

    private final DetermineCustomRequestUseCase determineCustomRequestUseCase;
    private final APIDetermineCustomRequestMapper apiDetermineCustomRequestMapper;

    @PutMapping(APIConstant.DETERMINE_CUSTOM_REQUEST_PATH)
    @CustomRequestTag
    @Operation(summary = "Accept or reject custom request")
    @ApiResponse(
            description = "The custom request",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = DetermineCustomRequestResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> determine(@PathVariable("customRequestId") Long customRequestId, @RequestBody DetermineCustomRequestRequestData determineCustomRequestRequestData) {
        DetermineCustomRequestRequest request = DetermineCustomRequestRequest.builder()
                .customRequestId(customRequestId)
                .customRequestStatus(determineCustomRequestRequestData.getCustomRequestStatus())
                .staffId(determineCustomRequestRequestData.getStaffId())
                .build();
        return handleRequest(request);
    }

    @Override
    protected UseCase<DetermineCustomRequestInput, DetermineCustomRequestOutput> getUseCase() {
        return determineCustomRequestUseCase;
    }

    @Override
    protected APIMapper<DetermineCustomRequestInput, DetermineCustomRequestOutput, CustomRequest, DetermineCustomRequestRequest, DetermineCustomRequestResponse> getMapper() {
        return apiDetermineCustomRequestMapper;
    }
}
