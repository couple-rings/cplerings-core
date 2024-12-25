package com.cplerings.core.application.order.implementation;

import com.cplerings.core.application.order.ViewResellOrderUseCase;
import com.cplerings.core.application.order.datasource.ViewResellOrderDataSource;
import com.cplerings.core.application.order.error.ViewResellOrderErrorCode;
import com.cplerings.core.application.order.input.ViewResellOrderInput;
import com.cplerings.core.application.order.mapper.AViewResellOrderMapper;
import com.cplerings.core.application.order.output.ViewResellOrderOutput;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.domain.resell.ResellOrder;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCaseImplementation
public class ViewResellOrderUseCaseImpl extends AbstractUseCase<ViewResellOrderInput, ViewResellOrderOutput> implements ViewResellOrderUseCase {

    private final ViewResellOrderDataSource dataSource;
    private final AViewResellOrderMapper aMapper;

    @Override
    protected void validateInput(UseCaseValidator validator, ViewResellOrderInput input) {
        super.validateInput(validator, input);
    }

    @Override
    protected ViewResellOrderOutput internalExecute(UseCaseValidator validator, ViewResellOrderInput input) {
        ResellOrder standardOrder = dataSource.getResellOrderById(input.resellOrderId())
                .orElse(null);
        validator.validateAndStopExecution(standardOrder != null, ViewResellOrderErrorCode.RESELL_ORDER_NOT_FOUND);
        return aMapper.toOutput(standardOrder);
    }
}
