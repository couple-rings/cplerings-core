package com.cplerings.core.application.dashboard.implementation;

import java.util.ArrayList;
import java.util.List;

import com.cplerings.core.application.dashboard.ViewResellOrdersWithDateUseCase;
import com.cplerings.core.application.dashboard.datasource.ViewResellOrdersWithDateDataSource;
import com.cplerings.core.application.dashboard.datasource.data.CombinedOrder;
import com.cplerings.core.application.dashboard.datasource.data.CombinedOrders;
import com.cplerings.core.application.dashboard.datasource.data.OrderTypeStatistic;
import com.cplerings.core.application.dashboard.input.ViewResellOrdersWithDateInput;
import com.cplerings.core.application.dashboard.mapper.AViewResellOrdersWithDateMapper;
import com.cplerings.core.application.dashboard.output.ViewResellOrdersWithDateOutput;
import com.cplerings.core.application.shared.entity.order.APaymentMethod;
import com.cplerings.core.application.shared.service.security.SecurityService;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.resell.PaymentMethod;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCaseImplementation
public class ViewResellOrdersWithDateUseCaseImpl extends AbstractUseCase<ViewResellOrdersWithDateInput, ViewResellOrdersWithDateOutput> implements ViewResellOrdersWithDateUseCase {

    private final ViewResellOrdersWithDateDataSource dataSource;
    private final SecurityService securityService;
    private final AViewResellOrdersWithDateMapper aViewResellOrdersWithDateMapper;

    @Override
    protected ViewResellOrdersWithDateOutput internalExecute(UseCaseValidator validator, ViewResellOrdersWithDateInput input) {
        var user = securityService.getCurrentUser();
        Account manager = dataSource.getAccountById(user.id());
        var result = dataSource.geResellOrders(input, manager.getBranch().getId());
        List<CombinedOrder> orders = new ArrayList<>();
        for (var resellOrder : result.getResellOrders()) {
            CombinedOrder combinedOrder = CombinedOrder.builder()
                    .orderId(resellOrder.getId())
                    .orderNo(resellOrder.getOrderNo())
                    .orderType(OrderTypeStatistic.RESELL)
                    .amount(resellOrder.getAmount())
                    .paymentMethod(resellOrder.getPaymentMethod() == PaymentMethod.CASH ? APaymentMethod.CASH : APaymentMethod.TRANSFER)
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
        return aViewResellOrdersWithDateMapper.toOutput(combinedOrders);
    }
}
