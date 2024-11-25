package com.cplerings.core.application.spouse.implementation;

import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.application.spouse.VerifyResidentIdUseCase;
import com.cplerings.core.application.spouse.datasource.VerifyResidentIdDataSource;
import com.cplerings.core.application.spouse.error.VerifyResidentIdErrorCode;
import com.cplerings.core.application.spouse.input.VerifyResidentIdInput;
import com.cplerings.core.application.spouse.mapper.AVerifyResidentIdMapper;
import com.cplerings.core.application.spouse.output.VerifyResidentIdOutput;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.spouse.Spouse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCaseImplementation
public class VerifyResidentIdUseCaseImpl extends AbstractUseCase<VerifyResidentIdInput, VerifyResidentIdOutput> implements VerifyResidentIdUseCase {

    private final VerifyResidentIdDataSource dataSource;
    private final AVerifyResidentIdMapper aVerifyResidentIdMapper;

    @Override
    protected void validateInput(UseCaseValidator validator, VerifyResidentIdInput input) {
        super.validateInput(validator, input);
        validator.validate(input.citizenId() != null, VerifyResidentIdErrorCode.CITIZEN_ID_REQUIRED);
        validator.validate(input.customerId() != null, VerifyResidentIdErrorCode.CUSTOMER_ID_REQUIRED);
        validator.clearAndThrowErrorCodes();
        validator.validateAndStopExecution(input.customerId() > 0, VerifyResidentIdErrorCode.WRONG_CUSTOMER_ID_INTEGER);
    }

    @Override
    protected VerifyResidentIdOutput internalExecute(UseCaseValidator validator, VerifyResidentIdInput input) {
        Spouse spouse = dataSource.getSpouseByCitizenId(input)
                .orElse(null);
        validator.validateAndStopExecution(spouse != null, VerifyResidentIdErrorCode.SPOUSE_NOT_FOUND_WITH_CITIZEN_ID);

        Account customer = dataSource.getCustomerWithSpouseById(input.customerId())
                .orElse(null);
        validator.validateAndStopExecution(customer != null, VerifyResidentIdErrorCode.CUSTOMER_NOT_FOUND);
        validator.validateAndStopExecution(customer.getSpouse() != null, VerifyResidentIdErrorCode.SPOUSE_ACCOUNT_NULL);
        validator.validateAndStopExecution(customer.getSpouse().getSpouse().getCitizenId().equalsIgnoreCase(input.citizenId()), VerifyResidentIdErrorCode.NOT_RIGHT_CUSTOMER);

        return aVerifyResidentIdMapper.toOutput(spouse);
    }
}
