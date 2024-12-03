package com.cplerings.core.application.transport.implementation;

import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.application.transport.AssignTransportOrderUseCase;
import com.cplerings.core.application.transport.datasource.AssignTransportOrderDataSource;
import com.cplerings.core.application.transport.error.AssignTransportOrderErrorCode;
import com.cplerings.core.application.transport.input.AssignTransportOrderInput;
import com.cplerings.core.application.transport.mapper.AAssignTransportOrderMapper;
import com.cplerings.core.application.transport.output.AssignTransportOrderOutput;
import com.cplerings.core.common.security.RoleConstant;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.account.Role;
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
public class AssignTransportOrderUseCaseImpl extends AbstractUseCase<AssignTransportOrderInput, AssignTransportOrderOutput> implements AssignTransportOrderUseCase {

    private final AssignTransportOrderDataSource assignTransportOrderDataSource;
    private final AAssignTransportOrderMapper aAssignTransportOrderMapper;

    @Override
    protected void validateInput(UseCaseValidator validator, AssignTransportOrderInput input) {
        super.validateInput(validator, input);
        validator.validateAndStopExecution(input.transportOrderId() != null, AssignTransportOrderErrorCode.TRANSPORT_ORDER_ID_REQUIRED);
        validator.validateAndStopExecution(input.transportOrderId() > 0, AssignTransportOrderErrorCode.TRANSPORT_ORDER_ID_WRONG_POSITIVE_NUMBER);
        validator.validateAndStopExecution(input.transporterId() != null, AssignTransportOrderErrorCode.TRANSPORTER_ID_REQUIRED);
        validator.validateAndStopExecution(input.transportOrderId() > 0, AssignTransportOrderErrorCode.TRANSPORTER_ID_WRONG_POSITIVE_NUMBER);
    }

    @Override
    protected AssignTransportOrderOutput internalExecute(UseCaseValidator validator, AssignTransportOrderInput input) {
        Account transporter = assignTransportOrderDataSource.getTransporterById(input.transporterId())
                .orElse(null);
        validator.validateAndStopExecution(transporter != null, AssignTransportOrderErrorCode.INVALID_TRANSPORTER_ID);
        validator.validateAndStopExecution(transporter.getRole() == Role.TRANSPORTER, AssignTransportOrderErrorCode.NOT_A_TRANSPORTER);
        TransportationOrder transportationOrder = assignTransportOrderDataSource.getTransportationOrderById(input.transportOrderId())
                .orElse(null);
        validator.validateAndStopExecution(transportationOrder != null, AssignTransportOrderErrorCode.INVALID_TRANSPORTATION_ORDER_ID);
        validator.validateAndStopExecution(transportationOrder.getStatus() == TransportStatus.PENDING, AssignTransportOrderErrorCode.INVALID_TRANSPORTATION_ORDER_STATUS_FOR_ASSIGNING);
        validator.validateAndStopExecution(transportationOrder.getTransporter() == null, AssignTransportOrderErrorCode.TRANSPORTATION_ORDER_HAS_BEEN_ASSIGNED);
        transportationOrder.setStatus(TransportStatus.WAITING);
        transportationOrder.setTransporter(transporter);
        TransportationOrder transportationOrderAssigned = assignTransportOrderDataSource.save(transportationOrder);

        TransportOrderHistory transportOrderHistory = TransportOrderHistory.builder()
                .transportationOrder(transportationOrderAssigned)
                .status(TransportStatus.WAITING)
                .build();
        assignTransportOrderDataSource.save(transportOrderHistory);

        if (transportationOrder.getCustomOrder() != null) {
            CustomOrder customOrder = transportationOrder.getCustomOrder();
            customOrder.setStatus(CustomOrderStatus.DELIVERING);
            CustomOrder customOrderUpdated = assignTransportOrderDataSource.save(customOrder);
            CustomOrderHistory customOrderHistory = CustomOrderHistory.builder()
                    .customOrder(customOrderUpdated)
                    .status(CustomOrderStatus.DELIVERING)
                    .build();
            assignTransportOrderDataSource.save(customOrderHistory);
        }

        if (transportationOrder.getStandardOrder() != null) {
            StandardOrder standardOrder = transportationOrder.getStandardOrder();
            standardOrder.setStatus(StandardOrderStatus.DELIVERING);
            standardOrder = assignTransportOrderDataSource.save(standardOrder);
            StandardOrderHistory standardOrderHistory = StandardOrderHistory.builder()
                    .status(StandardOrderStatus.DELIVERING)
                    .standardOrder(standardOrder)
                    .build();
            assignTransportOrderDataSource.save(standardOrderHistory);
        }

        return aAssignTransportOrderMapper.toOutput(transportationOrderAssigned);
    }
}
