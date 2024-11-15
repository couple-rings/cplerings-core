package com.cplerings.core.application.order.implementation;

import static com.cplerings.core.application.design.error.DesignErrorCode.INVALID_PAGE;
import static com.cplerings.core.application.design.error.DesignErrorCode.INVALID_PAGE_SIZE;

import com.cplerings.core.application.diamond.input.ViewDiamondSpecificationInput;
import com.cplerings.core.application.order.ViewCustomOrdersUseCase;
import com.cplerings.core.application.order.datasource.ViewCustomOrdersDataSource;
import com.cplerings.core.application.order.input.ViewCustomOrdersInput;
import com.cplerings.core.application.order.mapper.AViewCustomOrdersMapper;
import com.cplerings.core.application.order.output.ViewCustomOrdersOutput;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCaseImplementation
public class ViewCustomOrdersUseCaseImpl extends AbstractUseCase<ViewCustomOrdersInput, ViewCustomOrdersOutput> implements ViewCustomOrdersUseCase {

    private final ViewCustomOrdersDataSource viewCustomOrdersDataSource;
    private final AViewCustomOrdersMapper aViewCustomOrdersMapper;

    @Override
    protected void validateInput(UseCaseValidator validator, ViewCustomOrdersInput input) {
        super.validateInput(validator, input);
        validator.validate(input.getPage() >= 0, INVALID_PAGE);
        validator.validate(input.getPageSize() > 0, INVALID_PAGE_SIZE);
    }

    @Override
    protected ViewCustomOrdersOutput internalExecute(UseCaseValidator validator, ViewCustomOrdersInput input) {
        var result = viewCustomOrdersDataSource.getCustomOrders(input);
        return aViewCustomOrdersMapper.toOutput(result);
    }
}
