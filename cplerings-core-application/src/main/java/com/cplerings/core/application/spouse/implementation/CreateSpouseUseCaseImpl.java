package com.cplerings.core.application.spouse.implementation;

import java.util.UUID;

import com.cplerings.core.application.shared.output.NoOutput;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.application.spouse.CreateSpouseUseCase;
import com.cplerings.core.application.spouse.datasource.CreateSpouseDataSource;
import com.cplerings.core.application.spouse.error.SpouseErrorCode;
import com.cplerings.core.application.spouse.input.CreateSpouseInput;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.spouse.Spouse;
import com.cplerings.core.domain.spouse.SpouseAccount;

import lombok.RequiredArgsConstructor;

@UseCaseImplementation
@RequiredArgsConstructor
public class CreateSpouseUseCaseImpl extends AbstractUseCase<CreateSpouseInput, NoOutput> implements CreateSpouseUseCase {

    private final CreateSpouseDataSource spouseDataSource;

    @Override
    protected void validateInput(UseCaseValidator validator, CreateSpouseInput input) {
        super.validateInput(validator, input);
        validator.validateAndStopExecution(input.primarySpouse() != null, SpouseErrorCode.PRIMARY_SPOUSE_REQUIRED);
        validator.validateAndStopExecution(input.secondarySpouse() != null, SpouseErrorCode.SECONDARY_SPOUSE_REQUIRED);
    }

    @Override
    protected NoOutput internalExecute(UseCaseValidator validator, CreateSpouseInput input) {
        // Check valid account (will change message and error code when get the code in view profile)
        final Account customer = spouseDataSource.getAccountById(input.primarySpouse().customerId())
                .orElse(null);
        validator.validateAndStopExecution(customer != null, SpouseErrorCode.WILL_BE_REPLACED);

        // check citizenId of primary spouse
        final Spouse primarySpouse = spouseDataSource.getSpouseByCitizenId(input.primarySpouse().citizenId())
                .orElse(null);
        validator.validateAndStopExecution(primarySpouse == null, SpouseErrorCode.SPOUSE_HAS_BEEN_CREATED);

        // check citizenId of secondary spouse
        final Spouse secondarySpouse = spouseDataSource.getSpouseByCitizenId(input.primarySpouse().citizenId())
                .orElse(null);
        validator.validateAndStopExecution(secondarySpouse == null, SpouseErrorCode.SPOUSE_HAS_BEEN_CREATED);

        UUID coupleId = UUID.randomUUID();
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
                .dateOfBirth(input.primarySpouse().dateOfBirth())
                .fullName(input.primarySpouse().fullName())
                .coupleId(coupleId)
                .build();
        spouseDataSource.save(secondarySpouseCreate);
        return NoOutput.INSTANCE;
    }
}
