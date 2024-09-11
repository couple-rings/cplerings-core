package com.cplerings.core.api.authentication;

import com.cplerings.core.api.APIConstant;
import com.cplerings.core.api.AbstractRestController;
import com.cplerings.core.api.authentication.mapper.AuthenticationAPIMapper;
import com.cplerings.core.api.authentication.request.LoginCredentialRequest;
import com.cplerings.core.api.authentication.response.AuthenticationTokenResponse;
import com.cplerings.core.api.openapi.AuthTag;
import com.cplerings.core.api.openapi.ErrorAPIResponse;
import com.cplerings.core.api.security.IsAnyone;
import com.cplerings.core.application.authentication.LoginUseCase;
import com.cplerings.core.application.authentication.input.LoginCredentialInput;
import com.cplerings.core.application.authentication.output.AuthenticationTokenOutput;
import com.cplerings.core.application.shared.errorcode.ErrorCodes;
import com.cplerings.core.common.either.Either;

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
public class LoginController extends AbstractRestController {

    private final LoginUseCase loginUseCase;
    private final AuthenticationAPIMapper authenticationAPIMapper;

    @PostMapping("/auth/login")
    @IsAnyone
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
    public ResponseEntity<Object> login(@RequestBody LoginCredentialRequest loginCredentialRequest) {
        final LoginCredentialInput loginCredentialInput = authenticationAPIMapper.toInput(loginCredentialRequest);
        final Either<AuthenticationTokenOutput, ErrorCodes> authenticationTokenEither = loginUseCase.login(loginCredentialInput);
        if (authenticationTokenEither.isLeft()) {
            return ResponseEntity.ok(authenticationAPIMapper.toResponse(authenticationTokenEither.getLeft()));
        } else {
            return handleErrorCodes(authenticationTokenEither.getRight());
        }
    }
}
