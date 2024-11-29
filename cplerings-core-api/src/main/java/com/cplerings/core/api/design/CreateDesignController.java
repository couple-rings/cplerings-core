package com.cplerings.core.api.design;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.design.mapper.APICreateDesignMapper;
import com.cplerings.core.api.design.request.CreateDesignRequest;
import com.cplerings.core.api.design.response.CreateDesignResponse;
import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.DesignTag;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.application.design.CreateDesignUseCase;
import com.cplerings.core.application.design.input.CreateDesignInput;
import com.cplerings.core.application.design.output.CreateDesignOutput;
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
public class CreateDesignController extends AbstractController<CreateDesignInput, CreateDesignOutput, ADesign, CreateDesignRequest, CreateDesignResponse> {

    private final APICreateDesignMapper apiCreateDesignMapper;
    private final CreateDesignUseCase createDesignUseCase;

    @Override
    protected UseCase<CreateDesignInput, CreateDesignOutput> getUseCase() {
        return createDesignUseCase;
    }

    @Override
    protected APIMapper<CreateDesignInput, CreateDesignOutput, ADesign, CreateDesignRequest, CreateDesignResponse> getMapper() {
        return apiCreateDesignMapper;
    }

    @PostMapping(APIConstant.DESIGN_PATH)
    @DesignTag
    @Operation(summary = "Create Design")
    @ApiResponse(
            description = "design information",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = CreateDesignResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> create(@RequestBody CreateDesignRequest request) {
        return handleRequest(request);
    }
}
