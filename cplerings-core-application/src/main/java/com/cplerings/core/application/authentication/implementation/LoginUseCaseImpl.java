package com.cplerings.core.application.authentication.implementation;

import static com.cplerings.core.application.authentication.error.AuthenticationErrorCode.ACCOUNT_NOT_DISABLED;
import static com.cplerings.core.application.authentication.error.AuthenticationErrorCode.ACCOUNT_NOT_VERIFIED;
import static com.cplerings.core.application.authentication.error.AuthenticationErrorCode.ACCOUNT_WITH_EMAIL_NOT_FOUND;
import static com.cplerings.core.application.authentication.error.AuthenticationErrorCode.INVALID_PASSWORD;
import static com.cplerings.core.application.authentication.error.AuthenticationErrorCode.NO_EMAIL;
import static com.cplerings.core.application.authentication.error.AuthenticationErrorCode.NO_PASSWORD;

import com.cplerings.core.application.authentication.LoginUseCase;
import com.cplerings.core.application.authentication.datasource.LoginDataSource;
import com.cplerings.core.application.authentication.input.LoginCredentialInput;
import com.cplerings.core.application.authentication.output.AuthenticationTokenOutput;
import com.cplerings.core.application.shared.mapper.ARoleMapper;
import com.cplerings.core.application.shared.service.jwt.JWTGenerationService;
import com.cplerings.core.application.shared.service.jwt.input.JWTGenerationInput;
import com.cplerings.core.application.shared.service.password.PasswordService;
import com.cplerings.core.application.shared.transaction.SessionInformation;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.account.AccountStatus;
import com.cplerings.core.domain.account.Role;

import lombok.RequiredArgsConstructor;

import org.apache.commons.lang3.StringUtils;

@UseCaseImplementation
@RequiredArgsConstructor
public class LoginUseCaseImpl extends AbstractUseCase<LoginCredentialInput, AuthenticationTokenOutput>
        implements LoginUseCase {

    private final LoginDataSource loginDataSource;
    private final PasswordService passwordService;
    private final JWTGenerationService jwtGenerationService;
    private final ARoleMapper aRoleMapper;

    @Override
    protected SessionInformation customizeSessionInformation() {
        return SessionInformation.DEFAULT_QUERY_ONLY;
    }

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
        validator.validateAndStopExecution(loginAccount.getStatus() != AccountStatus.INACTIVE, ACCOUNT_NOT_DISABLED);
        validator.validateAndStopExecution(loginAccount.getStatus() != AccountStatus.VERIFYING, ACCOUNT_NOT_VERIFIED);
        validator.validateAndStopExecution(passwordService.passwordMatchesEncrypted(input.getPassword(),
                        loginAccount.getPassword()),
                INVALID_PASSWORD);
        final String email = loginAccount.getEmail();
        final Role role = loginAccount.getRole();
        final Long accountId = loginAccount.getId();
        final JWTGenerationInput jwtGenerationInput = JWTGenerationInput.builder()
                .email(email)
                .accountId(accountId)
                .role(aRoleMapper.toRole(role))
                .build();
        final String token = jwtGenerationService.generateToken(jwtGenerationInput);
        final String refreshToken = jwtGenerationService.generateRefreshToken(jwtGenerationInput);
        return new AuthenticationTokenOutput(token, refreshToken);
    }
}
