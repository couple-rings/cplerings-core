package com.cplerings.core.application.design.implementation;

import com.cplerings.core.application.design.ViewDesignUseCase;
import com.cplerings.core.application.design.datasource.ViewDesignDataSource;
import com.cplerings.core.application.design.error.ViewDesignErrorCode;
import com.cplerings.core.application.design.error.ViewDesignSessionsLeftErrorCode;
import com.cplerings.core.application.design.input.ViewDesignInput;
import com.cplerings.core.application.design.input.ViewDesignSessionsLeftInput;
import com.cplerings.core.application.design.mapper.AViewDesignMapper;
import com.cplerings.core.application.design.output.ViewDesignOutput;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.domain.design.Design;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCaseImplementation
public class ViewDesignUseCaseImpl extends AbstractUseCase<ViewDesignInput, ViewDesignOutput> implements ViewDesignUseCase {

    private final AViewDesignMapper aMapper;
    private final ViewDesignDataSource viewDesignDataSource;

    @Override
    protected void validateInput(UseCaseValidator validator, ViewDesignInput input) {
        super.validateInput(validator, input);
        validator.validate(input.designId() != null, ViewDesignErrorCode.DESIGN_ID_REQUIRED);
        validator.clearAndThrowErrorCodes();
        validator.validateAndStopExecution(input.designId() > 0, ViewDesignErrorCode.WRONG_DESIGN_ID_POSITIVE_INTEGER);
    }

    @Override
    protected ViewDesignOutput internalExecute(UseCaseValidator validator, ViewDesignInput input) {
        Design design = viewDesignDataSource.getDesign(input.designId())
                .orElse(null);
        validator.validateAndStopExecution(design != null, ViewDesignErrorCode.DESIGN_NOT_FOUND);
        return aMapper.toOutput(design);
    }
}
