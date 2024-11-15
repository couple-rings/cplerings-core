package com.cplerings.core.application.order.implementation;

import com.cplerings.core.application.order.ViewCustomOrderUseCase;
import com.cplerings.core.application.order.datasource.ViewCustomOrderDataSource;
import com.cplerings.core.application.order.error.ViewCustomOrderErrorCode;
import com.cplerings.core.application.order.input.ViewCustomOrderInput;
import com.cplerings.core.application.order.mapper.AViewCustomOrderMapper;
import com.cplerings.core.application.order.output.ViewCustomOrderOutput;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.domain.order.CustomOrder;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCaseImplementation
public class ViewCustomOrderUseCaseImpl extends AbstractUseCase<ViewCustomOrderInput, ViewCustomOrderOutput> implements ViewCustomOrderUseCase {

    private final ViewCustomOrderDataSource viewCustomOrderDataSource;
    private final AViewCustomOrderMapper aViewCustomOrderMapper;

    @Override
    protected void validateInput(UseCaseValidator validator, ViewCustomOrderInput input) {
        super.validateInput(validator, input);
        validator.validate(input.customOrderId() != null, ViewCustomOrderErrorCode.CUSTOM_ORDER_ID_REQUIRED);
        validator.validateAndStopExecution(input.customOrderId() > 0, ViewCustomOrderErrorCode.CUSTOM_ORDER_ID_WRONG_POSITIVE_INTEGER);
    }

    @Override
    protected ViewCustomOrderOutput internalExecute(UseCaseValidator validator, ViewCustomOrderInput input) {
        CustomOrder customOrder = viewCustomOrderDataSource.getCustomOrder(input.customOrderId())
                .orElse(null);
        validator.validateAndStopExecution(customOrder != null, ViewCustomOrderErrorCode.INVALID_CUSTOM_ORDER);
        return aViewCustomOrderMapper.toOutput(customOrder);
    }
}
