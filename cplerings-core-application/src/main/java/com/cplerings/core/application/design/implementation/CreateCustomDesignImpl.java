package com.cplerings.core.application.design.implementation;

import com.cplerings.core.application.design.CreateCustomDesignUseCase;
import com.cplerings.core.application.design.datasource.CreateCustomDesignDataSource;
import com.cplerings.core.application.design.error.DesignErrorCode;
import com.cplerings.core.application.design.input.CreateCustomDesignInput;
import com.cplerings.core.application.design.mapper.ACreateCustomDesignMapper;
import com.cplerings.core.application.design.output.CreateCustomDesignOutput;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.design.CustomDesign;
import com.cplerings.core.domain.design.DesignVersion;
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
        validator.validateAndStopExecution(input.blueprint() != null, DesignErrorCode.BLUEPRINT_REQUIRED);
        validator.validateAndStopExecution(input.metalWeight() != null, DesignErrorCode.METAL_WEIGHT_REQUIRED);
        validator.validateAndStopExecution(input.designVersionId() > 0, DesignErrorCode.DESIGN_VERSION_ID_WRONG_POSITIVE_NUMBER);
        validator.validateAndStopExecution(input.sideDiamondAmount() > 0, DesignErrorCode.SIDE_DIAMOND_AMOUNT_WRONG_POSITIVE_NUMBER);
        validator.validateAndStopExecution(input.customerId() > 0, DesignErrorCode.CUSTOMER_ID_WRONG_POSITIVE_NUMBER);
        validator.validateAndStopExecution(input.spouseId() > 0, DesignErrorCode.SPOUSE_ID_WRONG_POSITIVE_NUMBER);
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
                .build();
        CustomDesign customDesignCreated = createCustomDesignDataSource.save(customDesign);
        return aCreateCustomDesignMapper.toOutput(customDesignCreated);
    }
}
