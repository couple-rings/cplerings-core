package com.cplerings.core.api.design;

import com.cplerings.core.api.design.data.CustomDesignData;
import com.cplerings.core.api.design.mapper.APICreateCustomDesignMapper;
import com.cplerings.core.api.design.request.CreateCustomDesignRequest;
import com.cplerings.core.api.design.response.CreateCustomDesignResponse;
import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.DesignTag;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.application.design.CreateCustomDesignUseCase;
import com.cplerings.core.application.design.input.CreateCustomDesignInput;
import com.cplerings.core.application.design.output.CreateCustomDesignOutput;
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

@RequiredArgsConstructor
@RestController
public class CreateCustomDesignController extends AbstractController<CreateCustomDesignInput, CreateCustomDesignOutput, CustomDesignData, CreateCustomDesignRequest, CreateCustomDesignResponse> {

    private final CreateCustomDesignUseCase createCustomDesignUseCase;
    private final APICreateCustomDesignMapper apiCreateCustomDesignMapper;

    @PostMapping(APIConstant.CREATE_CUSTOM_DESIGN_PATH)
    @DesignTag
    @Operation(summary = "Create custom design")
    @ApiResponse(
            description = "Custom design information",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = CreateCustomDesignResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> create(@RequestBody CreateCustomDesignRequest request) {
        return handleRequest(request);
    }

    @Override
    protected UseCase<CreateCustomDesignInput, CreateCustomDesignOutput> getUseCase() {
        return createCustomDesignUseCase;
    }

    @Override
    protected APIMapper<CreateCustomDesignInput, CreateCustomDesignOutput, CustomDesignData, CreateCustomDesignRequest, CreateCustomDesignResponse> getMapper() {
        return apiCreateCustomDesignMapper;
    }
}
