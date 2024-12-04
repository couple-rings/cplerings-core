package com.cplerings.core.application.order.implementation;

import static com.cplerings.core.application.design.error.DesignErrorCode.INVALID_PAGE;
import static com.cplerings.core.application.design.error.DesignErrorCode.INVALID_PAGE_SIZE;

import com.cplerings.core.application.order.ViewStandardOrdersUseCase;
import com.cplerings.core.application.order.datasource.ViewStandardOrdersDataSource;
import com.cplerings.core.application.order.input.ViewStandardOrdersInput;
import com.cplerings.core.application.order.mapper.AViewStandardOrdersMapper;
import com.cplerings.core.application.order.output.ViewStandardOrdersOutput;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCaseImplementation
public class ViewStandardOrdersUseCaseImpl extends AbstractUseCase<ViewStandardOrdersInput, ViewStandardOrdersOutput> implements ViewStandardOrdersUseCase {

    private final ViewStandardOrdersDataSource viewStandardOrdersDataSource;
    private final AViewStandardOrdersMapper aViewStandardOrdersMapper;

    @Override
    protected void validateInput(UseCaseValidator validator, ViewStandardOrdersInput input) {
        super.validateInput(validator, input);
        validator.validate(input.getPage() >= 0, INVALID_PAGE);
        validator.validate(input.getPageSize() > 0, INVALID_PAGE_SIZE);
    }

    @Override
    protected ViewStandardOrdersOutput internalExecute(UseCaseValidator validator, ViewStandardOrdersInput input) {
        var result = viewStandardOrdersDataSource.getStandardOrders(input);
        return aViewStandardOrdersMapper.toOutput(result);
    }
}
