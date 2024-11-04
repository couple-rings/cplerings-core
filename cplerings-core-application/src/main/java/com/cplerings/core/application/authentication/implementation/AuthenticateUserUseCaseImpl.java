package com.cplerings.core.application.authentication.implementation;

import static com.cplerings.core.application.authentication.error.AuthenticationErrorCode.ACCOUNT_NOT_ACTIVE;
import static com.cplerings.core.application.authentication.error.AuthenticationErrorCode.ACCOUNT_WITH_EMAIL_NOT_FOUND;
import static com.cplerings.core.application.authentication.error.AuthenticationErrorCode.INVALID_TOKEN;
import static com.cplerings.core.application.authentication.error.AuthenticationErrorCode.NO_TOKEN;
import static com.cplerings.core.application.authentication.error.AuthenticationErrorCode.TOKEN_EXPIRED;

import com.cplerings.core.application.authentication.AuthenticateUserUseCase;
import com.cplerings.core.application.authentication.datasource.AuthenticateUserDataSource;
import com.cplerings.core.application.authentication.input.JWTInput;
import com.cplerings.core.application.authentication.mapper.AAuthenticateUserMapper;
import com.cplerings.core.application.authentication.output.AuthenticatedAccountOutput;
import com.cplerings.core.application.shared.service.jwt.JWTVerificationResult;
import com.cplerings.core.application.shared.service.jwt.JWTVerificationService;
import com.cplerings.core.application.shared.transaction.SessionInformation;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.account.AccountStatus;

import lombok.RequiredArgsConstructor;

import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

@UseCaseImplementation
@RequiredArgsConstructor
public class AuthenticateUserUseCaseImpl extends AbstractUseCase<JWTInput, AuthenticatedAccountOutput>
        implements AuthenticateUserUseCase {

    private final JWTVerificationService jwtVerificationService;
    private final AuthenticateUserDataSource dataSource;
    private final AAuthenticateUserMapper mapper;

    @Override
    protected SessionInformation customizeSessionInformation() {
        return SessionInformation.DEFAULT_QUERY_ONLY;
    }

    @Override
    protected void validateInput(UseCaseValidator validator, JWTInput input) {
        super.validateInput(validator, input);
        validator.validate(StringUtils.isNotBlank(input.token()), NO_TOKEN);
    }

    @Override
    protected AuthenticatedAccountOutput internalExecute(UseCaseValidator validator, JWTInput input) {
        final JWTVerificationResult result = jwtVerificationService.validateToken(input.token());
        if (JWTVerificationResult.Status.INVALID == result.getStatus()) {
            validator.validateAndStopExecution(false, INVALID_TOKEN);
        } else if (JWTVerificationResult.Status.EXPIRED == result.getStatus()) {
            validator.validateAndStopExecution(false, TOKEN_EXPIRED);
        }
        final Optional<Account> authenticatedAccount = dataSource.getAuthenticatedAccount(result.getSubject());
        validator.validateAndStopExecution(authenticatedAccount.isPresent(), ACCOUNT_WITH_EMAIL_NOT_FOUND);
        validator.validateAndStopExecution(authenticatedAccount.get().getStatus() == AccountStatus.ACTIVE, ACCOUNT_NOT_ACTIVE);
        return mapper.toOutput(authenticatedAccount.get());
    }
}
