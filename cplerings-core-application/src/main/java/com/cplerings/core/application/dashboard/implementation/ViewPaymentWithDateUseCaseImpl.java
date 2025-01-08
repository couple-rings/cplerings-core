package com.cplerings.core.application.dashboard.implementation;

import java.util.ArrayList;
import java.util.List;

import com.cplerings.core.application.dashboard.ViewPaymentWithDateUseCase;
import com.cplerings.core.application.dashboard.datasource.ViewPaymentWithDateDataSource;
import com.cplerings.core.application.dashboard.datasource.data.OrderTypeStatistic;
import com.cplerings.core.application.dashboard.datasource.data.PaymentOrder;
import com.cplerings.core.application.dashboard.datasource.data.PaymentOrders;
import com.cplerings.core.application.dashboard.input.ViewPaymentWithDateInput;
import com.cplerings.core.application.dashboard.mapper.AViewPaymentWithDateMapper;
import com.cplerings.core.application.dashboard.output.ViewPaymentWithDateOutput;
import com.cplerings.core.application.shared.entity.order.APaymentMethod;
import com.cplerings.core.application.shared.service.security.SecurityService;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.domain.account.Account;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCaseImplementation
public class ViewPaymentWithDateUseCaseImpl extends AbstractUseCase<ViewPaymentWithDateInput, ViewPaymentWithDateOutput> implements ViewPaymentWithDateUseCase {

    private final ViewPaymentWithDateDataSource dataSource;
    private final SecurityService securityService;
    private final AViewPaymentWithDateMapper mapper;

    @Override
    protected ViewPaymentWithDateOutput internalExecute(UseCaseValidator validator, ViewPaymentWithDateInput input) {
        var user = securityService.getCurrentUser();
        Account manager = dataSource.getAccountById(user.id());
        var result = dataSource.getPayments(input, manager.getBranch().getId());
        return mapper.toOutput(result);
    }
}
