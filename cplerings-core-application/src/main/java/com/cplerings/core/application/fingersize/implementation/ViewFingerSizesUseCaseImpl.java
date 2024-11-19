package com.cplerings.core.application.fingersize.implementation;

import static com.cplerings.core.application.design.error.DesignErrorCode.INVALID_PAGE;
import static com.cplerings.core.application.design.error.DesignErrorCode.INVALID_PAGE_SIZE;

import com.cplerings.core.application.branch.input.ViewBranchesInput;
import com.cplerings.core.application.fingersize.ViewFingerSizesUseCase;
import com.cplerings.core.application.fingersize.datasource.ViewFingerSizesDataSource;
import com.cplerings.core.application.fingersize.input.ViewFingerSizesInput;
import com.cplerings.core.application.fingersize.mapper.AViewFingerSizesMapper;
import com.cplerings.core.application.fingersize.output.ViewFingerSizesOutput;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;

import lombok.RequiredArgsConstructor;

@UseCaseImplementation
@RequiredArgsConstructor
public class ViewFingerSizesUseCaseImpl extends AbstractUseCase<ViewFingerSizesInput, ViewFingerSizesOutput> implements ViewFingerSizesUseCase {

    private final ViewFingerSizesDataSource viewFingerSizesDataSource;
    private final AViewFingerSizesMapper aViewFingerSizesMapper;

    @Override
    protected void validateInput(UseCaseValidator validator, ViewFingerSizesInput input) {
        super.validateInput(validator, input);
        validator.validate(input.getPage() >= 0, INVALID_PAGE);
        validator.validate(input.getPageSize() > 0, INVALID_PAGE_SIZE);
    }

    @Override
    protected ViewFingerSizesOutput internalExecute(UseCaseValidator validator, ViewFingerSizesInput input) {
        var result = viewFingerSizesDataSource.getFingerSizes(input);
        return aViewFingerSizesMapper.toOutput(result);
    }
}
