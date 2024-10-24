package com.cplerings.core.application.design.implementation;

import com.cplerings.core.application.design.CreateDesignVersionUseCase;
import com.cplerings.core.application.design.input.CreateDesignVersionInput;
import com.cplerings.core.application.design.output.CreateDesignVersionOutput;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;

import lombok.RequiredArgsConstructor;

@UseCaseImplementation
@RequiredArgsConstructor
public class CreateDesignVersionUseCaseImpl extends AbstractUseCase<CreateDesignVersionInput, CreateDesignVersionOutput>
        implements CreateDesignVersionUseCase {

    @Override
    protected CreateDesignVersionOutput internalExecute(UseCaseValidator validator, CreateDesignVersionInput input) {
        return null;
    }
}
