package com.cplerings.core.application.dashboard.implementation;

import com.cplerings.core.application.dashboard.ViewTotalRevenueUseCase;
import com.cplerings.core.application.dashboard.datasource.ViewTotalRevenueDataSource;
import com.cplerings.core.application.dashboard.output.ViewTotalRevenueOutput;
import com.cplerings.core.application.shared.input.NoInput;
import com.cplerings.core.application.shared.service.security.SecurityService;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.shared.valueobject.Money;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCaseImplementation
public class ViewTotalRevenueUseCaseImpl extends AbstractUseCase<NoInput, ViewTotalRevenueOutput> implements ViewTotalRevenueUseCase {

    private final ViewTotalRevenueDataSource dataSource;
    private final SecurityService securityService;

    @Override
    protected ViewTotalRevenueOutput internalExecute(UseCaseValidator validator, NoInput input) {
        var user = securityService.getCurrentUser();
        Account manager = dataSource.getAccountById(user.id());
        var result = dataSource.getTotalRevenue(manager.getBranch().getId());
        Money totalRevenue = Money.create(result);
        return new ViewTotalRevenueOutput(totalRevenue);
    }
}
