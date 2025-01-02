package com.cplerings.core.application.dashboard.implementation;

import com.cplerings.core.application.dashboard.ViewTotalOrdersOfBranchUseCase;
import com.cplerings.core.application.dashboard.datasource.ViewTotalOrdersOfBranchDataSource;
import com.cplerings.core.application.dashboard.output.ViewTotalOrdersOfBranchOutput;
import com.cplerings.core.application.shared.input.NoInput;
import com.cplerings.core.application.shared.service.security.SecurityService;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.domain.account.Account;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCaseImplementation
public class ViewTotalOrdersOfBranchUseCaseImpl extends AbstractUseCase<NoInput, ViewTotalOrdersOfBranchOutput> implements ViewTotalOrdersOfBranchUseCase {

    private final ViewTotalOrdersOfBranchDataSource dataSource;
    private final SecurityService securityService;

    @Override
    protected ViewTotalOrdersOfBranchOutput internalExecute(UseCaseValidator validator, NoInput input) {
        var user = securityService.getCurrentUser();
        Account manager = dataSource.getAccountById(user.id());
        var result = dataSource.getTotalOrders(manager.getBranch().getId());
        return new ViewTotalOrdersOfBranchOutput(result);
    }
}
