package com.cplerings.core.application.jewelry.implementation;

import com.cplerings.core.application.jewelry.CreateJewelryUseCase;
import com.cplerings.core.application.jewelry.datasource.CreateJewelryDataSource;
import com.cplerings.core.application.jewelry.error.CreateJewelryErrorCode;
import com.cplerings.core.application.jewelry.input.CreateJewelryInput;
import com.cplerings.core.application.jewelry.mapper.ACreateJewelryMapper;
import com.cplerings.core.application.jewelry.output.CreateJewelryOutput;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.domain.branch.Branch;
import com.cplerings.core.domain.design.Design;
import com.cplerings.core.domain.diamond.Diamond;
import com.cplerings.core.domain.jewelry.Jewelry;
import com.cplerings.core.domain.jewelry.JewelryStatus;
import com.cplerings.core.domain.metal.MetalSpecification;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCaseImplementation
public class CreateJewelryUseCaseImpl extends AbstractUseCase<CreateJewelryInput, CreateJewelryOutput> implements CreateJewelryUseCase {

    private final CreateJewelryDataSource createJewelryDataSource;
    private final ACreateJewelryMapper aCreateJewelryMapper;

    @Override
    protected void validateInput(UseCaseValidator validator, CreateJewelryInput input) {
        super.validateInput(validator, input);
        validator.validateAndStopExecution(input.branchId() != null, CreateJewelryErrorCode.BRANCH_ID_REQUIRED);
        validator.validateAndStopExecution(input.designId() != null, CreateJewelryErrorCode.DESIGN_ID_REQUIRED);
        validator.validateAndStopExecution(input.diamondId() != null, CreateJewelryErrorCode.DIAMOND_ID_REQUIRED);
        validator.validateAndStopExecution(input.metalSpecId() != null, CreateJewelryErrorCode.METAL_SPEC_ID_REQUIRED);
        validator.clearAndThrowErrorCodes();
        validator.validateAndStopExecution(input.designId() > 0, CreateJewelryErrorCode.DESIGN_ID_WRONG_INTEGER);
        validator.validateAndStopExecution(input.branchId() > 0, CreateJewelryErrorCode.BRANCH_ID_WRONG_INTEGER);
        validator.validateAndStopExecution(input.diamondId() > 0, CreateJewelryErrorCode.DIAMOND_ID_WRONG_INTEGER);
        validator.validateAndStopExecution(input.metalSpecId() > 0, CreateJewelryErrorCode.METAL_SPEC_ID_WRONG_INTEGER);
    }

    @Override
    protected CreateJewelryOutput internalExecute(UseCaseValidator validator, CreateJewelryInput input) {
        Branch branch = createJewelryDataSource.getBranchById(input.branchId())
                .orElse(null);
        validator.validateAndStopExecution(branch != null, CreateJewelryErrorCode.BRANCH_NOT_FOUND);
            Design design = createJewelryDataSource.getDesignById(input.designId())
                .orElse(null);
        validator.validateAndStopExecution(design != null, CreateJewelryErrorCode.DESIGN_NOT_FOUND);

        Diamond diamond = createJewelryDataSource.getDiamondById(input.diamondId())
                .orElse(null);
        validator.validateAndStopExecution(diamond != null, CreateJewelryErrorCode.DIAMOND_NOT_FOUND);
        MetalSpecification metalSpecification = createJewelryDataSource.getMetalSpecById(input.metalSpecId())
                .orElse(null);
        validator.validateAndStopExecution(metalSpecification != null, CreateJewelryErrorCode.METAL_SPEC_NOT_FOUND);

        Jewelry jewelry = Jewelry.builder()
                .branch(branch)
                .design(design)
                .diamond(diamond)
                .metalSpecification(metalSpecification)
                .status(JewelryStatus.AVAILABLE)
                .build();
        Jewelry jewelryCreated = createJewelryDataSource.save(jewelry);
        return aCreateJewelryMapper.toOutput(jewelryCreated);
    }
}
