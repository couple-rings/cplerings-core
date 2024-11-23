package com.cplerings.core.application.transport.implementation;

import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.application.transport.GetTransportationOrderByCustomOrderUseCase;
import com.cplerings.core.application.transport.datasource.GetTransportationOrderByCustomOrderDataSource;
import com.cplerings.core.application.transport.error.GetTransportationOrderByCustomOrderErrorCode;
import com.cplerings.core.application.transport.input.GetTransportationOrderByCustomOrderInput;
import com.cplerings.core.application.transport.mapper.AGetTransportationOrderByCustomOrderMapper;
import com.cplerings.core.application.transport.output.GetTransportationOrderByCustomOrderOutput;
import com.cplerings.core.domain.order.TransportationOrder;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCaseImplementation
public class GetTransportationOrderByCustomOrderUseCaseImpl extends AbstractUseCase<GetTransportationOrderByCustomOrderInput, GetTransportationOrderByCustomOrderOutput> implements GetTransportationOrderByCustomOrderUseCase {

    private final AGetTransportationOrderByCustomOrderMapper aMapper;
    private final GetTransportationOrderByCustomOrderDataSource dataSource;

    @Override
    protected void validateInput(UseCaseValidator validator, GetTransportationOrderByCustomOrderInput input) {
        super.validateInput(validator, input);
        validator.validate(input.customOrderId() != null, GetTransportationOrderByCustomOrderErrorCode.CUSTOM_ORDER_ID_REQUIRED);
        validator.clearAndThrowErrorCodes();
        validator.validate(input.customOrderId() > 0, GetTransportationOrderByCustomOrderErrorCode.CUSTOM_ORDER_ID_WRONG_POSITIVE_NUMBER);
    }

    @Override
    protected GetTransportationOrderByCustomOrderOutput internalExecute(UseCaseValidator validator, GetTransportationOrderByCustomOrderInput input) {
        TransportationOrder transportationOrder = dataSource.getTransportationOrderByCustomOrderId(input.customOrderId())
                .orElse(null);

        validator.validateAndStopExecution(transportationOrder != null, GetTransportationOrderByCustomOrderErrorCode.TRANSPORTATION_ORDER_NOT_FOUND);
        return aMapper.toOutput(transportationOrder);
    }
}
