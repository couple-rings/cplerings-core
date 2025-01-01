package com.cplerings.core.application.dashboard.implementation;

import java.util.ArrayList;
import java.util.List;

import com.cplerings.core.application.dashboard.ViewRefundOrdersWithDateUseCase;
import com.cplerings.core.application.dashboard.datasource.ViewRefundOrdersWithDateDataSource;
import com.cplerings.core.application.dashboard.datasource.data.CombinedOrder;
import com.cplerings.core.application.dashboard.datasource.data.CombinedOrders;
import com.cplerings.core.application.dashboard.datasource.data.OrderTypeStatistic;
import com.cplerings.core.application.dashboard.input.ViewRefundOrdersWithDateInput;
import com.cplerings.core.application.dashboard.mapper.AViewRefundOrdersWithDateMapper;
import com.cplerings.core.application.dashboard.output.ViewRefundOrdersWithDateOutput;
import com.cplerings.core.application.shared.entity.order.APaymentMethod;
import com.cplerings.core.application.shared.service.security.SecurityService;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.refund.RefundMethod;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCaseImplementation
public class ViewRefundOrdersWithDateUseCaseImpl extends AbstractUseCase<ViewRefundOrdersWithDateInput, ViewRefundOrdersWithDateOutput> implements ViewRefundOrdersWithDateUseCase {

    private final ViewRefundOrdersWithDateDataSource dataSource;
    private final SecurityService securityService;
    private final AViewRefundOrdersWithDateMapper aViewRefundOrdersWithDateMapper;

    @Override
    protected ViewRefundOrdersWithDateOutput internalExecute(UseCaseValidator validator, ViewRefundOrdersWithDateInput input) {
        var user = securityService.getCurrentUser();
        Account manager = dataSource.getAccountById(user.id());
        var result = dataSource.getRefundOrders(input, manager.getId());
        List<CombinedOrder> orders = new ArrayList<>();
        for (var resellOrder : result.getRefunds()) {
            CombinedOrder combinedOrder = CombinedOrder.builder()
                    .orderId(resellOrder.getId())
                    .orderNo(resellOrder.getOrderNo())
                    .orderType(OrderTypeStatistic.REFUND)
                    .amount(resellOrder.getAmount())
                    .paymentMethod(resellOrder.getMethod() == RefundMethod.CASH.CASH ? APaymentMethod.CASH : APaymentMethod.TRANSFER)
                    .createdAt(resellOrder.getCreatedAt())
                    .build();
            orders.add(combinedOrder);
        }
        CombinedOrders combinedOrders = CombinedOrders.builder()
                .orders(orders)
                .count(result.getCount())
                .pageSize(result.getPageSize())
                .page(result.getPage())
                .build();
        return aViewRefundOrdersWithDateMapper.toOutput(combinedOrders);
    }
}
