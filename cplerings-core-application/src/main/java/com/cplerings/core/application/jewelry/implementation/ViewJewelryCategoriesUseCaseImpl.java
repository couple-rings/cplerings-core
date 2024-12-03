package com.cplerings.core.application.jewelry.implementation;

import static com.cplerings.core.application.design.error.DesignErrorCode.INVALID_PAGE;
import static com.cplerings.core.application.design.error.DesignErrorCode.INVALID_PAGE_SIZE;

import com.cplerings.core.application.jewelry.ViewJewelryCategoriesUseCase;
import com.cplerings.core.application.jewelry.datasource.ViewJewelryCategoriesDataSource;
import com.cplerings.core.application.jewelry.input.ViewJewelryCategoriesInput;
import com.cplerings.core.application.jewelry.mapper.AViewJewelryCategoriesMapper;
import com.cplerings.core.application.jewelry.output.ViewJewelryCategoriesOutput;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCaseImplementation
public class ViewJewelryCategoriesUseCaseImpl extends AbstractUseCase<ViewJewelryCategoriesInput, ViewJewelryCategoriesOutput> implements ViewJewelryCategoriesUseCase {

    private final ViewJewelryCategoriesDataSource viewJewelryCategoriesDataSource;
    private final AViewJewelryCategoriesMapper aViewJewelryCategoriesMapper;

    @Override
    protected void validateInput(UseCaseValidator validator, ViewJewelryCategoriesInput input) {
        super.validateInput(validator, input);
        validator.validate(input.getPage() >= 0, INVALID_PAGE);
        validator.validate(input.getPageSize() > 0, INVALID_PAGE_SIZE);
    }

    @Override
    protected ViewJewelryCategoriesOutput internalExecute(UseCaseValidator validator, ViewJewelryCategoriesInput input) {
        var result = viewJewelryCategoriesDataSource.getJewelryCategories(input);
        return aViewJewelryCategoriesMapper.toOutput(result);
    }
}
