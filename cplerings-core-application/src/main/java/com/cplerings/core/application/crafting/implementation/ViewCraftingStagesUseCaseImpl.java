package com.cplerings.core.application.crafting.implementation;

import static com.cplerings.core.application.design.error.DesignErrorCode.INVALID_PAGE;
import static com.cplerings.core.application.design.error.DesignErrorCode.INVALID_PAGE_SIZE;

import com.cplerings.core.application.crafting.ViewCraftingStagesUseCase;
import com.cplerings.core.application.crafting.datasource.ViewCraftingStagesDataSource;
import com.cplerings.core.application.crafting.input.ViewCraftingStagesInput;
import com.cplerings.core.application.crafting.mapper.AViewCraftingStagesMapper;
import com.cplerings.core.application.crafting.output.ViewCraftingStagesOutput;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCaseImplementation
public class ViewCraftingStagesUseCaseImpl extends AbstractUseCase<ViewCraftingStagesInput, ViewCraftingStagesOutput> implements ViewCraftingStagesUseCase {

    private final ViewCraftingStagesDataSource viewCraftingStagesDataSource;
    private final AViewCraftingStagesMapper aViewCraftingStagesMapper;

    @Override
    protected void validateInput(UseCaseValidator validator, ViewCraftingStagesInput input) {
        super.validateInput(validator, input);
        validator.validate(input.getPage() >= 0, INVALID_PAGE);
        validator.validate(input.getPageSize() > 0, INVALID_PAGE_SIZE);
    }

    @Override
    protected ViewCraftingStagesOutput internalExecute(UseCaseValidator validator, ViewCraftingStagesInput input) {
        var result = viewCraftingStagesDataSource.getCraftingStages(input);
        return aViewCraftingStagesMapper.toOutput(result);
    }
}
