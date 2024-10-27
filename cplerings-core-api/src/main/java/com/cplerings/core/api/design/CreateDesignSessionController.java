package com.cplerings.core.api.design;

import com.cplerings.core.api.design.data.DesignSessionPayment;
import com.cplerings.core.api.design.mapper.APICreateDesignSessionMapper;
import com.cplerings.core.api.design.response.CreateDesignSessionResponse;
import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.NoRequest;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.DesignTag;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.application.design.CreateDesignSessionUseCase;
import com.cplerings.core.application.design.output.CreateDesignSessionOutput;
import com.cplerings.core.application.shared.input.NoInput;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.api.APIConstant;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequiredArgsConstructor
public class CreateDesignSessionController extends AbstractController<NoInput, CreateDesignSessionOutput, DesignSessionPayment, NoRequest, CreateDesignSessionResponse> {

    private final CreateDesignSessionUseCase createDesignSessionUseCase;
    private final APICreateDesignSessionMapper apiCreateDesignSessionMapper;

    @PostMapping(APIConstant.CREATE_DESIGN_SESSION_PATH)
    @DesignTag
    @Operation(summary = "Create Design session")
    @ApiResponse(
            description = "Payment link",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = CreateDesignSessionResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> create() {
        return handleRequest();
    }

    @Override
    protected UseCase<NoInput, CreateDesignSessionOutput> getUseCase() {
        return createDesignSessionUseCase;
    }

    @Override
    protected APIMapper<NoInput, CreateDesignSessionOutput, DesignSessionPayment, NoRequest, CreateDesignSessionResponse> getMapper() {
        return apiCreateDesignSessionMapper;
    }
}
