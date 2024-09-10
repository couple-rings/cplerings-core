package com.cplerings.core.api.authentication;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.AbstractRestController;
import com.cplerings.core.api.authentication.mapper.AuthenticationAPIMapper;
import com.cplerings.core.api.authentication.request.LoginCredentialRequest;
import com.cplerings.core.api.security.IsAnyone;
import com.cplerings.core.application.authentication.LoginUseCase;
import com.cplerings.core.application.authentication.input.LoginCredentialInput;
import com.cplerings.core.application.authentication.output.AuthenticationTokenOutput;
import com.cplerings.core.application.shared.errorcode.ErrorCodes;
import com.cplerings.core.common.either.Either;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class LoginController extends AbstractRestController {

    private final LoginUseCase loginUseCase;
    private final AuthenticationAPIMapper authenticationAPIMapper;

    @PostMapping("/auth/login")
    @IsAnyone
    @Operation(summary = "Login into the app and receive Authentication JWT")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Login credential",
            required = true,
            content = @Content(schema = @Schema(implementation = LoginCredentialRequest.class))
    )
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
