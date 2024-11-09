package com.cplerings.core.application.design.implementation;

import static com.cplerings.core.application.account.error.AccountErrorCode.ACCOUNT_WITH_EMAIL_NOT_FOUND;
import static com.cplerings.core.application.account.error.AccountErrorCode.UNAUTHENTICATED;

import java.util.Optional;

import com.cplerings.core.application.design.ViewDesignVersionsUseCase;
import com.cplerings.core.application.design.datasource.ViewDesignVersionsDataSource;
import com.cplerings.core.application.design.datasource.result.DesignVersions;
import com.cplerings.core.application.design.input.ViewDesignVersionsInput;
import com.cplerings.core.application.design.mapper.AViewDesignVersionsMapper;
import com.cplerings.core.application.design.output.ViewDesignVersionsOutput;
import com.cplerings.core.application.shared.service.security.CurrentUser;
import com.cplerings.core.application.shared.service.security.SecurityService;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.account.Role;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCaseImplementation
public class ViewDesignVersionsUseCaseImpl extends AbstractUseCase<ViewDesignVersionsInput, ViewDesignVersionsOutput> implements ViewDesignVersionsUseCase {

    private final AViewDesignVersionsMapper aViewDesignVersionsMapper;
    private final ViewDesignVersionsDataSource viewDesignVersionsDataSource;
    private final SecurityService securityService;

    @Override
    protected ViewDesignVersionsOutput internalExecute(UseCaseValidator validator, ViewDesignVersionsInput input) {
        final CurrentUser currentUser = securityService.getCurrentUser();
        validator.validateAndStopExecution(currentUser.authenticated(), UNAUTHENTICATED);
        final Optional<Account> accountOptional = viewDesignVersionsDataSource.findAccountByEmail(currentUser.email());
        validator.validateAndStopExecution(accountOptional.isPresent(), ACCOUNT_WITH_EMAIL_NOT_FOUND);
        final Account account = accountOptional.get();
        if (account.getRole() == Role.STAFF) {
            DesignVersions designVersions = viewDesignVersionsDataSource.findDesignVersions(input);
            return aViewDesignVersionsMapper.toOutput(designVersions);
        }
        DesignVersions designVersions = viewDesignVersionsDataSource.findDesignVersionsByCustomerId(account.getId(), input);
        return aViewDesignVersionsMapper.toOutput(designVersions);
    }
}
