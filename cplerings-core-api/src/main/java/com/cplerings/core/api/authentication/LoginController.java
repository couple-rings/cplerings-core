package com.cplerings.core.api.authentication;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.authentication.data.AuthenticationToken;
import com.cplerings.core.api.authentication.mapper.APILoginMapper;
import com.cplerings.core.api.authentication.request.LoginCredentialRequest;
import com.cplerings.core.api.authentication.response.AuthenticationTokenResponse;
import com.cplerings.core.api.mapper.APIMapper;
import com.cplerings.core.api.openapi.AuthTag;
import com.cplerings.core.api.openapi.ErrorAPIResponse;
import com.cplerings.core.api.shared.AbstractDataController;
import com.cplerings.core.application.authentication.LoginUseCase;
import com.cplerings.core.application.authentication.input.LoginCredentialInput;
import com.cplerings.core.application.authentication.output.AuthenticationTokenOutput;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.api.APIConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class LoginController extends AbstractDataController<LoginCredentialInput, AuthenticationTokenOutput, AuthenticationToken, LoginCredentialRequest, AuthenticationTokenResponse> {

    private final LoginUseCase loginUseCase;
    private final APILoginMapper apiLoginMapper;

    @PostMapping("/auth/login")
    @AuthTag
    @Operation(summary = "Login into the app and receive Authentication JWT")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Login credential",
            required = true,
            content = @Content(schema = @Schema(implementation = LoginCredentialRequest.class))
    )
    @ApiResponse(
            description = "The token for further access to the system",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = AuthenticationTokenResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> login(@RequestBody LoginCredentialRequest request) {
        return handleRequest(request);
    }

    @Override
    protected UseCase<LoginCredentialInput, AuthenticationTokenOutput> getUseCase() {
        return loginUseCase;
    }

    @Override
    protected APIMapper<LoginCredentialInput, AuthenticationTokenOutput, AuthenticationToken, LoginCredentialRequest, AuthenticationTokenResponse> getMapper() {
        return apiLoginMapper;
    }
}
