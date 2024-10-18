package com.cplerings.core.application.spouse.implementation;

import static com.cplerings.core.application.spouse.error.SpouseErrorCode.CUSTOMER_ID_REQUIRED;
import static com.cplerings.core.application.spouse.error.SpouseErrorCode.DATE_OF_BIRTH_REQUIRED;
import static com.cplerings.core.application.spouse.error.SpouseErrorCode.FULL_NAME_REQUIRED;
import static com.cplerings.core.application.spouse.error.SpouseErrorCode.PRIMARY_SPOUSE_REQUIRED;
import static com.cplerings.core.application.spouse.error.SpouseErrorCode.SECONDARY_SPOUSE_REQUIRED;
import static com.cplerings.core.application.spouse.error.SpouseErrorCode.SPOUSE_CITIZEN_ID_REQUIRED;

import com.cplerings.core.application.shared.output.NoOutput;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.application.spouse.CreateSpouseUseCase;
import com.cplerings.core.application.spouse.datasource.CreateSpouseDataSource;
import com.cplerings.core.application.spouse.error.SpouseErrorCode;
import com.cplerings.core.application.spouse.input.CreateSpouseInput;
import com.cplerings.core.common.input.InputValidator;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.spouse.Spouse;
import com.cplerings.core.domain.spouse.SpouseAccount;

import lombok.RequiredArgsConstructor;

import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@UseCaseImplementation
@RequiredArgsConstructor
public class CreateSpouseUseCaseImpl extends AbstractUseCase<CreateSpouseInput, NoOutput> implements CreateSpouseUseCase {

    private final CreateSpouseDataSource spouseDataSource;

    @Override
    protected void validateInput(UseCaseValidator validator, CreateSpouseInput input) {
        super.validateInput(validator, input);
        validator.validateAndStopExecution(input.primarySpouse() != null, PRIMARY_SPOUSE_REQUIRED);
        validator.validateAndStopExecution(input.secondarySpouse() != null, SECONDARY_SPOUSE_REQUIRED);
        validator.validate(StringUtils.isNotBlank(input.primarySpouse().citizenId()), SPOUSE_CITIZEN_ID_REQUIRED);
        validator.validate(StringUtils.isNotBlank(input.secondarySpouse().citizenId()), SPOUSE_CITIZEN_ID_REQUIRED);
        validator.validate(InputValidator.numberIsPositive(input.primarySpouse().customerId()), CUSTOMER_ID_REQUIRED);
        validator.validate(input.primarySpouse().dateOfBirth() != null, DATE_OF_BIRTH_REQUIRED);
        validator.validate(input.secondarySpouse().dateOfBirth() != null, DATE_OF_BIRTH_REQUIRED);
        validator.validate(StringUtils.isNotBlank(input.primarySpouse().fullName()), FULL_NAME_REQUIRED);
        validator.validate(StringUtils.isNotBlank(input.secondarySpouse().fullName()), FULL_NAME_REQUIRED);
    }

    @Override
    protected NoOutput internalExecute(UseCaseValidator validator, CreateSpouseInput input) {
        // Check valid account (will change message and error code when get the code in view profile)
        final Account customer = spouseDataSource.getAccountById(input.primarySpouse().customerId())
                .orElse(null);
        validator.validateAndStopExecution(customer != null, SpouseErrorCode.ACCOUNT_NOT_FOUND_WITH_ID);

        final Collection<String> citizenIds = List.of(input.primarySpouse().citizenId(), input.secondarySpouse().citizenId());
        validator.validateAndStopExecution(spouseDataSource.doesNotExistSpouseByCitizenIdIn(citizenIds), SpouseErrorCode.SPOUSE_HAS_BEEN_CREATED);

        final UUID coupleId = UUID.randomUUID();
        Spouse primarySpouseCreate = Spouse.builder()
                .citizenId(input.primarySpouse().citizenId())
                .dateOfBirth(input.primarySpouse().dateOfBirth())
                .fullName(input.primarySpouse().fullName())
                .coupleId(coupleId)
                .build();
        Spouse primarySpouseCreated = spouseDataSource.save(primarySpouseCreate);
        SpouseAccount spouseAccount = SpouseAccount.builder()
                .spouse(primarySpouseCreated)
                .customer(customer)
                .build();
        spouseDataSource.save(spouseAccount);
        Spouse secondarySpouseCreate = Spouse.builder()
                .citizenId(input.secondarySpouse().citizenId())
                .dateOfBirth(input.secondarySpouse().dateOfBirth())
                .fullName(input.secondarySpouse().fullName())
                .coupleId(coupleId)
                .build();
        spouseDataSource.save(secondarySpouseCreate);
        return NoOutput.INSTANCE;
    }
}
