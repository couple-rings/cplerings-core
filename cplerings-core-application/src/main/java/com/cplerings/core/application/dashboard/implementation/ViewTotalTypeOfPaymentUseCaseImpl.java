package com.cplerings.core.application.dashboard.implementation;

import com.cplerings.core.application.dashboard.ViewTotalTypeOfPaymentUseCase;
import com.cplerings.core.application.dashboard.datasource.ViewTotalTypeOfPaymentDataSource;
import com.cplerings.core.application.dashboard.input.ViewTotalTypeOfPaymentInput;
import com.cplerings.core.application.dashboard.output.ViewTotalTypeOfPaymentOutput;
import com.cplerings.core.application.shared.service.security.SecurityService;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.domain.account.Account;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCaseImplementation
public class ViewTotalTypeOfPaymentUseCaseImpl extends AbstractUseCase<ViewTotalTypeOfPaymentInput, ViewTotalTypeOfPaymentOutput> implements ViewTotalTypeOfPaymentUseCase {

    private final ViewTotalTypeOfPaymentDataSource dataSource;
    private final SecurityService securityService;

    @Override
    protected ViewTotalTypeOfPaymentOutput internalExecute(UseCaseValidator validator, ViewTotalTypeOfPaymentInput input) {
        var user = securityService.getCurrentUser();
        Account manager = dataSource.getAccountById(user.id());
        var result = dataSource.getTotalTypeOfPayment(input, manager.getBranch().getId());
        return result;
    }
}
