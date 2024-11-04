package com.cplerings.core.api.design;

import com.cplerings.core.api.design.mapper.APICreateCustomRequestMapper;
import com.cplerings.core.api.design.request.CreateCustomRequestRequest;
import com.cplerings.core.api.design.response.CreateCustomRequestResponse;
import com.cplerings.core.api.shared.AbstractDataController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.CustomRequestTag;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.application.design.CreateCustomRequestUseCase;
import com.cplerings.core.application.design.input.CreateCustomRequestInput;
import com.cplerings.core.application.design.output.CreateCustomRequestOutput;
import com.cplerings.core.application.shared.entity.design.request.ACustomRequest;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.api.APIConstant;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequiredArgsConstructor
public class CreateCustomRequestController extends AbstractDataController<CreateCustomRequestInput, CreateCustomRequestOutput, ACustomRequest, CreateCustomRequestRequest, CreateCustomRequestResponse> {

    private final CreateCustomRequestUseCase createCustomRequestUseCase;
    private final APICreateCustomRequestMapper apiCreateCustomRequestMapper;

    @PostMapping(APIConstant.CUSTOM_REQUEST_PATH)
    @CustomRequestTag
    @Operation(summary = "Create custom request")
    @ApiResponse(
            description = "The created custom request",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = CreateCustomRequestResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> create(@RequestBody CreateCustomRequestRequest request) {
        return handleRequest(request);
    }

    @Override
    protected UseCase<CreateCustomRequestInput, CreateCustomRequestOutput> getUseCase() {
        return createCustomRequestUseCase;
    }

    @Override
    protected APIMapper<CreateCustomRequestInput, CreateCustomRequestOutput, ACustomRequest, CreateCustomRequestRequest, CreateCustomRequestResponse> getMapper() {
        return apiCreateCustomRequestMapper;
    }
}
