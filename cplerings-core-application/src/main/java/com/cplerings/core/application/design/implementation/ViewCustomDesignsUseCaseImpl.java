package com.cplerings.core.application.design.implementation;

import static com.cplerings.core.application.design.error.DesignErrorCode.INVALID_PAGE;
import static com.cplerings.core.application.design.error.DesignErrorCode.INVALID_PAGE_SIZE;

import com.cplerings.core.application.design.ViewCustomDesignsUseCase;
import com.cplerings.core.application.design.datasource.ViewCustomDesignsDataSource;
import com.cplerings.core.application.design.datasource.result.CustomDesigns;
import com.cplerings.core.application.design.input.ViewCustomDesignsInput;
import com.cplerings.core.application.design.mapper.AViewCustomDesignsMapper;
import com.cplerings.core.application.design.output.ViewCustomDesignsOutput;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCaseImplementation
public class ViewCustomDesignsUseCaseImpl extends AbstractUseCase<ViewCustomDesignsInput, ViewCustomDesignsOutput> implements ViewCustomDesignsUseCase {

    private final ViewCustomDesignsDataSource viewCustomDesignsDataSource;
    private final AViewCustomDesignsMapper aViewCustomDesignsMapper;

    @Override
    protected void validateInput(UseCaseValidator validator, ViewCustomDesignsInput input) {
        super.validateInput(validator, input);
        validator.validate(input.getPage() >= 0, INVALID_PAGE);
        validator.validate(input.getPageSize() > 0, INVALID_PAGE_SIZE);
    }

    @Override
    protected ViewCustomDesignsOutput internalExecute(UseCaseValidator validator, ViewCustomDesignsInput input) {
        CustomDesigns designVersions = viewCustomDesignsDataSource.getCustomDesigns(input);
        return aViewCustomDesignsMapper.toOutput(designVersions);
    }
}
