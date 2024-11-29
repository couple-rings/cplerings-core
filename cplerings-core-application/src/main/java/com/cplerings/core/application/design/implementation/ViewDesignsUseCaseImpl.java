package com.cplerings.core.application.design.implementation;

import static com.cplerings.core.application.design.error.DesignErrorCode.INVALID_PAGE;
import static com.cplerings.core.application.design.error.DesignErrorCode.INVALID_PAGE_SIZE;

import com.cplerings.core.application.design.ViewDesignsUseCase;
import com.cplerings.core.application.design.datasource.ViewDesignsDataSource;
import com.cplerings.core.application.design.datasource.result.Designs;
import com.cplerings.core.application.design.input.ViewDesignsInput;
import com.cplerings.core.application.design.mapper.AViewDesignsMapper;
import com.cplerings.core.application.design.output.ViewDesignsOutput;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCaseImplementation
public class ViewDesignsUseCaseImpl extends AbstractUseCase<ViewDesignsInput, ViewDesignsOutput> implements ViewDesignsUseCase {

    private final ViewDesignsDataSource viewDesignsDataSource;
    private final AViewDesignsMapper aViewDesignsMapper;

    @Override
    protected void validateInput(UseCaseValidator validator, ViewDesignsInput input) {
        super.validateInput(validator, input);
        validator.validate(input.getPage() >= 0, INVALID_PAGE);
        validator.validate(input.getPageSize() > 0, INVALID_PAGE_SIZE);
    }

    @Override
    protected ViewDesignsOutput internalExecute(UseCaseValidator validator, ViewDesignsInput input) {
        Designs designs = viewDesignsDataSource.getDesigns(input);
        return aViewDesignsMapper.toOutput(designs);
    }
}
