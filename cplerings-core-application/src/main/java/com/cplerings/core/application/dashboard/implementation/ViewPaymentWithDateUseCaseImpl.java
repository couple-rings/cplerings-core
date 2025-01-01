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
        List<PaymentOrder> paymentOrders = new ArrayList<>();
        for (var payemnt : result.getPayments()) {
            PaymentOrder paymentOrder = PaymentOrder.builder()
                    .paymentId(payemnt.getId())
                    .orderNo(payemnt.getCraftingStage().getCustomOrder().getOrderNo())
                    .orderType(OrderTypeStatistic.CUSTOM)
                    .amount(payemnt.getAmount())
                    .paymentMethod(APaymentMethod.TRANSFER)
                    .createdAt(payemnt.getCreatedAt())
                    .build();
            paymentOrders.add(paymentOrder);
        }
        PaymentOrders payments = PaymentOrders.builder()
                .payments(paymentOrders)
                .count(result.getCount())
                .pageSize(result.getPageSize())
                .page(result.getPage())
                .build();
        return mapper.toOutput(payments);
    }
}
