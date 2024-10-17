package com.cplerings.core.application.account.implementation;

import static com.cplerings.core.application.account.error.AccountErrorCode.ACCOUNT_NOT_IN_ACTIVE_STATE;
import static com.cplerings.core.application.account.error.AccountErrorCode.ACCOUNT_WITH_EMAIL_NOT_FOUND;
import static com.cplerings.core.application.account.error.AccountErrorCode.UNAUTHENTICATED;

import com.cplerings.core.application.account.ViewCurrentProfileUseCase;
import com.cplerings.core.application.account.datasource.ViewCurrentProfileDataSource;
import com.cplerings.core.application.account.mapper.AViewCurrentProfileMapper;
import com.cplerings.core.application.account.output.ProfileOutput;
import com.cplerings.core.application.shared.input.NoInput;
import com.cplerings.core.application.shared.service.security.CurrentUser;
import com.cplerings.core.application.shared.service.security.SecurityService;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.account.AccountStatus;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

@UseCaseImplementation
@RequiredArgsConstructor
public class ViewCurrentProfileUseCaseImpl extends AbstractUseCase<NoInput, ProfileOutput> implements ViewCurrentProfileUseCase {

    private final ViewCurrentProfileDataSource viewCurrentProfileDataSource;
    private final AViewCurrentProfileMapper aViewCurrentProfileMapper;
    private final SecurityService securityService;

    @Override
    protected ProfileOutput internalExecute(UseCaseValidator validator, NoInput input) {
        final CurrentUser currentUser = securityService.getCurrentUser();
        validator.validateAndStopExecution(currentUser.authenticated(), UNAUTHENTICATED);
        final Optional<Account> accountOptional = viewCurrentProfileDataSource.findAccountByEmail(currentUser.email());
        validator.validateAndStopExecution(accountOptional.isPresent(), ACCOUNT_WITH_EMAIL_NOT_FOUND);
        final Account account = accountOptional.get();
        validator.validateAndStopExecution(account.getStatus() == AccountStatus.ACTIVE, ACCOUNT_NOT_IN_ACTIVE_STATE);
        return aViewCurrentProfileMapper.toOutput(account);
    }
}
