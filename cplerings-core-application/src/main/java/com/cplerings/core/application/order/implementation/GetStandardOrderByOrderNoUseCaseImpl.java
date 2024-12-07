package com.cplerings.core.application.order.implementation;

import com.cplerings.core.application.order.GetStandardOrderByOrderNoUseCase;
import com.cplerings.core.application.order.datasource.GetStandardOrderByOrderNoDataSource;
import com.cplerings.core.application.order.error.GetCustomOrderByOrderNoErrorCode;
import com.cplerings.core.application.order.error.GetStandardOrderByOrderNoErrorCode;
import com.cplerings.core.application.order.input.GetCustomOrderByOrderNoInput;
import com.cplerings.core.application.order.input.GetStandardOrderByOrderNoInput;
import com.cplerings.core.application.order.mapper.AGetStandardOrderByOrderNoMapper;
import com.cplerings.core.application.order.output.GetStandardOrderByOrderNoOutput;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.domain.order.StandardOrder;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCaseImplementation
public class GetStandardOrderByOrderNoUseCaseImpl extends AbstractUseCase<GetStandardOrderByOrderNoInput, GetStandardOrderByOrderNoOutput> implements GetStandardOrderByOrderNoUseCase {

    private final GetStandardOrderByOrderNoDataSource getStandardOrderByOrderNoDataSource;
    private final AGetStandardOrderByOrderNoMapper aGetStandardOrderByOrderNoMapper;

    @Override
    protected void validateInput(UseCaseValidator validator, GetStandardOrderByOrderNoInput input) {
        super.validateInput(validator, input);
        validator.validateAndStopExecution(!input.orderNo().isBlank(), GetCustomOrderByOrderNoErrorCode.ORDER_NO_BLANK);
    }

    @Override
    protected GetStandardOrderByOrderNoOutput internalExecute(UseCaseValidator validator, GetStandardOrderByOrderNoInput input) {
        StandardOrder standardOrder = getStandardOrderByOrderNoDataSource.getStandardOrderByOrderNo(input.orderNo())
                .orElse(null);
        validator.validateAndStopExecution(standardOrder != null, GetStandardOrderByOrderNoErrorCode.STANDARD_ORDER_NOT_FOUND);
        return aGetStandardOrderByOrderNoMapper.toOutput(standardOrder);
    }
}
