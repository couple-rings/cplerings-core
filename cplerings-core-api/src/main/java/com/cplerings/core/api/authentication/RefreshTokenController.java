package com.cplerings.core.api.authentication;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.authentication.data.AuthenticationToken;
import com.cplerings.core.api.authentication.mapper.AuthenticationAPIMapper;
import com.cplerings.core.api.authentication.mapper.RefreshTokenAPIMapper;
import com.cplerings.core.api.authentication.request.RefreshTokenRequest;
import com.cplerings.core.api.authentication.response.AuthenticationTokenResponse;
import com.cplerings.core.api.mapper.APIMapper;
import com.cplerings.core.api.openapi.AuthTag;
import com.cplerings.core.api.openapi.ErrorAPIResponse;
import com.cplerings.core.api.security.IsAnyone;
import com.cplerings.core.api.shared.AbstractDataController;
import com.cplerings.core.application.authentication.RefreshTokenUseCase;
import com.cplerings.core.application.authentication.input.RefreshTokenInput;
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
public class RefreshTokenController extends AbstractDataController<RefreshTokenInput, AuthenticationTokenOutput, AuthenticationToken, RefreshTokenRequest, AuthenticationTokenResponse> {

    private final RefreshTokenUseCase refreshTokenUseCase;
    private final AuthenticationAPIMapper authenticationAPIMapper;
    private final RefreshTokenAPIMapper refreshTokenAPIMapper;

    @PostMapping("/auth/refresh")
    @IsAnyone
    @AuthTag
    @ErrorAPIResponse
    @Operation(summary = "Send refresh token for new Authentication JWT")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Refresh token",
            required = true,
            content = @Content(schema = @Schema(implementation = RefreshTokenRequest.class))
    )
    @ApiResponse(
            description = "The token for further access to the system",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = AuthenticationTokenResponse.class)
            )
    )
    public ResponseEntity<Object> refreshToken(@RequestBody RefreshTokenRequest request) {
        return handleRequest(request);
    }

    @Override
    protected APIMapper<RefreshTokenInput, AuthenticationTokenOutput, AuthenticationToken, RefreshTokenRequest, AuthenticationTokenResponse> getMapper() {
        return refreshTokenAPIMapper;
    }

    @Override
    protected UseCase<RefreshTokenInput, AuthenticationTokenOutput> getUseCase() {
        return refreshTokenUseCase;
    }
}
