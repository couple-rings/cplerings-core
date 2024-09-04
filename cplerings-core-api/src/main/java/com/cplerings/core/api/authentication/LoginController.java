package com.cplerings.core.api.authentication;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.AbstractRestController;
import com.cplerings.core.api.authentication.mapper.AuthenticationAPIMapper;
import com.cplerings.core.api.authentication.request.LoginCredentialRequest;
import com.cplerings.core.api.security.IsAnyone;
import com.cplerings.core.application.authentication.LoginUserStory;
import com.cplerings.core.application.authentication.input.LoginCredentialInput;
import com.cplerings.core.application.authentication.output.AuthenticationTokenOutput;
import com.cplerings.core.application.shared.usecase.ErrorCodes;
import com.cplerings.core.common.pair.Pair;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class LoginController extends AbstractRestController {

    private final LoginUserStory loginUserStory;
    private final AuthenticationAPIMapper authenticationAPIMapper;

    @PostMapping("/auth/login")
    @IsAnyone
    public ResponseEntity<Object> login(@RequestBody LoginCredentialRequest loginCredentialRequest) {
        final LoginCredentialInput loginCredentialInput = authenticationAPIMapper.toInput(loginCredentialRequest);
        final Pair<AuthenticationTokenOutput, ErrorCodes> authenticationTokenPair = loginUserStory.login(loginCredentialInput);
        if (authenticationTokenPair.isLeft()) {
            return ResponseEntity.ok(authenticationAPIMapper.toResponse(authenticationTokenPair.getLeft()));
        } else {
            return handleErrorCodes(authenticationTokenPair.getRight());
        }
    }
}
