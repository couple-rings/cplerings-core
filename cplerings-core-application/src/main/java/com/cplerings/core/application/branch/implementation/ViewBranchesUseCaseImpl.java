package com.cplerings.core.application.branch.implementation;

import static com.cplerings.core.application.design.error.DesignErrorCode.INVALID_PAGE;
import static com.cplerings.core.application.design.error.DesignErrorCode.INVALID_PAGE_SIZE;

import com.cplerings.core.application.branch.ViewBranchesUseCase;
import com.cplerings.core.application.branch.datasource.ViewBranchesDataSource;
import com.cplerings.core.application.branch.input.ViewBranchesInput;
import com.cplerings.core.application.branch.mapper.AViewBranchesMapper;
import com.cplerings.core.application.branch.output.ViewBranchesOutput;
import com.cplerings.core.application.diamond.input.ViewDiamondSpecificationInput;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;

import lombok.RequiredArgsConstructor;

@UseCaseImplementation
@RequiredArgsConstructor
public class ViewBranchesUseCaseImpl extends AbstractUseCase<ViewBranchesInput, ViewBranchesOutput> implements ViewBranchesUseCase {

    private final AViewBranchesMapper aViewBranchesMapper;
    private final ViewBranchesDataSource viewBranchesDataSource;

    @Override
    protected void validateInput(UseCaseValidator validator, ViewBranchesInput input) {
        super.validateInput(validator, input);
        validator.validate(input.getPage() >= 0, INVALID_PAGE);
        validator.validate(input.getPageSize() > 0, INVALID_PAGE_SIZE);
    }

    @Override
    protected ViewBranchesOutput internalExecute(UseCaseValidator validator, ViewBranchesInput input) {
        var result = viewBranchesDataSource.getBranches(input);
        return aViewBranchesMapper.toOutput(result);
    }
}
