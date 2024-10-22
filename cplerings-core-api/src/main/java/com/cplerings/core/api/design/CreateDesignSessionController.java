package com.cplerings.core.api.design;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.account.request.VerifyCustomerRequest;
import com.cplerings.core.api.account.response.ProfileResponse;
import com.cplerings.core.api.design.data.DesignSession;
import com.cplerings.core.api.design.mapper.APICreateDesignSessionMapper;
import com.cplerings.core.api.design.request.CreateDesignSessionRequest;
import com.cplerings.core.api.design.response.CreateDesignSessionResponse;
import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.AccountTag;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.application.design.CreateDesignSessionUseCase;
import com.cplerings.core.application.design.input.CreateDesignSessionInput;
import com.cplerings.core.application.design.output.CreateDesignSessionOutput;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.api.APIConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CreateDesignSessionController extends AbstractController<CreateDesignSessionInput, CreateDesignSessionOutput, DesignSession, CreateDesignSessionRequest, CreateDesignSessionResponse> {

    private final CreateDesignSessionUseCase createDesignSessionUseCase;
    private final APICreateDesignSessionMapper apiCreateDesignSessionMapper;

    @PostMapping(APIConstant.DESIGN_SESSION_PATH)
//    @AccountTag
    @Operation(summary = "Create Design session")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Customer id",
            required = true,
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = CreateDesignSessionRequest.class)
            )
    )
    @ApiResponse(
            description = "UUID session of 3 sessions",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = CreateDesignSessionResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> create(@RequestBody CreateDesignSessionRequest createDesignSessionRequest) {
        return handleRequest(createDesignSessionRequest);
    }

    @Override
    protected UseCase<CreateDesignSessionInput, CreateDesignSessionOutput> getUseCase() {
        return createDesignSessionUseCase;
    }

    @Override
    protected APIMapper<CreateDesignSessionInput, CreateDesignSessionOutput, DesignSession, CreateDesignSessionRequest, CreateDesignSessionResponse> getMapper() {
        return apiCreateDesignSessionMapper;
    }
}
