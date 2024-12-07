package com.cplerings.core.application.order.implementation;

import com.cplerings.core.application.order.CompleteOrderUseCase;
import com.cplerings.core.application.order.datasource.CompleteOrderDataSource;
import com.cplerings.core.application.order.error.CompleteOrderErrorCode;
import com.cplerings.core.application.order.input.CompleteOrderInput;
import com.cplerings.core.application.shared.entity.order.OrderType;
import com.cplerings.core.application.shared.output.NoOutput;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.domain.order.CustomOrder;
import com.cplerings.core.domain.order.CustomOrderHistory;
import com.cplerings.core.domain.order.CustomOrderStatus;
import com.cplerings.core.domain.order.StandardOrder;
import com.cplerings.core.domain.order.StandardOrderHistory;
import com.cplerings.core.domain.order.StandardOrderStatus;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCaseImplementation
public class CompleteOrderUseCaseImpl extends AbstractUseCase<CompleteOrderInput, NoOutput> implements CompleteOrderUseCase {

    private final CompleteOrderDataSource completeOrderDataSource;

    @Override
    protected void validateInput(UseCaseValidator validator, CompleteOrderInput input) {
        super.validateInput(validator, input);
        validator.validate(input.orderId() != null, CompleteOrderErrorCode.ORDER_ID_REQUIRED);
        validator.validate(input.orderType() != null, CompleteOrderErrorCode.ORDER_TYPE_REQUIRED);
        validator.clearAndThrowErrorCodes();
        validator.validateAndStopExecution(input.orderId() > 0, CompleteOrderErrorCode.ORDER_ID_WRONG_INTEGER);
    }

    @Override
    protected NoOutput internalExecute(UseCaseValidator validator, CompleteOrderInput input) {
        if (input.orderType() == OrderType.CUSTOM) {
            CustomOrder customOrder = completeOrderDataSource.getCustomOrderById(input.orderId())
                    .orElse(null);
            validator.validateAndStopExecution(customOrder != null, CompleteOrderErrorCode.CUSTOM_ORDER_NOTFOUND);
            customOrder.setStatus(CustomOrderStatus.COMPLETED);
            CustomOrder customOrderUpdated = completeOrderDataSource.save(customOrder);
            CustomOrderHistory customOrderHistory = CustomOrderHistory.builder()
                    .customOrder(customOrderUpdated)
                    .status(CustomOrderStatus.COMPLETED)
                    .build();
            completeOrderDataSource.save(customOrderHistory);
            return NoOutput.INSTANCE;
        } else {
            StandardOrder standardOrder = completeOrderDataSource.getStandardOrderById(input.orderId())
                    .orElse(null);
            validator.validateAndStopExecution(standardOrder != null, CompleteOrderErrorCode.STANDARD_ORDER_NOT_FOUND);
            standardOrder.setStatus(StandardOrderStatus.COMPLETED);
            standardOrder = completeOrderDataSource.save(standardOrder);
            StandardOrderHistory standardOrderHistory = StandardOrderHistory.builder()
                    .status(StandardOrderStatus.COMPLETED)
                    .standardOrder(standardOrder)
                    .build();
            completeOrderDataSource.save(standardOrderHistory);
            return NoOutput.INSTANCE;
        }
    }
}
