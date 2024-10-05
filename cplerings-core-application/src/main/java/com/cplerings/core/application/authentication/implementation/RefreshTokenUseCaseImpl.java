package com.cplerings.core.application.authentication.implementation;

import static com.cplerings.core.application.authentication.error.AuthenticationErrorCode.INVALID_ACCOUNT_FROM_TOKEN;
import static com.cplerings.core.application.authentication.error.AuthenticationErrorCode.INVALID_TOKEN;
import static com.cplerings.core.application.authentication.error.AuthenticationErrorCode.NO_TOKEN;
import static com.cplerings.core.application.authentication.error.AuthenticationErrorCode.TOKEN_EXPIRED;

import com.cplerings.core.application.authentication.RefreshTokenUseCase;
import com.cplerings.core.application.authentication.datasource.RefreshTokenDataSource;
import com.cplerings.core.application.authentication.input.RefreshTokenInput;
import com.cplerings.core.application.authentication.output.AuthenticationTokenOutput;
import com.cplerings.core.application.shared.mapper.ARoleMapper;
import com.cplerings.core.application.shared.service.jwt.JWTGenerationService;
import com.cplerings.core.application.shared.service.jwt.JWTVerificationResult;
import com.cplerings.core.application.shared.service.jwt.JWTVerificationService;
import com.cplerings.core.application.shared.service.jwt.input.JWTGenerationInput;
import com.cplerings.core.application.shared.usecase.AbstractNewUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.account.Role;

import lombok.RequiredArgsConstructor;

import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

@UseCaseImplementation
@RequiredArgsConstructor
public class RefreshTokenUseCaseImpl extends AbstractNewUseCase<RefreshTokenInput, AuthenticationTokenOutput>
        implements RefreshTokenUseCase {

    private final JWTVerificationService jwtVerificationService;
    private final JWTGenerationService jwtGenerationService;
    private final RefreshTokenDataSource refreshTokenDataSource;
    private final ARoleMapper aRoleMapper;

    @Override
    protected void validateInput(UseCaseValidator validator, RefreshTokenInput input) {
        super.validateInput(validator, input);
        validator.validateAndStopExecution(StringUtils.isNotBlank(input.refreshToken()), NO_TOKEN);
    }

    @Override
    protected AuthenticationTokenOutput internalExecute(UseCaseValidator validator, RefreshTokenInput input) {
        JWTVerificationResult result = jwtVerificationService.validateRefreshToken(input.refreshToken());
        if (result.getStatus() == JWTVerificationResult.Status.EXPIRED) {
            validator.validateAndStopExecution(false, TOKEN_EXPIRED);
        } else if (result.getStatus() == JWTVerificationResult.Status.INVALID) {
            validator.validateAndStopExecution(false, INVALID_TOKEN);
        }
        final String email = result.getSubject();
        Optional<Account> account = refreshTokenDataSource.getAccountFromToken(email);
        validator.validateAndStopExecution(account.isPresent(), INVALID_ACCOUNT_FROM_TOKEN);
        final Role role = account.get().getRole();
        final Long accountId = account.get().getId();
        final JWTGenerationInput jwtGenerationInput = JWTGenerationInput.builder()
                .accountId(accountId)
                .role(aRoleMapper.toRole(role))
                .email(email)
                .build();
        final String token = jwtGenerationService.generateToken(jwtGenerationInput);
        final String refreshToken = jwtGenerationService.generateRefreshToken(jwtGenerationInput);
        return AuthenticationTokenOutput.builder()
                .token(token)
                .refreshToken(refreshToken)
                .build();
    }
}
