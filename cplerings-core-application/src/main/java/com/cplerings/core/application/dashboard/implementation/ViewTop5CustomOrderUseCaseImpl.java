package com.cplerings.core.application.dashboard.implementation;

import java.util.ArrayList;
import java.util.List;

import com.cplerings.core.application.dashboard.ViewTop5CustomOrderUseCase;
import com.cplerings.core.application.dashboard.datasource.ViewTop5CustomOrderDataSource;
import com.cplerings.core.application.dashboard.output.ViewTop5CustomOrderOutput;
import com.cplerings.core.application.dashboard.output.ViewTop5CustomOrderOutputData;
import com.cplerings.core.application.shared.entity.order.ACustomOrderStatus;
import com.cplerings.core.application.shared.entity.order.APaymentMethod;
import com.cplerings.core.application.shared.entity.order.OrderType;
import com.cplerings.core.application.shared.input.NoInput;
import com.cplerings.core.application.shared.service.security.SecurityService;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.domain.account.Account;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCaseImplementation
public class ViewTop5CustomOrderUseCaseImpl extends AbstractUseCase<NoInput, ViewTop5CustomOrderOutputData> implements ViewTop5CustomOrderUseCase {

    private final ViewTop5CustomOrderDataSource dataSource;
    private final SecurityService securityService;

    @Override
    protected ViewTop5CustomOrderOutputData internalExecute(UseCaseValidator validator, NoInput input) {
        var user = securityService.getCurrentUser();
        Account manager = dataSource.getAccountById(user.id());
        var result = dataSource.getTop5CustomOrders(manager.getBranch().getId());
        List<ViewTop5CustomOrderOutput> viewTop5CustomOrderOutputs = new ArrayList<>();
        for (var customOrder : result) {
            ViewTop5CustomOrderOutput viewTop5CustomOrderOutput = ViewTop5CustomOrderOutput.builder()
                    .orderNo(customOrder.getOrderNo())
                    .orderType(OrderType.CUSTOM)
                    .paymentMethod(APaymentMethod.TRANSFER)
                    .totalPrice(customOrder.getTotalPrice())
                    .createdAt(customOrder.getCreatedAt())
                    .build();
            switch (customOrder.getStatus()) {
                case CANCELED -> viewTop5CustomOrderOutput.setStatus(ACustomOrderStatus.CANCELED);
                case COMPLETED -> viewTop5CustomOrderOutput.setStatus(ACustomOrderStatus.COMPLETED);
                case DELIVERING -> viewTop5CustomOrderOutput.setStatus(ACustomOrderStatus.DELIVERING);
                case DONE -> viewTop5CustomOrderOutput.setStatus(ACustomOrderStatus.DONE);
                case IN_PROGRESS -> viewTop5CustomOrderOutput.setStatus(ACustomOrderStatus.IN_PROGRESS);
                case PENDING -> viewTop5CustomOrderOutput.setStatus(ACustomOrderStatus.PENDING);
                case REFUNDED -> viewTop5CustomOrderOutput.setStatus(ACustomOrderStatus.REFUNDED);
                case WAITING -> viewTop5CustomOrderOutput.setStatus(ACustomOrderStatus.WAITING);
                case RESOLD -> viewTop5CustomOrderOutput.setStatus(ACustomOrderStatus.RESOLD);
            }
            viewTop5CustomOrderOutputs.add(viewTop5CustomOrderOutput);
        }
        return ViewTop5CustomOrderOutputData.builder()
                .top5CustomOrder(viewTop5CustomOrderOutputs)
                .build();
    }
}
