package com.cplerings.core.application.dashboard.implementation;

import java.util.ArrayList;
import java.util.List;

import com.cplerings.core.application.dashboard.ViewCustomOrdersWithDateUseCase;
import com.cplerings.core.application.dashboard.datasource.ViewCustomOrdersWithDateDataSource;
import com.cplerings.core.application.dashboard.datasource.data.CombinedOrder;
import com.cplerings.core.application.dashboard.datasource.data.CombinedOrders;
import com.cplerings.core.application.dashboard.datasource.data.OrderTypeStatistic;
import com.cplerings.core.application.dashboard.input.ViewCustomOrdersWithDateInput;
import com.cplerings.core.application.dashboard.mapper.AViewCustomOrdersWithDateMapper;
import com.cplerings.core.application.dashboard.output.ViewCustomOrdersWithDateOutput;
import com.cplerings.core.application.shared.entity.order.APaymentMethod;
import com.cplerings.core.application.shared.service.security.SecurityService;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.domain.account.Account;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCaseImplementation
public class ViewCustomOrdersWithDateUseCaseImpl extends AbstractUseCase<ViewCustomOrdersWithDateInput, ViewCustomOrdersWithDateOutput> implements ViewCustomOrdersWithDateUseCase {

    private final ViewCustomOrdersWithDateDataSource dataSource;
    private final SecurityService securityService;
    private final AViewCustomOrdersWithDateMapper aViewCustomOrdersWithDateMapper;

    @Override
    protected ViewCustomOrdersWithDateOutput internalExecute(UseCaseValidator validator, ViewCustomOrdersWithDateInput input) {
        var user = securityService.getCurrentUser();
        Account manager = dataSource.getAccountById(user.id());
        var result = dataSource.getCustomOrders(input, manager.getId());
        List<CombinedOrder> orders = new ArrayList<>();
        for (var customOrder : result.getCustomOrders()) {
            CombinedOrder combinedOrder = CombinedOrder.builder()
                    .orderId(customOrder.getId())
                    .orderNo(customOrder.getOrderNo())
                    .orderType(OrderTypeStatistic.CUSTOM)
                    .amount(customOrder.getTotalPrice())
                    .paymentMethod(APaymentMethod.TRANSFER)
                    .createdAt(customOrder.getCreatedAt())
                    .build();
            orders.add(combinedOrder);
        }
        CombinedOrders combinedOrders = CombinedOrders.builder()
                .orders(orders)
                .count(result.getCount())
                .pageSize(result.getPageSize())
                .page(result.getPage())
                .build();
        return aViewCustomOrdersWithDateMapper.toOutput(combinedOrders);
    }
}
