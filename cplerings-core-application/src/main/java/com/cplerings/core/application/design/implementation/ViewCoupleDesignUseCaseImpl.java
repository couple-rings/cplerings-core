package com.cplerings.core.application.design.implementation;

import com.cplerings.core.application.design.ViewCoupleDesignUseCase;
import com.cplerings.core.application.design.datasource.ViewCoupleDesignDataSource;
import com.cplerings.core.application.design.error.ViewDesignCoupleErrorCode;
import com.cplerings.core.application.design.input.ViewCoupleDesignInput;
import com.cplerings.core.application.design.mapper.AViewCoupleDesignMapper;
import com.cplerings.core.application.design.output.ViewCoupleDesignOutput;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;

import lombok.RequiredArgsConstructor;

@UseCaseImplementation
@RequiredArgsConstructor
public class ViewCoupleDesignUseCaseImpl extends AbstractUseCase<ViewCoupleDesignInput, ViewCoupleDesignOutput> implements ViewCoupleDesignUseCase {

    private final ViewCoupleDesignDataSource viewCoupleDesignDataSource;
    private final AViewCoupleDesignMapper aViewCoupleDesignMapper;

    @Override
    protected void validateInput(UseCaseValidator validator, ViewCoupleDesignInput input) {
        super.validateInput(validator, input);
        if (input.getCollectionId() != null) {
            validator.validate(input.getCollectionId() > 0, ViewDesignCoupleErrorCode.INVALID_COLLECTION_ID);
        }

        if (input.getCollectionId() != null) {
            validator.validate(input.getMetalSpecificationId() > 0, ViewDesignCoupleErrorCode.INVALID_METAL_SPECIFICATION_ID);
        }
    }

    @Override
    protected ViewCoupleDesignOutput internalExecute(UseCaseValidator validator, ViewCoupleDesignInput input) {
        if (input.getMaxPrice() != null && input.getMinPrice() != null && input.getMinPrice() <= 0 && input.getMaxPrice() > 0) {
            input.setMinPrice(0D);
        }

        if (input.getMaxPrice() != null && input.getMinPrice() != null && input.getMaxPrice() <= 0 && input.getMinPrice() > 0) {
            input.setMaxPrice(Double.MAX_VALUE);
        }
        var result = viewCoupleDesignDataSource.getDesignCouples(input);
        return aViewCoupleDesignMapper.toOutput(result);
    }
}
