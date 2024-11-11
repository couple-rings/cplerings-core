package com.cplerings.core.application.crafting.implementation;

import static com.cplerings.core.application.design.error.DesignErrorCode.INVALID_PAGE;
import static com.cplerings.core.application.design.error.DesignErrorCode.INVALID_PAGE_SIZE;

import com.cplerings.core.application.crafting.ViewCraftingRequestsUseCase;
import com.cplerings.core.application.crafting.datasource.ViewCraftingRequestsDataSource;
import com.cplerings.core.application.crafting.datasource.result.CraftingRequests;
import com.cplerings.core.application.crafting.input.ViewCraftingRequestsInput;
import com.cplerings.core.application.crafting.mapper.AViewCraftingRequestsMapper;
import com.cplerings.core.application.crafting.output.ViewCraftingRequestsOutput;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;

import lombok.RequiredArgsConstructor;

@UseCaseImplementation
@RequiredArgsConstructor
public class ViewCraftingRequestsUseCaseImpl extends AbstractUseCase<ViewCraftingRequestsInput, ViewCraftingRequestsOutput> implements ViewCraftingRequestsUseCase {

    private final ViewCraftingRequestsDataSource viewCraftingRequestsDataSource;
    private final AViewCraftingRequestsMapper aViewCraftingRequestsMapper;

    @Override
    protected void validateInput(UseCaseValidator validator, ViewCraftingRequestsInput input) {
        super.validateInput(validator, input);
        validator.validate(input.getPage() >= 0, INVALID_PAGE);
        validator.validate(input.getPageSize() > 0, INVALID_PAGE_SIZE);
    }

    @Override
    protected ViewCraftingRequestsOutput internalExecute(UseCaseValidator validator, ViewCraftingRequestsInput input) {
        CraftingRequests result = viewCraftingRequestsDataSource.getCraftingrequests(input);
        return aViewCraftingRequestsMapper.toOutput(result);
    }
}
