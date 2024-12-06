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
import com.cplerings.core.domain.order.CustomOrderHistory;
import com.cplerings.core.domain.order.CustomOrderStatus;
import com.cplerings.core.domain.order.StandardOrder;
import com.cplerings.core.domain.order.StandardOrderHistory;
import com.cplerings.core.domain.order.StandardOrderStatus;
import com.cplerings.core.domain.order.TransportOrderHistory;
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
        TransportOrderHistory transportOrderHistory = null;
        switch (input.status()) {
            case DELIVERING: {
                validator.validateAndStopExecution(transportationOrder.getStatus() == TransportStatus.ON_GOING, UpdateTransportationOrderStatusErrorCode.INVALID_STATUS);
                transportationOrder.setStatus(TransportStatus.DELIVERING);
                transportOrderHistory = TransportOrderHistory.builder()
                        .status(TransportStatus.DELIVERING)
                        .build();
                break;
            }

            case REDELIVERING: {
                validator.validateAndStopExecution(transportationOrder.getStatus() == TransportStatus.DELIVERING, UpdateTransportationOrderStatusErrorCode.INVALID_STATUS);
                transportationOrder.setStatus(TransportStatus.REDELIVERING);
                transportOrderHistory = TransportOrderHistory.builder()
                        .status(TransportStatus.REDELIVERING)
                        .build();
                break;
            }

            case REJECTED: {
                validator.validateAndStopExecution(transportationOrder.getStatus() == TransportStatus.DELIVERING, UpdateTransportationOrderStatusErrorCode.INVALID_STATUS);
                transportationOrder.setStatus(TransportStatus.REJECTED);
                transportOrderHistory = TransportOrderHistory.builder()
                        .status(TransportStatus.REJECTED)
                        .build();
                if (transportationOrder.getCustomOrder() != null) {
                    CustomOrder customOrder = transportationOrder.getCustomOrder();
                    customOrder.setStatus(CustomOrderStatus.COMPLETED);
                    CustomOrder customOrderUpdated = updateTransportationOrderStatusDataSource.save(customOrder);
                    CustomOrderHistory customOrderHistory = CustomOrderHistory.builder()
                            .customOrder(customOrderUpdated)
                            .status(CustomOrderStatus.COMPLETED)
                            .build();
                    updateTransportationOrderStatusDataSource.save(customOrderHistory);
                }
                if (transportationOrder.getStandardOrder() != null) {
                    StandardOrder standardOrder = transportationOrder.getStandardOrder();
                    standardOrder.setStatus(StandardOrderStatus.COMPLETED);
                    standardOrder = updateTransportationOrderStatusDataSource.save(standardOrder);
                    StandardOrderHistory standardOrderHistory = StandardOrderHistory.builder()
                            .status(StandardOrderStatus.COMPLETED)
                            .standardOrder(standardOrder)
                            .build();
                    updateTransportationOrderStatusDataSource.save(standardOrderHistory);
                }
                break;
            }

            case COMPLETED: {
                validator.validateAndStopExecution(transportationOrder.getStatus() == TransportStatus.DELIVERING, UpdateTransportationOrderStatusErrorCode.INVALID_STATUS);
                transportationOrder.setStatus(TransportStatus.COMPLETED);
                transportOrderHistory = TransportOrderHistory.builder()
                        .status(TransportStatus.COMPLETED)
                        .build();
                if (transportationOrder.getCustomOrder() != null) {
                    CustomOrder customOrder = transportationOrder.getCustomOrder();
                    customOrder.setStatus(CustomOrderStatus.COMPLETED);
                    CustomOrder customOrderUpdated = updateTransportationOrderStatusDataSource.save(customOrder);
                    CustomOrderHistory customOrderHistory = CustomOrderHistory.builder()
                            .customOrder(customOrderUpdated)
                            .status(CustomOrderStatus.COMPLETED)
                            .build();
                    updateTransportationOrderStatusDataSource.save(customOrderHistory);
                }
                if (transportationOrder.getStandardOrder() != null) {
                    StandardOrder standardOrder = transportationOrder.getStandardOrder();
                    standardOrder.setStatus(StandardOrderStatus.COMPLETED);
                    standardOrder = updateTransportationOrderStatusDataSource.save(standardOrder);
                    StandardOrderHistory standardOrderHistory = StandardOrderHistory.builder()
                            .status(StandardOrderStatus.COMPLETED)
                            .standardOrder(standardOrder)
                            .build();
                    updateTransportationOrderStatusDataSource.save(standardOrderHistory);
                }
                break;
            }

            default:
                validator.validateAndStopExecution(false, UpdateTransportationOrderStatusErrorCode.WRONG_STATUS);
        }
        TransportationOrder transportationOrderUpdated = updateTransportationOrderStatusDataSource.save(transportationOrder);
        transportOrderHistory.setTransportationOrder(transportationOrderUpdated);
        updateTransportationOrderStatusDataSource.save(transportOrderHistory);

        return aUpdateTransportationOrderStatusMapper.toOutput(transportationOrder);
    }
}
