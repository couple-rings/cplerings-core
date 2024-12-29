package com.cplerings.core.application.dashboard.implementation;

import com.cplerings.core.application.dashboard.ViewBranchOrdersPaginateUseCase;
import com.cplerings.core.application.dashboard.input.ViewBranchOrdersPaginateInput;
import com.cplerings.core.application.dashboard.output.ViewBranchOrdersPaginateOutput;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCaseImplementation
public class ViewBranchOrdersPaginateUseCaseImpl extends AbstractUseCase<ViewBranchOrdersPaginateInput, ViewBranchOrdersPaginateOutput> implements ViewBranchOrdersPaginateUseCase {

    private final

    @Override
    protected ViewBranchOrdersPaginateOutput internalExecute(UseCaseValidator validator, ViewBranchOrdersPaginateInput input) {
        return null;
    }
}
