package com.cplerings.core.application.jewelry.implementation;

import static com.cplerings.core.application.jewelry.error.ResellJewelryErrorCode.JEWELRY_NOT_FOUND;

import com.cplerings.core.application.jewelry.UpdateJewelryUseCase;
import com.cplerings.core.application.jewelry.datasource.UpdateJewelryDataSource;
import com.cplerings.core.application.jewelry.input.UpdateJewelryInput;
import com.cplerings.core.application.jewelry.mapper.AUpdateJewelryMapper;
import com.cplerings.core.application.jewelry.output.UpdateJewelryOutput;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.domain.design.Design;
import com.cplerings.core.domain.jewelry.Jewelry;
import com.cplerings.core.domain.metal.MetalSpecification;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCaseImplementation
public class UpdateJewelryUseCaseImpl extends AbstractUseCase<UpdateJewelryInput, UpdateJewelryOutput> implements UpdateJewelryUseCase {

    private final AUpdateJewelryMapper aUpdateJewelryMapper;
    private final UpdateJewelryDataSource updateJewelryDataSource;

    @Override
    protected UpdateJewelryOutput internalExecute(UseCaseValidator validator, UpdateJewelryInput input) {
        Jewelry jewelry = updateJewelryDataSource.getJewelryById(input.jewelryId())
                .orElse(null);
        validator.validateAndStopExecution(jewelry != null, JEWELRY_NOT_FOUND);
        Design design = updateJewelryDataSource.getDesignById(input.designId())
                .orElse(null);
        MetalSpecification metalSpecification = updateJewelryDataSource.getMetalSpecificationById(input.designId())
                .orElse(null);

        jewelry.setDesign(design);
        jewelry.setMetalSpecification(metalSpecification);
        jewelry = updateJewelryDataSource.save(jewelry);
        return aUpdateJewelryMapper.toOutput(jewelry);
    }
}
