package com.cplerings.core.application.authentication.implementation;

import static com.cplerings.core.application.authentication.error.AuthenticationErrorCode.ACCOUNT_WITH_EMAIL_NOT_FOUND;
import static com.cplerings.core.application.authentication.error.AuthenticationErrorCode.INVALID_PASSWORD;
import static com.cplerings.core.application.authentication.error.AuthenticationErrorCode.NO_EMAIL;
import static com.cplerings.core.application.authentication.error.AuthenticationErrorCode.NO_PASSWORD;

import com.cplerings.core.application.authentication.LoginUseCase;
import com.cplerings.core.application.authentication.datasource.LoginDataSource;
import com.cplerings.core.application.authentication.input.LoginCredentialInput;
import com.cplerings.core.application.authentication.output.AuthenticationTokenOutput;
import com.cplerings.core.application.shared.service.jwt.JWTGenerationService;
import com.cplerings.core.application.shared.service.password.PasswordService;
import com.cplerings.core.application.shared.transaction.SessionInformation;
import com.cplerings.core.application.shared.usecase.AbstractNewUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.domain.account.Account;

import lombok.RequiredArgsConstructor;

import org.apache.commons.lang3.StringUtils;

@UseCaseImplementation
@RequiredArgsConstructor
public class LoginUseCaseImpl extends AbstractNewUseCase<LoginCredentialInput, AuthenticationTokenOutput>
        implements LoginUseCase {

    private final LoginDataSource loginDataSource;
    private final PasswordService passwordService;
    private final JWTGenerationService jwtGenerationService;

    @Override
    protected void validateInput(UseCaseValidator validator, LoginCredentialInput input) {
        super.validateInput(validator, input);
        validator.validate(StringUtils.isNotBlank(input.getEmail()), NO_EMAIL);
        validator.validate(StringUtils.isNotBlank(input.getPassword()), NO_PASSWORD);
    }

    @Override
    protected AuthenticationTokenOutput internalExecute(UseCaseValidator validator, LoginCredentialInput input) {
        final Account loginAccount = loginDataSource.getLoginAccount(input.getEmail())
                .orElse(null);
        validator.validateAndStopExecution(loginAccount != null, ACCOUNT_WITH_EMAIL_NOT_FOUND);
        validator.validateAndStopExecution(passwordService.passwordMatchesEncrypted(input.getPassword(), loginAccount.getPassword()),
                INVALID_PASSWORD);
        final String email = loginAccount.getEmail();
        final String token = jwtGenerationService.generateToken(email);
        final String refreshToken = jwtGenerationService.generateRefreshToken(email);
        return new AuthenticationTokenOutput(token, refreshToken);
    }

    @Override
    protected SessionInformation customizeSessionInformation() {
        return SessionInformation.DEFAULT_QUERY_ONLY;
    }
}
