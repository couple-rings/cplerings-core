package com.cplerings.core.application.dashboard.implementation;

import com.cplerings.core.application.dashboard.ViewTotalTransactionsOfBranchUseCase;
import com.cplerings.core.application.dashboard.datasource.ViewTotalTransactionsOfBranchDataSource;
import com.cplerings.core.application.dashboard.output.ViewTotalTransactionsOfBranchOutput;
import com.cplerings.core.application.shared.input.NoInput;
import com.cplerings.core.application.shared.service.security.SecurityService;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.domain.account.Account;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCaseImplementation
public class ViewTotalTransactionsOfBranchUseCaseImpl extends AbstractUseCase<NoInput, ViewTotalTransactionsOfBranchOutput> implements ViewTotalTransactionsOfBranchUseCase {

    private final ViewTotalTransactionsOfBranchDataSource dataSource;
    private final SecurityService securityService;

    @Override
    protected ViewTotalTransactionsOfBranchOutput internalExecute(UseCaseValidator validator, NoInput input) {
        var user = securityService.getCurrentUser();
        Account manager = dataSource.getAccountById(user.id());
        var result = dataSource.getTotalTransaction(manager.getBranch().getId());
        return new ViewTotalTransactionsOfBranchOutput(result);
    }
}
