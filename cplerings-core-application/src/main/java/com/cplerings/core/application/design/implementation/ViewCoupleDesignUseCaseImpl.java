package com.cplerings.core.application.design.implementation;

import com.cplerings.core.application.design.ViewCoupleDesignUseCase;
import com.cplerings.core.application.design.input.ViewCoupleDesignInput;
import com.cplerings.core.application.design.output.ViewCoupleDesignOutput;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;

import lombok.RequiredArgsConstructor;

@UseCaseImplementation
@RequiredArgsConstructor
public class ViewCoupleDesignUseCaseImpl extends AbstractUseCase<ViewCoupleDesignInput, ViewCoupleDesignOutput> implements ViewCoupleDesignUseCase {

    @Override
    protected ViewCoupleDesignOutput internalExecute(UseCaseValidator validator, ViewCoupleDesignInput input) {
        return null;
    }
}
