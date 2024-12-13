package com.cplerings.core.application.order.implementation;

import static com.cplerings.core.application.design.error.DesignErrorCode.INVALID_PAGE;
import static com.cplerings.core.application.design.error.DesignErrorCode.INVALID_PAGE_SIZE;

import com.cplerings.core.application.order.ViewRefundOrdersUseCase;
import com.cplerings.core.application.order.datasource.ViewRefundOrdersDataSource;
import com.cplerings.core.application.order.input.ViewRefundOrdersInput;
import com.cplerings.core.application.order.mapper.AViewRefundOrdersMapper;
import com.cplerings.core.application.order.output.ViewRefundOrdersOutput;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCaseImplementation
public class ViewRefundOrdersUseCaseImpl extends AbstractUseCase<ViewRefundOrdersInput, ViewRefundOrdersOutput> implements ViewRefundOrdersUseCase {

    private final ViewRefundOrdersDataSource dataSource;
    private final AViewRefundOrdersMapper aViewRefundOrdersMapper;

    @Override
    protected void validateInput(UseCaseValidator validator, ViewRefundOrdersInput input) {
        super.validateInput(validator, input);
        validator.validate(input.getPage() >= 0, INVALID_PAGE);
        validator.validate(input.getPageSize() > 0, INVALID_PAGE_SIZE);
    }

    @Override
    protected ViewRefundOrdersOutput internalExecute(UseCaseValidator validator, ViewRefundOrdersInput input) {
        var result = dataSource.getRefunds(input);
        return aViewRefundOrdersMapper.toOutput(result);
    }
}
