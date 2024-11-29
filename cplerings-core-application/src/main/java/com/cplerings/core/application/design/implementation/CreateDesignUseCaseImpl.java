package com.cplerings.core.application.design.implementation;

import com.cplerings.core.application.design.CreateDesignUseCase;
import com.cplerings.core.application.design.datasource.CreateDesignDataSource;
import com.cplerings.core.application.design.error.CreateDesignErrorCode;
import com.cplerings.core.application.design.input.CreateDesignInput;
import com.cplerings.core.application.design.mapper.ACreateDesignMapper;
import com.cplerings.core.application.design.output.CreateDesignOutput;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.domain.design.Design;
import com.cplerings.core.domain.design.DesignCharacteristic;
import com.cplerings.core.domain.design.DesignCollection;
import com.cplerings.core.domain.design.DesignStatus;
import com.cplerings.core.domain.file.Document;
import com.cplerings.core.domain.jewelry.JewelryCategory;
import com.cplerings.core.domain.shared.valueobject.DesignSize;
import com.cplerings.core.domain.shared.valueobject.Weight;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCaseImplementation
public class CreateDesignUseCaseImpl extends AbstractUseCase<CreateDesignInput, CreateDesignOutput> implements CreateDesignUseCase {

    private final CreateDesignDataSource createDesignDataSource;
    private final ACreateDesignMapper aCreateDesignMapper;

    @Override
    protected void validateInput(UseCaseValidator validator, CreateDesignInput input) {
        super.validateInput(validator, input);
        validator.validate(input.collectionId() != null, CreateDesignErrorCode.COLLECTION_ID_REQUIRED);
        validator.validate(input.jewelryCategoryId() != null, CreateDesignErrorCode.JEWELRY_CATEGORY_ID_REQUIRED);
        validator.validate(input.metalWeight() != null, CreateDesignErrorCode.METAL_WEIGHT_REQUIRED);
        validator.validate(input.description() != null, CreateDesignErrorCode.DESCRIPTION_REQUIRED);
        validator.validate(input.blueprintId() != null, CreateDesignErrorCode.BLUEPRINT_ID_REQUIRED);
        validator.validate(input.characteristic() != null, CreateDesignErrorCode.CHARACTERISTIC_REQUIRED);
        validator.validate(input.size() != null, CreateDesignErrorCode.SIZE_REQUIRED);
        validator.validate(input.sideDiamond() != null, CreateDesignErrorCode.SIZE_DIAMOND_REQUIRED);
        validator.validate(input.name() != null, CreateDesignErrorCode.NAME_REQUIRED);
        validator.clearAndThrowErrorCodes();
        validator.validate(input.collectionId() > 0, CreateDesignErrorCode.COLLECTION_ID_WRONG_INTEGER);
        validator.validate(input.jewelryCategoryId() > 0, CreateDesignErrorCode.JEWELRY_CATEGORY_ID_WRONG_INTEGER);
        validator.validate(input.blueprintId() > 0, CreateDesignErrorCode.BLUEPRINT_ID_WRONG_INTEGER);
        validator.validate(input.size() > 0, CreateDesignErrorCode.SIZE_WRONG_INTEGER);
        validator.validate(input.sideDiamond() > 0, CreateDesignErrorCode.SIZE_DIAMOND_WRONG_INTEGER);
    }

    @Override
    protected CreateDesignOutput internalExecute(UseCaseValidator validator, CreateDesignInput input) {
        DesignCollection designCollection = createDesignDataSource.getDesignCollectionById(input.collectionId())
                .orElse(null);
        validator.validateAndStopExecution(designCollection != null, CreateDesignErrorCode.DESIGN_COLLECTION_NOT_FOUND);
        Document blueprint = createDesignDataSource.getBlueprintById(input.blueprintId())
                .orElse(null);
        validator.validateAndStopExecution(blueprint != null, CreateDesignErrorCode.BLUEPRINT_NOT_FOUND);
        JewelryCategory jewelryCategory = createDesignDataSource.getJewelryCategoryById(input.jewelryCategoryId())
                .orElse(null);
        validator.validateAndStopExecution(blueprint != null, CreateDesignErrorCode.JEWELRY_CATEGORY_ID_NOT_FOUND);
        Design design = Design.builder()
                .blueprint(blueprint)
                .designCollection(designCollection)
                .description(input.description())
                .jewelryCategory(jewelryCategory)
                .sideDiamondsCount(input.sideDiamond())
                .metalWeight(Weight.create(input.metalWeight()))
                .size(DesignSize.create(input.size()))
                .name(input.name())
                .status(DesignStatus.AVAILABLE)
                .build();
        switch (input.characteristic()) {
            case ANDROGYNOUS -> design.setCharacteristic(DesignCharacteristic.ANDROGYNOUS);
            case FEMININE -> design.setCharacteristic(DesignCharacteristic.FEMININE);
            case MASCULINE -> design.setCharacteristic(DesignCharacteristic.MASCULINE);
        }
        Design designCreated = createDesignDataSource.save(design);
        return aCreateDesignMapper.toOutput(designCreated);
    }
}
