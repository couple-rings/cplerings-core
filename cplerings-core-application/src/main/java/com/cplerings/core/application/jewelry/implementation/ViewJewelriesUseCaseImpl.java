package com.cplerings.core.application.jewelry.implementation;

import static com.cplerings.core.application.design.error.DesignErrorCode.INVALID_PAGE;
import static com.cplerings.core.application.design.error.DesignErrorCode.INVALID_PAGE_SIZE;

import com.cplerings.core.application.jewelry.ViewJewelriesUseCase;
import com.cplerings.core.application.jewelry.datasource.ViewJewelriesDataSource;
import com.cplerings.core.application.jewelry.input.ViewJewelriesInput;
import com.cplerings.core.application.jewelry.mapper.AViewJewelriesMapper;
import com.cplerings.core.application.jewelry.output.ViewJewelriesOutput;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCaseImplementation
public class ViewJewelriesUseCaseImpl extends AbstractUseCase<ViewJewelriesInput, ViewJewelriesOutput> implements ViewJewelriesUseCase {

    private final AViewJewelriesMapper aViewJewelriesMapper;
    private final ViewJewelriesDataSource viewJewelriesDataSource;

    @Override
    protected void validateInput(UseCaseValidator validator, ViewJewelriesInput input) {
        super.validateInput(validator, input);
        validator.validate(input.getPage() >= 0, INVALID_PAGE);
        validator.validate(input.getPageSize() > 0, INVALID_PAGE_SIZE);
    }

    @Override
    protected ViewJewelriesOutput internalExecute(UseCaseValidator validator, ViewJewelriesInput input) {
        var result = viewJewelriesDataSource.getJewelries(input);
        return aViewJewelriesMapper.toOutput(result);
    }
}
