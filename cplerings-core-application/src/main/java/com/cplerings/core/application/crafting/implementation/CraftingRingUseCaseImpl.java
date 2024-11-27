package com.cplerings.core.application.crafting.implementation;

import com.cplerings.core.application.crafting.CraftingRingUseCase;
import com.cplerings.core.application.crafting.datasource.CraftingRingDataSource;
import com.cplerings.core.application.crafting.error.CraftingRingErrorCode;
import com.cplerings.core.application.crafting.input.CraftingRingInput;
import com.cplerings.core.application.shared.output.NoOutput;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.branch.Branch;
import com.cplerings.core.domain.design.CustomDesign;
import com.cplerings.core.domain.design.Design;
import com.cplerings.core.domain.design.DesignStatus;
import com.cplerings.core.domain.design.DesignVersion;
import com.cplerings.core.domain.design.DesignVersionOwner;
import com.cplerings.core.domain.design.crafting.CraftingRequest;
import com.cplerings.core.domain.design.crafting.CraftingRequestStatus;
import com.cplerings.core.domain.diamond.DiamondSpecification;
import com.cplerings.core.domain.metal.MetalSpecification;
import com.cplerings.core.domain.spouse.Spouse;

import lombok.RequiredArgsConstructor;

@UseCaseImplementation
@RequiredArgsConstructor
public class CraftingRingUseCaseImpl extends AbstractUseCase<CraftingRingInput, NoOutput> implements CraftingRingUseCase {

    private final CraftingRingDataSource craftingRingDataSource;

    @Override
    protected void validateInput(UseCaseValidator validator, CraftingRingInput input) {
        super.validateInput(validator, input);
        validator.validate(input.branchId() != null, CraftingRingErrorCode.BRANCH_ID_REQUIRED);
        validator.validate(input.customerId() != null, CraftingRingErrorCode.CUSTOMER_ID_REQUIRED);
        validator.validate(input.self() != null, CraftingRingErrorCode.SELF_REQUIRED);
        validator.validate(input.partner() != null, CraftingRingErrorCode.PARTNER_REQUIRED);
        validator.clearAndThrowErrorCodes();
        validator.validate(input.self().diamondSpecId() != null, CraftingRingErrorCode.DIAMOND_SPEC_ID_REQUIRED);
        validator.validate(input.self().designId() != null, CraftingRingErrorCode.DESIGN_ID_REQUIRED);
        validator.validate(input.self().metalSpecId() != null, CraftingRingErrorCode.METAL_SPEC_ID_REQUIRED);
        validator.validate(input.self().fingerSize() != null, CraftingRingErrorCode.FINGER_SIZE_REQUIRED);
        validator.validate(input.self().spouseId() != null, CraftingRingErrorCode.SPOUSE_ID_REQUIRED);
        validator.validate(input.partner().diamondSpecId() != null, CraftingRingErrorCode.DIAMOND_SPEC_ID_REQUIRED);
        validator.validate(input.partner().designId() != null, CraftingRingErrorCode.DESIGN_ID_REQUIRED);
        validator.validate(input.partner().metalSpecId() != null, CraftingRingErrorCode.METAL_SPEC_ID_REQUIRED);
        validator.validate(input.partner().fingerSize() != null, CraftingRingErrorCode.FINGER_SIZE_REQUIRED);
        validator.validate(input.partner().spouseId() != null, CraftingRingErrorCode.SPOUSE_ID_REQUIRED);
        validator.clearAndThrowErrorCodes();
        validator.validate(input.self().diamondSpecId() > 0, CraftingRingErrorCode.DIAMOND_SPEC_ID_WRONG_INTEGER);
        validator.validate(input.self().designId() > 0, CraftingRingErrorCode.DESIGN_ID_WRONG_INTEGER);
        validator.validate(input.self().metalSpecId() > 0, CraftingRingErrorCode.METAL_SPEC_ID_WRONG_INTEGER);
        validator.validate(input.self().fingerSize() > 0, CraftingRingErrorCode.FINGER_SIZE_WRONG_INTEGER);
        validator.validate(input.self().spouseId() > 0, CraftingRingErrorCode.SPOUSE_ID_WRONG_INTEGER);
        validator.validate(input.partner().diamondSpecId() > 0, CraftingRingErrorCode.DIAMOND_SPEC_ID_WRONG_INTEGER);
        validator.validate(input.partner().designId() > 0, CraftingRingErrorCode.DESIGN_ID_WRONG_INTEGER);
        validator.validate(input.partner().metalSpecId() > 0, CraftingRingErrorCode.METAL_SPEC_ID_WRONG_INTEGER);
        validator.validate(input.partner().fingerSize() > 0, CraftingRingErrorCode.FINGER_SIZE_WRONG_INTEGER);
        validator.validateAndStopExecution(input.partner().spouseId() > 0, CraftingRingErrorCode.SPOUSE_ID_WRONG_INTEGER);
    }

