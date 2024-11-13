package com.cplerings.core.application.account.implementation;

import com.cplerings.core.application.account.ViewTransportersUseCase;
import com.cplerings.core.application.account.datasource.ViewTransportersDataSource;
import com.cplerings.core.application.account.error.ViewTransportersErrorCode;
import com.cplerings.core.application.account.input.ViewTransportersInput;
import com.cplerings.core.application.account.mapper.AViewTransportersMapper;
import com.cplerings.core.application.account.output.ViewTransportersOutput;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCaseImplementation
public class ViewTransportersUseCaseImpl extends AbstractUseCase<ViewTransportersInput, ViewTransportersOutput> implements ViewTransportersUseCase {

    private final AViewTransportersMapper aViewTransportersMapper;
    private final ViewTransportersDataSource viewTransportersDataSource;

    @Override
    protected void validateInput(UseCaseValidator validator, ViewTransportersInput input) {
        super.validateInput(validator, input);
        validator.validateAndStopExecution(input.getBranchId() != null, ViewTransportersErrorCode.BRANCH_ID_REQUIRED);
        validator.validateAndStopExecution(input.getBranchId() > 0, ViewTransportersErrorCode.BRANCH_ID_WRONG_POSITIVE_INTEGER);
    }

    @Override
    protected ViewTransportersOutput internalExecute(UseCaseValidator validator, ViewTransportersInput input) {
        var result = viewTransportersDataSource.getTransporters(input);
        return aViewTransportersMapper.toOutput(result);
    }
}
