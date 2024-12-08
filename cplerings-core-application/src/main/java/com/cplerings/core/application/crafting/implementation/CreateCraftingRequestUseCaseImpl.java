package com.cplerings.core.application.crafting.implementation;

import com.cplerings.core.application.crafting.CreateCraftingRequestUseCase;
import com.cplerings.core.application.crafting.datasource.CreateCraftingRequestDataSource;
import com.cplerings.core.application.crafting.error.CreateCraftingRequestErrorCode;
import com.cplerings.core.application.crafting.input.CreateCraftingRequestInput;
import com.cplerings.core.application.crafting.mapper.ACreateCraftingRequestMapper;
import com.cplerings.core.application.crafting.output.CreateCraftingRequestOutput;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.branch.Branch;
import com.cplerings.core.domain.design.CustomDesign;
import com.cplerings.core.domain.design.crafting.CraftingRequest;
import com.cplerings.core.domain.design.crafting.CraftingRequestHistory;
import com.cplerings.core.domain.design.crafting.CraftingRequestStatus;
import com.cplerings.core.domain.diamond.DiamondSpecification;
import com.cplerings.core.domain.metal.MetalSpecification;

import lombok.RequiredArgsConstructor;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@UseCaseImplementation
public class CreateCraftingRequestUseCaseImpl extends AbstractUseCase<CreateCraftingRequestInput, CreateCraftingRequestOutput> implements CreateCraftingRequestUseCase {

    private final CreateCraftingRequestDataSource dataSource;
    private final ACreateCraftingRequestMapper aCreateCraftingRequestMapper;

    @Override
    protected void validateInput(UseCaseValidator validator, CreateCraftingRequestInput input) {
        super.validateInput(validator, input);
        if (input.customerId() != null) {
            validator.validateAndStopExecution(input.customerId() > 0, CreateCraftingRequestErrorCode.CUSTOMER_ID_WRONG_POSITIVE_INTEGER);
        }

        if (input.customDesignId() != null) {
            validator.validateAndStopExecution(input.customDesignId() > 0, CreateCraftingRequestErrorCode.CUSTOM_DESIGN_ID_WRONG_POSITIVE_INTEGER);
        }

        if (input.diamondSpecId() != null) {
            validator.validateAndStopExecution(input.diamondSpecId() > 0, CreateCraftingRequestErrorCode.DIAMOND_SPECIFICATION_ID_WRONG_POSITIVE_INTEGER);
        }

        if (input.metalSpecId() != null) {
            validator.validateAndStopExecution(input.metalSpecId() > 0, CreateCraftingRequestErrorCode.METAL_SPECIFICATION_ID_WRONG_POSITIVE_INTEGER);
        }

        if (input.fingerSize() != null) {
            validator.validateAndStopExecution(input.fingerSize() > 0, CreateCraftingRequestErrorCode.FINGER_SIZE_WRONG_POSITIVE_INTEGER);
        }

        validator.validate(input.branchId() != null, CreateCraftingRequestErrorCode.BRANCH_ID_REQUIRED);
        validator.clearAndThrowErrorCodes();

        validator.validate(input.branchId() > 0, CreateCraftingRequestErrorCode.INVALID_BRANCH_ID);
    }

    @Override
    protected CreateCraftingRequestOutput internalExecute(UseCaseValidator validator, CreateCraftingRequestInput input) {
        Account customer = dataSource.getAccountByCustomerId(input.customerId())
                .orElse(null);
        validator.validateAndStopExecution(customer != null, CreateCraftingRequestErrorCode.CUSTOMER_NOT_FOUND);
        if (CollectionUtils.isNotEmpty(customer.getCraftingRequests())) {
            final Collection<CraftingRequest> previousCraftingRequests = customer.getCraftingRequests()
                    .stream()
                    .filter(craftingRequest -> craftingRequest.getCraftingRequestStatus() == CraftingRequestStatus.PENDING)
                    .collect(Collectors.toSet());
            validator.validateAndStopExecution(previousCraftingRequests.size() <= 2, CreateCraftingRequestErrorCode.MAX_ALLOWED_CRAFTING_REQUESTS_EXCEEDED);
        }
        Branch branch = dataSource.getBranchById(input.branchId())
                .orElse(null);
        validator.validateAndStopExecution(branch != null, CreateCraftingRequestErrorCode.BRANCH_NOT_FOUND);
        DiamondSpecification diamondSpecification = dataSource.getDiamondSpecificationByDiamondSpecId(input.diamondSpecId())
                .orElse(null);
        validator.validateAndStopExecution(diamondSpecification != null, CreateCraftingRequestErrorCode.DIAMOND_SPECIFICATION_NOT_FOUND);
        CustomDesign customDesign = dataSource.getCustomDesignByCustomDesignId(input.customDesignId())
                .orElse(null);
        validator.validateAndStopExecution(customDesign != null, CreateCraftingRequestErrorCode.CUSTOM_DESIGN_NOT_FOUND);
        MetalSpecification metalSpecification = dataSource.getMetalSpecificationByMetalSpecId(input.metalSpecId())
                .orElse(null);
        validator.validateAndStopExecution(metalSpecification != null, CreateCraftingRequestErrorCode.METAL_SPECIFICATION_NOT_FOUND);
        CraftingRequest craftingRequest = CraftingRequest.builder()
                .craftingRequestStatus(CraftingRequestStatus.PENDING)
                .diamondSpecification(diamondSpecification)
                .customDesign(customDesign)
                .engraving(StringUtils.isNotBlank(input.engraving()) ? input.engraving().trim() : null)
                .fingerSize(input.fingerSize())
                .metalSpecification(metalSpecification)
                .customer(customer)
                .branch(branch)
                .build();
        CraftingRequest craftingRequestCreated = dataSource.save(craftingRequest);
        CraftingRequestHistory craftingRequestHistory = CraftingRequestHistory.builder()
                .craftingRequest(craftingRequestCreated)
                .status(CraftingRequestStatus.PENDING)
                .build();
        dataSource.save(craftingRequestHistory);

        return aCreateCraftingRequestMapper.toOutput(craftingRequestCreated);
    }
}
