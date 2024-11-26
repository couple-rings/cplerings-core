package com.cplerings.core.application.transport.implementation;

import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.application.transport.UpdateTransportationOrderStatusUseCase;
import com.cplerings.core.application.transport.datasource.UpdateTransportationOrderStatusDataSource;
import com.cplerings.core.application.transport.error.UpdateTransportationOrderStatusErrorCode;
import com.cplerings.core.application.transport.input.UpdateTransportationOrderStatusInput;
import com.cplerings.core.application.transport.mapper.AUpdateTransportationOrderStatusMapper;
import com.cplerings.core.application.transport.output.UpdateTransportationOrderStatusOutput;
import com.cplerings.core.domain.order.CustomOrder;
import com.cplerings.core.domain.order.CustomOrderStatus;
import com.cplerings.core.domain.order.TransportStatus;
import com.cplerings.core.domain.order.TransportationOrder;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCaseImplementation
public class UpdateTransportationOrderStatusUseCaseImpl extends AbstractUseCase<UpdateTransportationOrderStatusInput, UpdateTransportationOrderStatusOutput> implements UpdateTransportationOrderStatusUseCase {

    private final UpdateTransportationOrderStatusDataSource updateTransportationOrderStatusDataSource;
    private final AUpdateTransportationOrderStatusMapper aUpdateTransportationOrderStatusMapper;

    @Override
    protected void validateInput(UseCaseValidator validator, UpdateTransportationOrderStatusInput input) {
        super.validateInput(validator, input);
        validator.validate(input.transportationOrderId() != null, UpdateTransportationOrderStatusErrorCode.TRANSPORTATION_ORDER_ID_REQUIRED);
        validator.validate(input.status() != null, UpdateTransportationOrderStatusErrorCode.STATUS_REQUIRED);
        validator.validate(input.transportationOrderId() > 0, UpdateTransportationOrderStatusErrorCode.TRANSPORTATION_ORDER_ID_WRONG_POSITIVE_NUMBER);

    }

    @Override
    protected UpdateTransportationOrderStatusOutput internalExecute(UseCaseValidator validator, UpdateTransportationOrderStatusInput input) {
        TransportationOrder transportationOrder = updateTransportationOrderStatusDataSource.getTransportationOrderById(input.transportationOrderId())
                .orElse(null);
        validator.validateAndStopExecution(transportationOrder != null, UpdateTransportationOrderStatusErrorCode.INVALID_TRANSPORTATION_ORDER_ID);

        switch (input.status()) {
            case DELIVERING: {
                validator.validateAndStopExecution(transportationOrder.getStatus() == TransportStatus.ON_GOING, UpdateTransportationOrderStatusErrorCode.INVALID_STATUS);
                transportationOrder.setStatus(TransportStatus.DELIVERING);
                break;
            }

            case WAITING: {
                validator.validateAndStopExecution(transportationOrder.getStatus() == TransportStatus.DELIVERING, UpdateTransportationOrderStatusErrorCode.INVALID_STATUS);
                transportationOrder.setStatus(TransportStatus.WAITING);
                break;
            }

            case REJECTED: {
                validator.validateAndStopExecution(transportationOrder.getStatus() == TransportStatus.DELIVERING, UpdateTransportationOrderStatusErrorCode.INVALID_STATUS);
                transportationOrder.setStatus(TransportStatus.REJECTED);
                CustomOrder customOrder = transportationOrder.getCustomOrder();
                customOrder.setStatus(CustomOrderStatus.COMPLETED);
                updateTransportationOrderStatusDataSource.save(customOrder);
                break;
            }

            case COMPLETED: {
                validator.validateAndStopExecution(transportationOrder.getStatus() == TransportStatus.DELIVERING, UpdateTransportationOrderStatusErrorCode.INVALID_STATUS);
                transportationOrder.setStatus(TransportStatus.COMPLETED);
                CustomOrder customOrder = transportationOrder.getCustomOrder();
                customOrder.setStatus(CustomOrderStatus.COMPLETED);
                updateTransportationOrderStatusDataSource.save(customOrder);
                break;
            }

            default:
                validator.validateAndStopExecution(false, UpdateTransportationOrderStatusErrorCode.WRONG_STATUS);
        }
        updateTransportationOrderStatusDataSource.save(transportationOrder);

        return aUpdateTransportationOrderStatusMapper.toOutput(transportationOrder);
    }
}
