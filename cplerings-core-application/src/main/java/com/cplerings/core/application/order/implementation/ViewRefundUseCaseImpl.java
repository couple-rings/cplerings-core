package com.cplerings.core.application.order.implementation;

import com.cplerings.core.application.order.ViewRefundUseCase;
import com.cplerings.core.application.order.datasource.ViewRefundDataSource;
import com.cplerings.core.application.order.input.ViewRefundInput;
import com.cplerings.core.application.order.mapper.AViewRefundMapper;
import com.cplerings.core.application.order.output.ViewRefundOutput;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCaseImplementation
public class ViewRefundUseCaseImpl extends AbstractUseCase<ViewRefundInput, ViewRefundOutput> implements ViewRefundUseCase {

    private final ViewRefundDataSource dataSource;
    private final AViewRefundMapper aViewRefundMapper;

    @Override
    protected ViewRefundOutput internalExecute(UseCaseValidator validator, ViewRefundInput input) {
        var result = dataSource.getRefundById(input.refundOrderId());
        return aViewRefundMapper.toOutput(result);
    }
}
