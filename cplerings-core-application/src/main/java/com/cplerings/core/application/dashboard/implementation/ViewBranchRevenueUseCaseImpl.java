package com.cplerings.core.application.dashboard.implementation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.cplerings.core.application.dashboard.ViewBranchRevenueUseCase;
import com.cplerings.core.application.dashboard.datasource.ViewBranchRevenueDataSource;
import com.cplerings.core.application.dashboard.input.ViewBranchRevenueInput;
import com.cplerings.core.application.dashboard.output.ViewBranchRevenueOutput;
import com.cplerings.core.application.shared.service.security.SecurityService;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.shared.valueobject.Money;

import lombok.RequiredArgsConstructor;

@UseCaseImplementation
@RequiredArgsConstructor
public class ViewBranchRevenueUseCaseImpl extends AbstractUseCase<ViewBranchRevenueInput, ViewBranchRevenueOutput> implements ViewBranchRevenueUseCase {

    private final ViewBranchRevenueDataSource dataSource;
    private final SecurityService securityService;

    @Override
    protected ViewBranchRevenueOutput internalExecute(UseCaseValidator validator, ViewBranchRevenueInput input) {
        var user = securityService.getCurrentUser();
        Account manager = dataSource.getAccountById(user.id());
        var revenue = dataSource.getTotalRevenue(input.startDate(), input.endDate(), manager.getBranch().getId());
        BigDecimal totalRevenue = revenue.totalRevenue();
        Money totalRevenueMoney = Money.create(totalRevenue);
        List<Money> moneyList = new ArrayList<>();
        for(var eachRevenue : revenue.revenueForEach()) {
            Money revenueMoneyForEach = Money.create(eachRevenue);
            moneyList.add(revenueMoneyForEach);
        }
        return ViewBranchRevenueOutput.builder()
                .revenueForEach(moneyList)
                .totalRevenue(totalRevenueMoney)
                .build();
    }
}
