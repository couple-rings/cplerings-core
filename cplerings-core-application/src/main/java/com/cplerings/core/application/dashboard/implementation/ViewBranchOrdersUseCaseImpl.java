package com.cplerings.core.application.dashboard.implementation;

import com.cplerings.core.application.dashboard.ViewBranchOrdersUseCase;
import com.cplerings.core.application.dashboard.datasource.ViewBranchOrdersDataSource;
import com.cplerings.core.application.dashboard.input.ViewBranchOrdersInput;
import com.cplerings.core.application.dashboard.output.ViewBranchOrdersOutput;
import com.cplerings.core.application.shared.service.security.SecurityService;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.domain.account.Account;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCaseImplementation
public class ViewBranchOrdersUseCaseImpl extends AbstractUseCase<ViewBranchOrdersInput, ViewBranchOrdersOutput> implements ViewBranchOrdersUseCase {

    private final ViewBranchOrdersDataSource dataSource;
    private final SecurityService securityService;

    @Override
    protected ViewBranchOrdersOutput internalExecute(UseCaseValidator validator, ViewBranchOrdersInput input) {
        var user = securityService.getCurrentUser();
        Account manager = dataSource.getAccountById(user.id());
        var orders = dataSource.getOrders(input.startDate(), input.endDate(), manager.getBranch().getId());
        return ViewBranchOrdersOutput
                .builder()
                .orders(orders)
                .build();
    }
}
