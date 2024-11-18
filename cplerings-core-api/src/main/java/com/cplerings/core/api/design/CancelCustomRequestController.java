package com.cplerings.core.api.design;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.design.data.CustomRequest;
import com.cplerings.core.api.design.mapper.APICancelCustomRequestMapper;
import com.cplerings.core.api.design.request.CancelCustomRequestRequest;
import com.cplerings.core.api.design.request.DetermineCustomRequestRequest;
import com.cplerings.core.api.design.request.data.DetermineCustomRequestRequestData;
import com.cplerings.core.api.design.response.CancelCustomRequestResponse;
import com.cplerings.core.api.design.response.DetermineCustomRequestResponse;
import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.CustomRequestTag;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.application.design.CancelCustomRequestUseCase;
import com.cplerings.core.application.design.input.CancelCustomRequestInput;
import com.cplerings.core.application.design.output.CancelCustomRequestOutput;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.api.APIConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class CancelCustomRequestController extends AbstractController<CancelCustomRequestInput, CancelCustomRequestOutput, CustomRequest, CancelCustomRequestRequest, CancelCustomRequestResponse> {

    private final APICancelCustomRequestMapper apiCancelCustomRequestMapper;
    private final CancelCustomRequestUseCase cancelCustomRequestUseCase;

    @Override
    protected UseCase<CancelCustomRequestInput, CancelCustomRequestOutput> getUseCase() {
        return cancelCustomRequestUseCase;
    }

    @Override
    protected APIMapper<CancelCustomRequestInput, CancelCustomRequestOutput, CustomRequest, CancelCustomRequestRequest, CancelCustomRequestResponse> getMapper() {
        return apiCancelCustomRequestMapper;
    }

    @PutMapping(APIConstant.CANCEL_CUSTOM_REQUEST_PATH)
    @CustomRequestTag
    @Operation(summary = "Reject custom request")
    @ApiResponse(
            description = "The custom request",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = CancelCustomRequestResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> cancel(@PathVariable("customRequestId") Long customRequestId) {
        CancelCustomRequestRequest request = CancelCustomRequestRequest.builder().customRequestId(customRequestId).build();
        return handleRequest(request);
    }
}
