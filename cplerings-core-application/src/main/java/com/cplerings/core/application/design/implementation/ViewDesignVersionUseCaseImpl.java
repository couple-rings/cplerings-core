package com.cplerings.core.application.design.implementation;

import com.cplerings.core.application.design.ViewDesignVersionUseCase;
import com.cplerings.core.application.design.datasource.ViewDesignVersionDataSource;
import com.cplerings.core.application.design.input.ViewDesignVersionInput;
import com.cplerings.core.application.design.mapper.AViewDesignVersionMapper;
import com.cplerings.core.application.design.output.ViewDesignVersionOutput;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCaseImplementation
public class ViewDesignVersionUseCaseImpl extends AbstractUseCase<ViewDesignVersionInput, ViewDesignVersionOutput> implements ViewDesignVersionUseCase {

    private final ViewDesignVersionDataSource viewDesignVersionDataSource;
    private final AViewDesignVersionMapper aViewDesignVersionMapper;

    @Override
    protected void validateInput(UseCaseValidator validator, ViewDesignVersionInput input) {
        super.validateInput(validator, input);
        if (input.designVersionId() != null) {
            validator.validate(input.designVersionId() > 0, DESIGN_VERSION_ID_WRONG_POSITIVE_NUMBER);
        }
    }

    @Override
    protected ViewDesignVersionOutput internalExecute(UseCaseValidator validator, ViewDesignVersionInput input) {
        DesignVerion designVerion = viewDesignVersionDataSource.getDesignVersionByUd(input.designVersionId())
                .orElse(null);
        validator.validateAndStopExecution(designVerion != null, INVALID_DESIGN_VERSION_ID);
        return aViewDesignVersionMapper.toOutput(designVerion);
    }
}
