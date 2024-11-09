package com.cplerings.core.application.metal.implementation;

import static com.cplerings.core.application.design.error.DesignErrorCode.INVALID_PAGE;
import static com.cplerings.core.application.design.error.DesignErrorCode.INVALID_PAGE_SIZE;

import com.cplerings.core.application.metal.ViewMetalSpecificationUseCase;
import com.cplerings.core.application.metal.datasource.ViewMetalSpecificationDataSource;
import com.cplerings.core.application.metal.datasource.result.MetalSpecifications;
import com.cplerings.core.application.metal.input.ViewMetalSpecificationInput;
import com.cplerings.core.application.metal.mapper.AViewMetalSpecificationMapper;
import com.cplerings.core.application.metal.output.ViewMetalSpecificationOutput;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;

import lombok.RequiredArgsConstructor;

@UseCaseImplementation
@RequiredArgsConstructor
public class ViewMetalSpecificationUseCaseImpl extends AbstractUseCase<ViewMetalSpecificationInput, ViewMetalSpecificationOutput> implements ViewMetalSpecificationUseCase {

    private final AViewMetalSpecificationMapper aViewMetalSpecificationMapper;
    private final ViewMetalSpecificationDataSource viewMetalSpecificationDataSource;

    @Override
    protected void validateInput(UseCaseValidator validator, ViewMetalSpecificationInput input) {
        super.validateInput(validator, input);
        validator.validate(input.getPage() >= 0, INVALID_PAGE);
        validator.validate(input.getPageSize() > 0, INVALID_PAGE_SIZE);
    }


    @Override
    protected ViewMetalSpecificationOutput internalExecute(UseCaseValidator validator, ViewMetalSpecificationInput input) {
        MetalSpecifications result = viewMetalSpecificationDataSource.getMetalSpecifications(input);
        return aViewMetalSpecificationMapper.toOutput(result);
    }
}
