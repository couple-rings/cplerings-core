package com.cplerings.core.application.dashboard.implementation;

import com.cplerings.core.application.dashboard.ViewTotalExpenditureForAllUseCase;
import com.cplerings.core.application.dashboard.datasource.ViewTotalExpenditureForAllDataSource;
import com.cplerings.core.application.dashboard.output.ViewTotalExpenditureForAllOutput;
import com.cplerings.core.application.shared.input.NoInput;
import com.cplerings.core.application.shared.service.security.SecurityService;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.domain.account.Account;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCaseImplementation
public class ViewTotalExpenditureForAllUseCaseImpl extends AbstractUseCase<NoInput, ViewTotalExpenditureForAllOutput> implements ViewTotalExpenditureForAllUseCase {

    private final ViewTotalExpenditureForAllDataSource dataSource;
    private final SecurityService securityService;

    @Override
    protected ViewTotalExpenditureForAllOutput internalExecute(UseCaseValidator validator, NoInput input) {
        var user = securityService.getCurrentUser();
        Account manager = dataSource.getAccountById(user.id());
        var result = dataSource.getTotalExpenditureForAll(manager.getBranch().getId());
        return result;
    }
}
