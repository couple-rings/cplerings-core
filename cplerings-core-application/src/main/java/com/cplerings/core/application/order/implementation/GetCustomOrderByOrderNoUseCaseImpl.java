package com.cplerings.core.application.order.implementation;

import com.cplerings.core.application.order.GetCustomOrderByOrderNoUseCase;
import com.cplerings.core.application.order.datasource.GetCustomOrderByOrderNoDataSource;
import com.cplerings.core.application.order.error.CreateStandardOrderErrorCode;
import com.cplerings.core.application.order.error.GetCustomOrderByOrderNoErrorCode;
import com.cplerings.core.application.order.input.GetCustomOrderByOrderNoInput;
import com.cplerings.core.application.order.mapper.AGetCustomOrderByOrderNoMapper;
import com.cplerings.core.application.order.output.GetCustomOrderByOrderNoOutput;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.domain.order.CustomOrder;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCaseImplementation
public class GetCustomOrderByOrderNoUseCaseImpl extends AbstractUseCase<GetCustomOrderByOrderNoInput, GetCustomOrderByOrderNoOutput> implements GetCustomOrderByOrderNoUseCase {

    private final GetCustomOrderByOrderNoDataSource getCustomOrderByOrderNoDataSource;
    private final AGetCustomOrderByOrderNoMapper aGetCustomOrderByOrderNoMapper;

    @Override
    protected void validateInput(UseCaseValidator validator, GetCustomOrderByOrderNoInput input) {
        super.validateInput(validator, input);
        validator.validateAndStopExecution(!input.orderNo().isBlank(), GetCustomOrderByOrderNoErrorCode.ORDER_NO_BLANK);
    }

    @Override
    protected GetCustomOrderByOrderNoOutput internalExecute(UseCaseValidator validator, GetCustomOrderByOrderNoInput input) {
        CustomOrder customOrder = getCustomOrderByOrderNoDataSource.getCustomOrderByOrderNo(input.orderNo())
                .orElse(null);
        validator.validateAndStopExecution(customOrder != null, GetCustomOrderByOrderNoErrorCode.CUSTOM_ORDER_NOT_FOUND);
        return aGetCustomOrderByOrderNoMapper.toOutput(customOrder);
    }
}
