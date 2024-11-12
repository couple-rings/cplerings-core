package com.cplerings.core.application.crafting.implementation;

import com.cplerings.core.application.crafting.ViewCraftingRequestUseCase;
import com.cplerings.core.application.crafting.datasource.ViewCraftingRequestDataSource;
import com.cplerings.core.application.crafting.error.ViewCraftingRequestErrorCode;
import com.cplerings.core.application.crafting.input.ViewCraftingRequestInput;
import com.cplerings.core.application.crafting.mapper.AViewCraftingRequestMapper;
import com.cplerings.core.application.crafting.output.ViewCraftingRequestOutput;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.domain.design.crafting.CraftingRequest;

import lombok.RequiredArgsConstructor;

@UseCaseImplementation
@RequiredArgsConstructor
public class ViewCraftingRequestUseCaseImpl extends AbstractUseCase<ViewCraftingRequestInput, ViewCraftingRequestOutput> implements ViewCraftingRequestUseCase {

    private final AViewCraftingRequestMapper aViewCraftingRequestMapper;
    private final ViewCraftingRequestDataSource viewCraftingRequestDataSource;

    @Override
    protected void validateInput(UseCaseValidator validator, ViewCraftingRequestInput input) {
        super.validateInput(validator, input);
        validator.validate(input.craftingRequestId() > 0, ViewCraftingRequestErrorCode.WRONG_ID_POSITIVE_INTEGER);
    }

    @Override
    protected ViewCraftingRequestOutput internalExecute(UseCaseValidator validator, ViewCraftingRequestInput input) {
        CraftingRequest craftingRequest = viewCraftingRequestDataSource.getCraftingRequest(input.craftingRequestId())
                .orElse(null);
        validator.validateAndStopExecution(craftingRequest != null, ViewCraftingRequestErrorCode.INVALID_CRAFTING_REQUEST_ID);
        return aViewCraftingRequestMapper.toOutput(craftingRequest);
    }
}
