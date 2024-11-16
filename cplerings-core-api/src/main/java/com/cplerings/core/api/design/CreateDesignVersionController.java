package com.cplerings.core.api.design;

import com.cplerings.core.api.design.data.CreateDesignVersionData;
import com.cplerings.core.api.design.data.DesignVersion;
import com.cplerings.core.api.design.mapper.APICreateCustomDesignVersionMapper;
import com.cplerings.core.api.design.request.CreateDesignVersionRequest;
import com.cplerings.core.api.design.response.CreateDesignVersionResponse;
import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.DesignTag;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.application.design.CreateDesignVersionUseCase;
import com.cplerings.core.application.design.input.CreateDesignVersionInput;
import com.cplerings.core.application.design.output.CreateDesignVersionOutput;
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
public class CreateDesignVersionController extends AbstractController<CreateDesignVersionInput, CreateDesignVersionOutput, CreateDesignVersionData, CreateDesignVersionRequest, CreateDesignVersionResponse> {

    private final CreateDesignVersionUseCase createDesignVersionUseCase;
    private final APICreateCustomDesignVersionMapper apiCreateCustomDesignVersionMapper;

    @PostMapping(APIConstant.DESIGN_VERSION_PATH)
    @DesignTag
    @Operation(summary = "Create Design version")
    @ApiResponse(
            description = "Design version information",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = CreateDesignVersionResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> create(@RequestBody CreateDesignVersionRequest request) {
        return handleRequest(request);
    }

    @Override
    protected UseCase<CreateDesignVersionInput, CreateDesignVersionOutput> getUseCase() {
        return createDesignVersionUseCase;
    }

    @Override
    protected APIMapper<CreateDesignVersionInput, CreateDesignVersionOutput, CreateDesignVersionData, CreateDesignVersionRequest, CreateDesignVersionResponse> getMapper() {
        return apiCreateCustomDesignVersionMapper;
    }
}
