package com.cplerings.core.application.order.implementation;

import static com.cplerings.core.application.design.error.DesignErrorCode.INVALID_PAGE;
import static com.cplerings.core.application.design.error.DesignErrorCode.INVALID_PAGE_SIZE;

import com.cplerings.core.application.order.ViewResellOrdersUseCase;
import com.cplerings.core.application.order.datasource.ViewResellOrdersDataSource;
import com.cplerings.core.application.order.input.ViewResellOrdersInput;
import com.cplerings.core.application.order.mapper.AViewResellOrdersMapper;
import com.cplerings.core.application.order.output.ViewResellOrdersOutput;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCaseImplementation
public class ViewResellOrdersUseCaseImpl extends AbstractUseCase<ViewResellOrdersInput, ViewResellOrdersOutput> implements ViewResellOrdersUseCase {

    private final ViewResellOrdersDataSource dataSource;
    private final AViewResellOrdersMapper aViewResellOrdersMapper;

    @Override
    protected void validateInput(UseCaseValidator validator, ViewResellOrdersInput input) {
        super.validateInput(validator, input);
        validator.validate(input.getPage() >= 0, INVALID_PAGE);
        validator.validate(input.getPageSize() > 0, INVALID_PAGE_SIZE);
    }

    @Override
    protected ViewResellOrdersOutput internalExecute(UseCaseValidator validator, ViewResellOrdersInput input) {
        var result = dataSource.getResellOrders(input);
        return aViewResellOrdersMapper.toOutput(result);
    }
}
