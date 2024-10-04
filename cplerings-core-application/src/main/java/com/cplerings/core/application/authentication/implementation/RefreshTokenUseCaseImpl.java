package com.cplerings.core.application.authentication.implementation;

import static com.cplerings.core.application.authentication.error.AuthenticationErrorCode.INVALID_TOKEN;
import static com.cplerings.core.application.authentication.error.AuthenticationErrorCode.NO_TOKEN;
import static com.cplerings.core.application.authentication.error.AuthenticationErrorCode.TOKEN_EXPIRED;

import org.apache.commons.lang3.StringUtils;

import com.cplerings.core.application.authentication.RefreshTokenUseCase;
import com.cplerings.core.application.authentication.input.RefreshTokenInput;
import com.cplerings.core.application.authentication.output.AuthenticationTokenOutput;
import com.cplerings.core.application.shared.service.jwt.JWTGenerationService;
import com.cplerings.core.application.shared.service.jwt.JWTVerificationResult;
import com.cplerings.core.application.shared.service.jwt.JWTVerificationService;
import com.cplerings.core.application.shared.usecase.AbstractNewUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;

import lombok.RequiredArgsConstructor;

@UseCaseImplementation
@RequiredArgsConstructor
public class RefreshTokenUseCaseImpl extends AbstractNewUseCase<RefreshTokenInput, AuthenticationTokenOutput>
        implements RefreshTokenUseCase {

    private final JWTVerificationService jwtVerificationService;
    private final JWTGenerationService jwtGenerationService;

    @Override
    protected AuthenticationTokenOutput internalExecute(UseCaseValidator validator, RefreshTokenInput input) {
        JWTVerificationResult result = jwtVerificationService.validateRefreshToken(input.refreshToken());
        if (result.getStatus() == JWTVerificationResult.Status.EXPIRED) {
            validator.validateAndStopExecution(false, TOKEN_EXPIRED);
        } else if (result.getStatus() == JWTVerificationResult.Status.INVALID) {
            validator.validateAndStopExecution(false, INVALID_TOKEN);
        }
        final String token = jwtGenerationService.generateToken(result.getSubject());
        final String refreshToken = jwtGenerationService.generateRefreshToken(result.getSubject());
        return AuthenticationTokenOutput.builder()
                .token(token)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    protected void validateInput(UseCaseValidator validator, RefreshTokenInput input) {
        super.validateInput(validator, input);
        validator.validateAndStopExecution(StringUtils.isNotBlank(input.refreshToken()), NO_TOKEN);
    }
}