    @Override
    protected NoOutput internalExecute(UseCaseValidator validator, CraftingRingInput input) {
        Account customer = craftingRingDataSource.getCustomerById(input.customerId())
                .orElse(null);
        validator.validateAndStopExecution(customer != null, CraftingRingErrorCode.CUSTOMER_NOT_FOUND);

        Branch branch = craftingRingDataSource.getBranchById(input.branchId())
                .orElse(null);
        validator.validateAndStopExecution(branch != null, CraftingRingErrorCode.BRANCH_NOT_FOUND);

        Spouse selfSpouse = craftingRingDataSource.getSpouseById(input.self().spouseId())
                .orElse(null);
        validator.validateAndStopExecution(selfSpouse != null, CraftingRingErrorCode.SPOUSE_NOT_FOUND);

        Spouse partnerSpouse = craftingRingDataSource.getSpouseById(input.partner().spouseId())
                .orElse(null);
        validator.validateAndStopExecution(partnerSpouse != null, CraftingRingErrorCode.SPOUSE_NOT_FOUND);

        Design selfDesign = craftingRingDataSource.getDesignByDesignId(input.self().designId()) // fetch metal spec
                .orElse(null);
        validator.validateAndStopExecution(selfDesign != null, CraftingRingErrorCode.DESIGN_NOT_FOUND);

        Design partnerDesign = craftingRingDataSource.getDesignByDesignId(input.self().designId())
                .orElse(null);
        validator.validateAndStopExecution(partnerDesign != null, CraftingRingErrorCode.DESIGN_NOT_FOUND);

        MetalSpecification selfnerMetalSpecification = craftingRingDataSource.getMetalSpecificationById(input.self().metalSpecId())
                .orElse(null);
        validator.validateAndStopExecution(selfnerMetalSpecification != null, CraftingRingErrorCode.METAL_SPEC_NOT_FOUND);

        MetalSpecification partnernerMetalSpecification = craftingRingDataSource.getMetalSpecificationById(input.self().metalSpecId())
                .orElse(null);
        validator.validateAndStopExecution(partnernerMetalSpecification != null, CraftingRingErrorCode.METAL_SPEC_NOT_FOUND);

        DiamondSpecification selfDiamondSpecification = craftingRingDataSource.getDiamondSpecificationById(input.self().diamondSpecId())
                .orElse(null);
        validator.validateAndStopExecution(selfDiamondSpecification != null, CraftingRingErrorCode.DIAMOND_SPEC_NOT_FOUND);

        DiamondSpecification partnerDiamondSpecification = craftingRingDataSource.getDiamondSpecificationById(input.self().diamondSpecId())
                .orElse(null);
        validator.validateAndStopExecution(partnerDiamondSpecification != null, CraftingRingErrorCode.DIAMOND_SPEC_NOT_FOUND);

        // Create design version 0 for self and partner
        DesignVersion selfDesignVersion = createDesignVersion(selfDesign, customer, true);
        DesignVersion partnerDesignVersion = createDesignVersion(partnerDesign, customer, false);

        // Create Custom
        CustomDesign selfCustomDesign = createCustomDesign(selfDesignVersion, customer, selfSpouse, selfDesign);
        CustomDesign partnerCustomDesign = createCustomDesign(partnerDesignVersion, customer, partnerSpouse, partnerDesign);

        // Create Crafting request
        createCraftingRequest(selfCustomDesign, customer, input.self().engraving(), selfnerMetalSpecification, selfDiamondSpecification, branch, input.self().fingerSize());
        createCraftingRequest(partnerCustomDesign, customer, input.self().engraving(), partnernerMetalSpecification, partnerDiamondSpecification, branch, input.partner().fingerSize());

        selfDesign.setStatus(DesignStatus.UNAVAILABLE);
        partnerDesign.setStatus(DesignStatus.UNAVAILABLE);


        return NoOutput.INSTANCE;
    }

    private DesignVersion createDesignVersion(Design design, Account customer, boolean isOwner) {
        DesignVersion designVersion = DesignVersion.builder()
                .customer(customer)
                .design(design)
                .isOld(false)
                .isAccepted(true)
                .designFile(design.getBlueprint())
                .image(design.getDesignMetalSpecifications().stream().findAny().get().getImage())
                .owner(isOwner ? DesignVersionOwner.SELF : DesignVersionOwner.PARTNER)
                .versionNumber(0)
                .build();
        return craftingRingDataSource.save(designVersion);
    }

    private CustomDesign createCustomDesign(DesignVersion designVersion, Account customer, Spouse spouse, Design design) {
        CustomDesign customerDesign = CustomDesign.builder()
                .designVersion(designVersion)
                .sideDiamondsCount(design.getSideDiamondsCount())
                .spouse(spouse)
                .account(customer)
                .blueprint(designVersion.getDesignFile())
                .metalWeight(design.getMetalWeight())
                .build();
        return craftingRingDataSource.saveCustomDesign(customerDesign);
    }

    private CraftingRequest createCraftingRequest(CustomDesign customDesign, Account customer, String engraving, MetalSpecification metalSpecification, DiamondSpecification diamondSpecification, Branch branch, Integer fingerSize) {
        CraftingRequest customerDesign = CraftingRequest.builder()
                .engraving(engraving)
                .craftingRequestStatus(CraftingRequestStatus.PENDING)
                .customDesign(customDesign)
                .metalSpecification(metalSpecification)
                .diamondSpecification(diamondSpecification)
                .fingerSize(fingerSize)
                .branch(branch)
                .customer(customer)
                .build();
        return craftingRingDataSource.save(customerDesign);
    }
}
