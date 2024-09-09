package com.cplerings.core.application.authentication.implementation;

import com.cplerings.core.application.authentication.AuthenticateUserJWTUseCase;
import com.cplerings.core.application.authentication.datasource.AuthenticateUserJWTDataSource;
import com.cplerings.core.application.authentication.error.AuthenticationErrorCode;
import com.cplerings.core.application.authentication.input.JWTInput;
import com.cplerings.core.application.authentication.mapper.AccountApplicationMapper;
import com.cplerings.core.application.authentication.output.AccountOutput;
import com.cplerings.core.application.shared.errorcode.ErrorCodes;
import com.cplerings.core.application.shared.service.jwt.JWTVerificationResult;
import com.cplerings.core.application.shared.service.jwt.JWTVerificationService;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.common.either.Either;
import com.cplerings.core.domain.account.Account;

import lombok.RequiredArgsConstructor;

import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

@UseCaseImplementation
@RequiredArgsConstructor
public class DefaultAuthenticateUserJWTUseCase extends AbstractUseCase<JWTInput, AccountOutput>
        implements AuthenticateUserJWTUseCase {

    private final JWTVerificationService jwtVerificationService;
    private final AuthenticateUserJWTDataSource dataSource;
    private final AccountApplicationMapper mapper;

    @Override
    public Either<AccountOutput, ErrorCodes> authenticate(JWTInput input) {
        addStep(AbstractUseCase.<JWTInput, JWTVerificationResult>createStep(i -> {
            final JWTVerificationResult result = jwtVerificationService.validateToken(i.token());
            if (JWTVerificationResult.Status.INVALID == result.getStatus()) {
                validate(false, AuthenticationErrorCode.INVALID_TOKEN);
            } else if (JWTVerificationResult.Status.EXPIRED == result.getStatus()) {
                validate(false, AuthenticationErrorCode.TOKEN_EXPIRED);
            }
            return result;
        }));
        addStep(AbstractUseCase.<JWTVerificationResult, AccountOutput>createStep(result -> {
            final Optional<Account> authenticatedAccount = dataSource.getAuthenticatedAccount(result.getSubject());
            if (authenticatedAccount.isPresent()) {
                return mapper.toOutput(authenticatedAccount.get());
            }
            validate(false, AuthenticationErrorCode.ACCOUNT_WITH_EMAIL_NOT_FOUND);
            return null;
        }));
        return executeSteps(input);
    }

    @Override
    protected void validateInput(JWTInput input) {
        super.validateInput(input);
        validate(StringUtils.isNotBlank(input.token()), AuthenticationErrorCode.NO_TOKEN);
    }
}
