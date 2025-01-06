package com.cplerings.core.application.dashboard.implementation;

import com.cplerings.core.application.dashboard.ViewTotalExpenditureUseCase;
import com.cplerings.core.application.dashboard.datasource.ViewTotalExpenditureDataSource;
import com.cplerings.core.application.dashboard.input.ViewTotalExpenditureInput;
import com.cplerings.core.application.dashboard.output.ViewTotalExpenditureOutput;
import com.cplerings.core.application.shared.service.security.SecurityService;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.domain.account.Account;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCaseImplementation
public class ViewTotalExpenditureUseCaseImpl extends AbstractUseCase<ViewTotalExpenditureInput, ViewTotalExpenditureOutput> implements ViewTotalExpenditureUseCase {

    private final ViewTotalExpenditureDataSource dataSource;
    private final SecurityService securityService;

    @Override
    protected ViewTotalExpenditureOutput internalExecute(UseCaseValidator validator, ViewTotalExpenditureInput input) {
        var user = securityService.getCurrentUser();
        Account manager = dataSource.getAccountById(user.id());
        var result = dataSource.getTotalExpenditure(input.startDate(), input.endDate(), manager.getBranch().getId());
        return result;
    }
}
