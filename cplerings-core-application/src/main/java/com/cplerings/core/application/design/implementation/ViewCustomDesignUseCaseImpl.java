package com.cplerings.core.application.design.implementation;

import com.cplerings.core.application.design.ViewCustomDesignUseCase;
import com.cplerings.core.application.design.datasource.ViewCustomDesignDataSource;
import com.cplerings.core.application.design.input.ViewCustomDesignInput;
import com.cplerings.core.application.design.mapper.AViewCustomDesignMapper;
import com.cplerings.core.application.design.output.ViewCustomDesignOutput;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.domain.design.CustomDesign;

import lombok.RequiredArgsConstructor;

@UseCaseImplementation
@RequiredArgsConstructor
public class ViewCustomDesignUseCaseImpl extends AbstractUseCase<ViewCustomDesignInput, ViewCustomDesignOutput> implements ViewCustomDesignUseCase {

    private final AViewCustomDesignMapper aViewCustomDesignMapper;
    private final ViewCustomDesignDataSource viewCustomDesignDataSource;

    @Override
    protected void validateInput(UseCaseValidator validator, ViewCustomDesignInput input) {
        super.validateInput(validator, input);
        validator.validateAndStopExecution(input.customDesignId() != null, CUSTOM_DESIGN_ID_REQUIRED);
        validator.validateAndStopExecution(input.customDesignId() > 0, CUSTOM_DESIGN_ID_WRONG_POSITIVE_NUMBER);
    }
    @Override
    protected ViewCustomDesignOutput internalExecute(UseCaseValidator validator, ViewCustomDesignInput input) {
        CustomDesign customDesign = viewCustomDesignDataSource.getCustomDesignByCustomDesignId(input.customDesignId())
                .orElse(null);
        validator.validateAndStopExecution(customDesign != null, INVALID_CUSTOM_DESIGN_ID);
        return aViewCustomDesignMapper.toOutput(customDesign);
    }
}
