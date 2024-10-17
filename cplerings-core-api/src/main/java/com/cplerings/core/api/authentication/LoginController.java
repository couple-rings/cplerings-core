package com.cplerings.core.api.authentication;

import com.cplerings.core.api.authentication.data.AuthenticationToken;
import com.cplerings.core.api.authentication.mapper.APILoginMapper;
import com.cplerings.core.api.authentication.request.LoginCredentialRequest;
import com.cplerings.core.api.authentication.response.AuthenticationTokenResponse;
import com.cplerings.core.api.shared.AbstractDataController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.AuthTag;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.application.authentication.LoginUseCase;
import com.cplerings.core.application.authentication.input.LoginCredentialInput;
import com.cplerings.core.application.authentication.output.AuthenticationTokenOutput;
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
public class LoginController extends AbstractDataController<LoginCredentialInput, AuthenticationTokenOutput, AuthenticationToken, LoginCredentialRequest, AuthenticationTokenResponse> {

    private final LoginUseCase loginUseCase;
    private final APILoginMapper apiLoginMapper;

    @PostMapping(APIConstant.LOGIN_PATH)
    @AuthTag
    @Operation(summary = "Login into the app and receive Authentication JWT")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Login credential",
            required = true,
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = LoginCredentialRequest.class)
            )
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
