package com.cplerings.core.application.crafting.implementation;

import static com.cplerings.core.application.design.error.DesignErrorCode.INVALID_PAGE;
import static com.cplerings.core.application.design.error.DesignErrorCode.INVALID_PAGE_SIZE;

import com.cplerings.core.application.crafting.ViewCraftingRequestsGroupsUseCase;
import com.cplerings.core.application.crafting.datasource.ViewCraftingRequestsGroupsDataSource;
import com.cplerings.core.application.crafting.input.ViewCraftingRequestsGroupsInput;
import com.cplerings.core.application.crafting.mapper.AViewCraftingRequestsGroupsMapper;
import com.cplerings.core.application.crafting.output.ViewCraftingRequestsGroupsOutput;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;

import lombok.RequiredArgsConstructor;

@UseCaseImplementation
@RequiredArgsConstructor
public class ViewCraftingRequestsGroupsUseCaseImpl extends AbstractUseCase<ViewCraftingRequestsGroupsInput, ViewCraftingRequestsGroupsOutput> implements ViewCraftingRequestsGroupsUseCase {

    private final ViewCraftingRequestsGroupsDataSource viewCraftingRequestsGroupsDataSource;
    private final AViewCraftingRequestsGroupsMapper aViewCraftingRequestsMapper;

    @Override
    protected void validateInput(UseCaseValidator validator, ViewCraftingRequestsGroupsInput input) {
        super.validateInput(validator, input);
        validator.validate(input.getPage() >= 0, INVALID_PAGE);
        validator.validate(input.getPageSize() > 0, INVALID_PAGE_SIZE);
    }

    @Override
    protected ViewCraftingRequestsGroupsOutput internalExecute(UseCaseValidator validator, ViewCraftingRequestsGroupsInput input) {
        var result = viewCraftingRequestsGroupsDataSource.getAccountCraftingRequests(input);
        return aViewCraftingRequestsMapper.toOutput(result);
    }
}
