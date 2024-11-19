package com.cplerings.core.application.order.implementation;

import com.cplerings.core.application.order.AssignJewelerToCustomOrderUseCase;
import com.cplerings.core.application.order.datasource.AssignJewelerToCustomOrderDataSource;
import com.cplerings.core.application.order.error.AssignJewelerToCustomOrderErrorCode;
import com.cplerings.core.application.order.input.AssignJewelerToCustomOrderInput;
import com.cplerings.core.application.order.mapper.AAssignJewelerToCustomOrderMapper;
import com.cplerings.core.application.order.output.AssignJewelerToCustomOrderOutput;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.account.Role;
import com.cplerings.core.domain.order.CustomOrder;
import com.cplerings.core.domain.order.CustomOrderStatus;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCaseImplementation
public class AssignJewelerToCustomOrderUseCaseImpl extends AbstractUseCase<AssignJewelerToCustomOrderInput, AssignJewelerToCustomOrderOutput> implements AssignJewelerToCustomOrderUseCase {

    private final AssignJewelerToCustomOrderDataSource assignJewelerToCustomOrderDataSource;
    private final AAssignJewelerToCustomOrderMapper aAssignJewelerToCustomOrderMapper;

    @Override
    protected void validateInput(UseCaseValidator validator, AssignJewelerToCustomOrderInput input) {
        super.validateInput(validator, input);
        validator.validateAndStopExecution(input.customOrderId() != null, AssignJewelerToCustomOrderErrorCode.CUSTOM_ORDER_ID_REQUIRED);
        validator.validateAndStopExecution(input.customOrderId() > 0, AssignJewelerToCustomOrderErrorCode.CUSTOM_ORDER_ID_WRONG_POSITIVE_NUMBER);
        validator.validateAndStopExecution(input.jewelerId() != null, AssignJewelerToCustomOrderErrorCode.JEWELER_ID_REQUIRED);
        validator.validateAndStopExecution(input.jewelerId() > 0, AssignJewelerToCustomOrderErrorCode.JEWELER_ID_WRONG_POSITIVE_NUMBER);
    }

    @Override
    protected AssignJewelerToCustomOrderOutput internalExecute(UseCaseValidator validator, AssignJewelerToCustomOrderInput input) {
        Account jeweler = assignJewelerToCustomOrderDataSource.getJewelerById(input.jewelerId())
                .orElse(null);
        validator.validateAndStopExecution(jeweler != null, AssignJewelerToCustomOrderErrorCode.INVALID_JEWELER_ID);
        validator.validateAndStopExecution(jeweler.getRole() == Role.JEWELER, AssignJewelerToCustomOrderErrorCode.NOT_A_JEWELER);
        CustomOrder customOrder = assignJewelerToCustomOrderDataSource.getCustomOrderById(input.customOrderId())
                .orElse(null);
        validator.validateAndStopExecution(customOrder != null, AssignJewelerToCustomOrderErrorCode.INVALID_CUSTOM_ORDER_ID);
        validator.validateAndStopExecution(customOrder.getStatus() == CustomOrderStatus.WAITING, AssignJewelerToCustomOrderErrorCode.INVALID_CUSTOM_ORDER_STATUS_FOR_ASSIGNING);
        validator.validateAndStopExecution(customOrder.getJeweler() == null, AssignJewelerToCustomOrderErrorCode.CUSTOM_ORDER_HAS_BEEN_ASSIGNED);
        customOrder.setStatus(CustomOrderStatus.IN_PROGRESS);
        customOrder.setJeweler(jeweler);
        CustomOrder customOrderAssigned = assignJewelerToCustomOrderDataSource.save(customOrder);
        return aAssignJewelerToCustomOrderMapper.toOutput(customOrderAssigned);
    }

}
