package com.cplerings.core.application.craftingrequest.implementation;

import com.cplerings.core.application.craftingrequest.CreateCraftingRequestUseCase;
import com.cplerings.core.application.craftingrequest.datasource.CreateCraftingRequestDataSource;
import com.cplerings.core.application.craftingrequest.error.CraftingRequestErrorCode;
import com.cplerings.core.application.craftingrequest.input.CreateCraftingRequestInput;
import com.cplerings.core.application.craftingrequest.mapper.ACreateCraftingRequestMapper;
import com.cplerings.core.application.craftingrequest.output.CreateCraftingRequestOutput;
import com.cplerings.core.application.design.error.DesignErrorCode;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.design.CustomDesign;
import com.cplerings.core.domain.design.crafting.CraftingRequest;
import com.cplerings.core.domain.design.crafting.CraftingRequestStatus;
import com.cplerings.core.domain.diamond.DiamondSpecification;
import com.cplerings.core.domain.metal.MetalSpecification;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCaseImplementation
public class CreateCraftingRequestImpl extends AbstractUseCase<CreateCraftingRequestInput, CreateCraftingRequestOutput> implements CreateCraftingRequestUseCase {

    private final CreateCraftingRequestDataSource createCraftingRequestDataSource;
    private final ACreateCraftingRequestMapper aCreateCraftingRequestMapper;

    @Override
    protected void validateInput(UseCaseValidator validator, CreateCraftingRequestInput input) {
        super.validateInput(validator, input);
        if (input.customerId() != null) {
            validator.validateAndStopExecution(input.customerId() > 0, CraftingRequestErrorCode.CUSTOMER_ID_WRONG_POSITIVE_INTEGER);
        }

        if (input.customDesignId() != null) {
            validator.validateAndStopExecution(input.customDesignId() > 0, CraftingRequestErrorCode.CUSTOM_DESIGN_ID_WRONG_POSITIVE_INTEGER);
        }

        if (input.diamondSpecId() != null) {
            validator.validateAndStopExecution(input.diamondSpecId() > 0, CraftingRequestErrorCode.DIAMOND_SPECIFICATION_ID_WRONG_POSITIVE_INTEGER);
        }

        if (input.metalSpecId() != null) {
            validator.validateAndStopExecution(input.metalSpecId() > 0, CraftingRequestErrorCode.METAL_SPECIFICATION_ID_WRONG_POSITIVE_INTEGER);
        }

        if (input.fingerSize() != null) {
            validator.validateAndStopExecution(input.fingerSize() > 0, CraftingRequestErrorCode.FINGER_SIZE_WRONG_POSITIVE_INTEGER);
        }

    }

    @Override
    protected CreateCraftingRequestOutput internalExecute(UseCaseValidator validator, CreateCraftingRequestInput input) {
        Account customer = createCraftingRequestDataSource.getAccountByCustomerId(input.customerId())
                .orElse(null);
        validator.validateAndStopExecution(customer != null, DesignErrorCode.INVALID_CUSTOMER_ID);
        DiamondSpecification diamondSpecification = createCraftingRequestDataSource.getDiamondSpecificationByDiamondSpecId(input.diamondSpecId())
                .orElse(null);
        validator.validateAndStopExecution(diamondSpecification != null, CraftingRequestErrorCode.INVALID_DIAMOND_SPECIFICATION_ID);
        CustomDesign customDesign = createCraftingRequestDataSource.getCustomDesignByCustomDesignId(input.customDesignId())
                .orElse(null);
        validator.validateAndStopExecution(customDesign != null, CraftingRequestErrorCode.INVALID_CUSTOM_DESIGN_ID);
        MetalSpecification metalSpecification = createCraftingRequestDataSource.getMetalSpecificationByMetalSpecId(input.metalSpecId())
                .orElse(null);
        validator.validateAndStopExecution(metalSpecification != null, CraftingRequestErrorCode.INVALID_METAL_SPECIFICATION_ID);
        CraftingRequest craftingRequest = CraftingRequest.builder()
                .craftingRequestStatus(CraftingRequestStatus.PENDING)
                .diamondSpecification(diamondSpecification)
                .customDesign(customDesign)
                .engraving(input.engraving() != null ? input.engraving() : null)
                .fingerSize(input.fingerSize())
                .metalSpecification(metalSpecification)
                .customer(customer)
                .build();
        CraftingRequest craftingRequestCreated = createCraftingRequestDataSource.save(craftingRequest);
        return aCreateCraftingRequestMapper.toOutput(craftingRequestCreated);
    }
}
