package com.cplerings.core.application.order.implementation;

import com.cplerings.core.application.order.CancelStandardOrderUseCase;
import com.cplerings.core.application.order.datasource.CancelStandardOrderDataSource;
import com.cplerings.core.application.order.error.CancelStandardOrderErrorCode;
import com.cplerings.core.application.order.input.CancelStandardOrderInput;
import com.cplerings.core.application.order.mapper.ACancelStandardOrderMapper;
import com.cplerings.core.application.order.output.CancelStandardOrderOutput;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.domain.order.StandardOrder;
import com.cplerings.core.domain.order.StandardOrderHistory;
import com.cplerings.core.domain.order.StandardOrderStatus;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCaseImplementation
public class CancelStandardOrderUseCaseImpl extends AbstractUseCase<CancelStandardOrderInput, CancelStandardOrderOutput> implements CancelStandardOrderUseCase {

    private final ACancelStandardOrderMapper aCancelStandardOrderMapper;
    private final CancelStandardOrderDataSource cancelStandardOrderDataSource;

    @Override
    protected CancelStandardOrderOutput internalExecute(UseCaseValidator validator, CancelStandardOrderInput input) {
        StandardOrder standardOrder = cancelStandardOrderDataSource.getStandardOrderById(input.standardOrderId())
                .orElse(null);
        validator.validateAndStopExecution(standardOrder != null, CancelStandardOrderErrorCode.STANDARD_ORDER_NOT_FOUND);
        validator.validateAndStopExecution(standardOrder.getStatus() == StandardOrderStatus.PENDING, CancelStandardOrderErrorCode.STANDARD_ORDER_WRONG_STATUS_FOR_CANCEL);
        standardOrder.setStatus(StandardOrderStatus.CANCELLED);
        standardOrder = cancelStandardOrderDataSource.save(standardOrder);
        StandardOrderHistory standardOrderHistory = StandardOrderHistory.builder()
                .status(StandardOrderStatus.CANCELLED)
                .standardOrder(standardOrder)
                .build();
        cancelStandardOrderDataSource.save(standardOrderHistory);
        return aCancelStandardOrderMapper.toOutput(standardOrder);
    }
}
