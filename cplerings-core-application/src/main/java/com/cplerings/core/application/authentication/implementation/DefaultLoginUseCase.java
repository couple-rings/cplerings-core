package com.cplerings.core.application.authentication.implementation;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import com.cplerings.core.application.authentication.LoginUseCase;
import com.cplerings.core.application.authentication.datasource.LoginDataSource;
import com.cplerings.core.application.authentication.error.AuthenticationErrorCode;
import com.cplerings.core.application.authentication.input.LoginCredentialInput;
import com.cplerings.core.application.authentication.output.AuthenticationTokenOutput;
import com.cplerings.core.application.shared.service.jwt.JWTGenerationService;
import com.cplerings.core.application.shared.service.password.PasswordService;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.ErrorCodes;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.common.pair.Pair;
import com.cplerings.core.domain.account.Account;

import lombok.RequiredArgsConstructor;

@UseCaseImplementation
@RequiredArgsConstructor
public class DefaultLoginUseCase extends AbstractUseCase<LoginCredentialInput, AuthenticationTokenOutput>
        implements LoginUseCase {

    private final LoginDataSource loginDataSource;
    private final PasswordService passwordService;
    private final JWTGenerationService jwtGenerationService;

    @Override
    public Pair<AuthenticationTokenOutput, ErrorCodes> login(LoginCredentialInput input) {
        addStep(AbstractUseCase.<LoginCredentialInput, Account>createStep(i -> {
            final Optional<Account> loginAccount = loginDataSource.getLoginAccount(input.getEmail());
            validate(loginAccount.isPresent(), AuthenticationErrorCode.ACCOUNT_WITH_EMAIL_NOT_FOUND);
            return loginAccount.orElse(null);
        }));
        addStep(AbstractUseCase.<Account, AuthenticationTokenOutput>createStep(account -> {
            if (!validate(passwordService.passwordMatchesEncrypted(input.getPassword(), account.getPassword()),
                    AuthenticationErrorCode.INVALID_PASSWORD)) {
                return null;
            }
            final String email = account.getEmail();
            final String token = jwtGenerationService.generateToken(email);
            final String refreshToken = jwtGenerationService.generateRefreshToken(email);
            return new AuthenticationTokenOutput(token, refreshToken);
        }));
        return executeSteps(input);
    }

    @Override
    protected void validateInput(LoginCredentialInput input) {
        super.validateInput(input);
        validate(StringUtils.isNotBlank(input.getEmail()), AuthenticationErrorCode.NO_EMAIL);
        validate(StringUtils.isNotBlank(input.getPassword()), AuthenticationErrorCode.NO_PASSWORD);
    }
}
