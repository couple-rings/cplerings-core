package com.cplerings.core.application.dashboard.implementation;

import com.cplerings.core.application.dashboard.ViewBranchOrdersPaginateUseCase;
import com.cplerings.core.application.dashboard.datasource.ViewBranchOrdersPaginateDataSource;
import com.cplerings.core.application.dashboard.input.ViewBranchOrdersPaginateInput;
import com.cplerings.core.application.dashboard.output.ViewBranchOrdersOutput;
import com.cplerings.core.application.dashboard.output.ViewBranchOrdersPaginateOutput;
import com.cplerings.core.application.shared.service.security.SecurityService;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.domain.account.Account;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCaseImplementation
public class ViewBranchOrdersPaginateUseCaseImpl extends AbstractUseCase<ViewBranchOrdersPaginateInput, ViewBranchOrdersPaginateOutput> implements ViewBranchOrdersPaginateUseCase {

    private final ViewBranchOrdersPaginateDataSource dataSource;
    private final SecurityService securityService;

    @Override
    protected ViewBranchOrdersPaginateOutput internalExecute(UseCaseValidator validator, ViewBranchOrdersPaginateInput input) {
        var user = securityService.getCurrentUser();
        Account manager = dataSource.getAccountById(user.id());
        var orders = dataSource.getAllTypeOrders(input, manager.getBranch().getId());
        return ViewBranchOrdersPaginateOutput
                .builder()
                .orders(orders)
                .build();
    }
}
