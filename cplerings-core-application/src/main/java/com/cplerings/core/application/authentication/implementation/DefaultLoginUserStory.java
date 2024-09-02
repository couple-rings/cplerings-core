package com.cplerings.core.application.authentication.implementation;

import java.util.Optional;

import com.cplerings.core.application.authentication.LoginUserStory;
import com.cplerings.core.application.authentication.datasource.LoginDataSource;
import com.cplerings.core.application.authentication.error.AuthenticationErrorCode;
import com.cplerings.core.application.authentication.input.LoginCredentialInput;
import com.cplerings.core.application.authentication.output.AuthenticationTokenOutput;
import com.cplerings.core.application.shared.password.PasswordService;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.ErrorCodes;
import com.cplerings.core.application.shared.usecase.UserStoryImplementation;
import com.cplerings.core.common.pair.Pair;
import com.cplerings.core.domain.account.Account;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@UserStoryImplementation
@RequiredArgsConstructor
public class DefaultLoginUserStory
        extends AbstractUseCase<LoginCredentialInput, AuthenticationTokenOutput, AuthenticationErrorCode>
        implements LoginUserStory {

    private final LoginDataSource loginDataSource;
    private final PasswordService passwordService;

    @Override
    public Pair<AuthenticationTokenOutput, ErrorCodes> login(LoginCredentialInput input) {
        addStep(AbstractUseCase.<LoginCredentialInput, Account>createStep(i -> {
            final Optional<Account> loginAccount = loginDataSource.getLoginAccount(input.getEmail());
            validate(loginAccount.isPresent(), AuthenticationErrorCode.EMAIL_NOT_FOUND);
            return loginAccount.orElse(null);
        }));
        addStep(AbstractUseCase.<Account, Account>createStep(account -> {
            validate(passwordService.passwordMatchesEncrypted(input.getPassword(), account.getPassword()),
                    AuthenticationErrorCode.INVALID_PASSWORD);
            return account;
        }));
        return executeSteps(input);
    }

    @Override
    protected void validateInputInternal(LoginCredentialInput input) {
        super.validateInputInternal(input);
        validate(StringUtils.isNotBlank(input.getEmail()), AuthenticationErrorCode.NO_EMAIL);
        validate(StringUtils.isNotBlank(input.getPassword()), AuthenticationErrorCode.NO_PASSWORD);
    }
}
