package com.cplerings.core.application.transport.implementation;

import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.application.transport.ViewTransportationOrderUseCase;
import com.cplerings.core.application.transport.datasource.ViewTransportationOrderDataSource;
import com.cplerings.core.application.transport.error.ViewTransportationOrderErrorCode;
import com.cplerings.core.application.transport.input.ViewTransportationOrderInput;
import com.cplerings.core.application.transport.mapper.AViewTransportationOrderMapper;
import com.cplerings.core.application.transport.output.ViewTransportationOrderOutput;
import com.cplerings.core.domain.order.TransportationOrder;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCaseImplementation
public class ViewTransportationOrderUseCaseImpl extends AbstractUseCase<ViewTransportationOrderInput, ViewTransportationOrderOutput> implements ViewTransportationOrderUseCase {

    private final AViewTransportationOrderMapper aViewTransportationOrderMapper;
    private final ViewTransportationOrderDataSource dataSource;

    @Override
    protected void validateInput(UseCaseValidator validator, ViewTransportationOrderInput input) {
        super.validateInput(validator, input);
        validator.validate(input.transportationOrderId() != null, ViewTransportationOrderErrorCode.TRANSPORTATION_ORDER_ID_REQUIRED);
        validator.clearAndThrowErrorCodes();
        validator.validateAndStopExecution(input.transportationOrderId() > 0, ViewTransportationOrderErrorCode.TRANSPORTATION_ORDER_WRONG_INTEGER);
    }

    @Override
    protected ViewTransportationOrderOutput internalExecute(UseCaseValidator validator, ViewTransportationOrderInput input) {
        TransportationOrder transportationOrder = dataSource.getTransportationOrder(input.transportationOrderId())
                .orElse(null);
        validator.validateAndStopExecution(transportationOrder != null, ViewTransportationOrderErrorCode.INVALID_TRANSPORTATION_ORDER);
        return aViewTransportationOrderMapper.toOutput(transportationOrder);
    }
}
