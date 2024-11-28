package com.cplerings.core.application.transport.implementation;

import java.util.List;

import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.application.transport.UpdateTransportationOrdersToOngoingUseCase;
import com.cplerings.core.application.transport.datasource.UpdateTransportationOrdersToOngoingDataSource;
import com.cplerings.core.application.transport.error.UpdateTransportationOrdersToOngoingErrorCode;
import com.cplerings.core.application.transport.input.UpdateTransportationOrdersToOngoingInput;
import com.cplerings.core.application.transport.mapper.AUpdateTransportationOrdersToOngoingMapper;
import com.cplerings.core.application.transport.output.UpdateTransportationOrdersToOngoingOutput;
import com.cplerings.core.application.transport.output.data.TransportationOrderList;
import com.cplerings.core.domain.order.TransportOrderHistory;
import com.cplerings.core.domain.order.TransportStatus;
import com.cplerings.core.domain.order.TransportationOrder;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCaseImplementation
public class UpdateTransportationOrdersToOngoingUseCaseImpl extends AbstractUseCase<UpdateTransportationOrdersToOngoingInput, UpdateTransportationOrdersToOngoingOutput> implements UpdateTransportationOrdersToOngoingUseCase {

    private final UpdateTransportationOrdersToOngoingDataSource updateTransportationOrdersToOngoingDataSource;
    private final AUpdateTransportationOrdersToOngoingMapper aUpdateTransportationOrdersToOngoingMapper;

    @Override
    protected void validateInput(UseCaseValidator validator, UpdateTransportationOrdersToOngoingInput input) {
        super.validateInput(validator, input);
        validator.validate(input.transportationOrderIds() != null, UpdateTransportationOrdersToOngoingErrorCode.LIST_TRANSPORTATION_ORDER_IDS_REQUIRED);
    }

    @Override
    protected UpdateTransportationOrdersToOngoingOutput internalExecute(UseCaseValidator validator, UpdateTransportationOrdersToOngoingInput input) {
        List<TransportationOrder> transportationOrders = updateTransportationOrdersToOngoingDataSource.getTransportationOrders(input.transportationOrderIds());
        for (var transportationOrder : transportationOrders) {
            validator.validateAndStopExecution(transportationOrder != null, UpdateTransportationOrdersToOngoingErrorCode.ONE_OF_THE_IDS_IS_INVALID);
            validator.validateAndStopExecution(transportationOrder.getStatus() == TransportStatus.WAITING, UpdateTransportationOrdersToOngoingErrorCode.ONE_OF_THE_TRANSPORTATIONS_IS_NOT_WAITING);
        }
        transportationOrders.forEach(transportationOrder -> {
            transportationOrder.setStatus(TransportStatus.ON_GOING);
        });
        List<TransportationOrder> transportationOrdersUpdated = updateTransportationOrdersToOngoingDataSource.updateToOngoing(transportationOrders);
        transportationOrdersUpdated.forEach(x -> {
            TransportOrderHistory transportOrderHistory = TransportOrderHistory.builder()
                    .transportationOrder(x)
                    .status(TransportStatus.ON_GOING)
                    .build();
            updateTransportationOrdersToOngoingDataSource.save(transportOrderHistory);
        });
        TransportationOrderList transportationOrdersList = TransportationOrderList.builder().transportationOrders(transportationOrdersUpdated).build();

        return aUpdateTransportationOrdersToOngoingMapper.toOutput(transportationOrdersList);
    }
}
