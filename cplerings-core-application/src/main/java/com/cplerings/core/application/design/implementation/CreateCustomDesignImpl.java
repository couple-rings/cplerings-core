package com.cplerings.core.application.design.implementation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.cplerings.core.application.design.CreateCustomDesignUseCase;
import com.cplerings.core.application.design.datasource.CreateCustomDesignDataSource;
import com.cplerings.core.application.design.error.CreateCustomDesignErrorCode;
import com.cplerings.core.application.design.error.DesignErrorCode;
import com.cplerings.core.application.design.input.CreateCustomDesignInput;
import com.cplerings.core.application.design.mapper.ACreateCustomDesignMapper;
import com.cplerings.core.application.design.output.CreateCustomDesignOutput;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.design.CustomDesign;
import com.cplerings.core.domain.design.CustomDesignDiamondSpecification;
import com.cplerings.core.domain.design.CustomDesignMetalSpecification;
import com.cplerings.core.domain.design.DesignVersion;
import com.cplerings.core.domain.diamond.DiamondSpecification;
import com.cplerings.core.domain.file.Document;
import com.cplerings.core.domain.metal.MetalSpecification;
import com.cplerings.core.domain.shared.valueobject.Weight;
import com.cplerings.core.domain.spouse.Spouse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCaseImplementation
public class CreateCustomDesignImpl extends AbstractUseCase<CreateCustomDesignInput, CreateCustomDesignOutput> implements CreateCustomDesignUseCase {

    private final CreateCustomDesignDataSource createCustomDesignDataSource;
    private final ACreateCustomDesignMapper aCreateCustomDesignMapper;

    @Override
    protected void validateInput(UseCaseValidator validator, CreateCustomDesignInput input) {
        super.validateInput(validator, input);
        validator.validateAndStopExecution(input.blueprintId() != null, DesignErrorCode.BLUEPRINT_REQUIRED);
        validator.validateAndStopExecution(input.metalWeight() != null, DesignErrorCode.METAL_WEIGHT_REQUIRED);
        validator.validateAndStopExecution(input.designVersionId() > 0, DesignErrorCode.DESIGN_VERSION_ID_WRONG_POSITIVE_NUMBER);
        validator.validateAndStopExecution(input.sideDiamondAmount() > 0, DesignErrorCode.SIDE_DIAMOND_AMOUNT_WRONG_POSITIVE_NUMBER);
        validator.validateAndStopExecution(input.customerId() > 0, DesignErrorCode.CUSTOMER_ID_WRONG_POSITIVE_NUMBER);
        validator.validateAndStopExecution(input.spouseId() > 0, DesignErrorCode.SPOUSE_ID_WRONG_POSITIVE_NUMBER);
        validator.validateAndStopExecution(input.diamondSpecIds() != null, CreateCustomDesignErrorCode.DIAMOND_SPEC_IDS_REQUIRED);
        validator.validateAndStopExecution(input.metalSpecIds() != null, CreateCustomDesignErrorCode.METAL_SPEC_IDS_REQUIRED);
    }

    @Override
    protected CreateCustomDesignOutput internalExecute(UseCaseValidator validator, CreateCustomDesignInput input) {
        Account customer = createCustomDesignDataSource.getCustomerById(input.customerId())
                .orElse(null);
        validator.validateAndStopExecution(customer != null, DesignErrorCode.INVALID_CUSTOMER_ID);
        Spouse spouse = createCustomDesignDataSource.getSpouseById(input.spouseId())
                .orElse(null);
        validator.validateAndStopExecution(spouse != null, DesignErrorCode.INVALID_SPOUSE_ID);
        DesignVersion designVersion = createCustomDesignDataSource.getDesignVersionById(input.designVersionId())
                .orElse(null);
        validator.validateAndStopExecution(designVersion != null, DesignErrorCode.INVALID_DESIGN_VERSION_ID);
        validator.validateAndStopExecution(designVersion.getIsAccepted(), CreateCustomDesignErrorCode.DESIGN_VERSION_HAS_NOT_BEEN_ACCEPTED);
        Document document = createCustomDesignDataSource.getDocumentById(input.blueprintId())
                .orElse(null);
        validator.validateAndStopExecution(document != null, CreateCustomDesignErrorCode.INVALID_DOCUMENT);
        CustomDesign customDesignFindBySpouse = createCustomDesignDataSource.getCustomDesignBySpouseId(spouse.getId())
                .orElse(null);
        validator.validateAndStopExecution(customDesignFindBySpouse == null, DesignErrorCode.SPOUSE_HAS_BEEN_LINKED_WITH_CUSTOM_DESIGN);
        CustomDesign customDesignFindByDesignVersion = createCustomDesignDataSource.getCustomDesignByDesignVersionId(designVersion.getId())
                .orElse(null);
        validator.validateAndStopExecution(customDesignFindByDesignVersion == null, DesignErrorCode.DESIGN_VERSION_HAS_BEEN_LINKED_WITH_CUSTOM_DESIGN);
        CustomDesign customDesign = CustomDesign.builder()
                .designVersion(designVersion)
                .sideDiamondsCount(input.sideDiamondAmount())
                .metalWeight(Weight.create(input.metalWeight()))
                .account(customer)
                .spouse(spouse)
                .blueprint(document)
                .build();
        CustomDesign customDesignCreated = createCustomDesignDataSource.save(customDesign);
        List<CustomDesignMetalSpecification> customDesignMetalSpecifications = new ArrayList<>();
        List<MetalSpecification> metalSpecifications = createCustomDesignDataSource.getMetalSpecById(input.metalSpecIds());
        for (var metalSpec : metalSpecifications) {
            CustomDesignMetalSpecification customDesignMetalSpecification = CustomDesignMetalSpecification.builder()
                    .customDesign(customDesignCreated)
                    .metalSpecification(metalSpec)
                    .build();
            customDesignMetalSpecifications.add(customDesignMetalSpecification);
        }
        createCustomDesignDataSource.saveMetalSpec(customDesignMetalSpecifications);
        List<CustomDesignDiamondSpecification> customDesignDiamondSpecifications = new ArrayList<>();
        List<DiamondSpecification> diamondSpecifications = createCustomDesignDataSource.getDiamondSpecById(input.diamondSpecIds());
        for (var diamondSpec : diamondSpecifications) {
            CustomDesignDiamondSpecification customDesignDiamondSpecification = CustomDesignDiamondSpecification.builder()
                    .customDesign(customDesignCreated)
                    .diamondSpecification(diamondSpec)
                    .build();
            customDesignDiamondSpecifications.add(customDesignDiamondSpecification);
        }
        createCustomDesignDataSource.saveDiamondSpec(customDesignDiamondSpecifications);
        return aCreateCustomDesignMapper.toOutput(customDesignCreated, metalSpecifications, diamondSpecifications);
    }
}
