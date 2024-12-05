package com.cplerings.core.application.order.implementation;

import com.cplerings.core.application.order.ViewStandardOrderUseCase;
import com.cplerings.core.application.order.datasource.ViewStandardOrderDataSource;
import com.cplerings.core.application.order.error.ViewStandardOrderErrorCode;
import com.cplerings.core.application.order.input.ViewStandardOrderInput;
import com.cplerings.core.application.order.mapper.AViewStandardOrderMapper;
import com.cplerings.core.application.order.output.ViewStandardOrderOutput;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.domain.order.StandardOrder;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCaseImplementation
public class ViewStandardOrderUseCaseImpl extends AbstractUseCase<ViewStandardOrderInput, ViewStandardOrderOutput> implements ViewStandardOrderUseCase {

    private final ViewStandardOrderDataSource dataSource;
    private final AViewStandardOrderMapper aViewStandardOrderMapper;

    @Override
    protected void validateInput(UseCaseValidator validator, ViewStandardOrderInput input) {
        super.validateInput(validator, input);
        validator.validate(input.standardOrderId() != null, ViewStandardOrderErrorCode.STANDARD_ORDER_ID_REQUIRED);
        validator.clearAndThrowErrorCodes();
        validator.validateAndStopExecution(input.standardOrderId() > 0, ViewStandardOrderErrorCode.STANDARD_ORDER_ID_WRONG_INTEGER);
    }

    @Override
    protected ViewStandardOrderOutput internalExecute(UseCaseValidator validator, ViewStandardOrderInput input) {
        StandardOrder standardOrder = dataSource.getStandardOrderById(input.standardOrderId())
                .orElse(null);
        validator.validateAndStopExecution(standardOrder != null, ViewStandardOrderErrorCode.STANDARD_ORDER_NOT_FOUND);
        return aViewStandardOrderMapper.toOutput(standardOrder);
    }
}

