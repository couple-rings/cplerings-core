package com.cplerings.core.application.dashboard.implementation;

import com.cplerings.core.application.dashboard.ViewTotalPaymentPerOrderUseCase;
import com.cplerings.core.application.dashboard.datasource.ViewTotalPaymentPerOrderDataSource;
import com.cplerings.core.application.dashboard.input.ViewTotalPaymentPerOrderInput;
import com.cplerings.core.application.dashboard.output.ViewTotalPaymentPerOrderOutput;
import com.cplerings.core.application.shared.service.security.SecurityService;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.domain.account.Account;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCaseImplementation
public class ViewTotalPaymentPerOrderUseCaseImpl extends AbstractUseCase<ViewTotalPaymentPerOrderInput, ViewTotalPaymentPerOrderOutput> implements ViewTotalPaymentPerOrderUseCase {

    private final ViewTotalPaymentPerOrderDataSource dataSource;
    private final SecurityService securityService;

    @Override
    protected ViewTotalPaymentPerOrderOutput internalExecute(UseCaseValidator validator, ViewTotalPaymentPerOrderInput input) {
        var user = securityService.getCurrentUser();
        Account manager = dataSource.getAccountById(user.id());
        var result = dataSource.getTotalAmountPaymentWithOrderType(input, manager.getBranch().getId());
        return new ViewTotalPaymentPerOrderOutput(result);
    }
}
