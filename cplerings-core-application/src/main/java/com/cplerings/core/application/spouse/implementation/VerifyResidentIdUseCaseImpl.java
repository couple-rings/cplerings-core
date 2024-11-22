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
        validator.validateAndStopExecution(input.citizenId() != null, VerifyResidentIdErrorCode.CITIZEN_ID_REQUIRED);
    }

    @Override
    protected VerifyResidentIdOutput internalExecute(UseCaseValidator validator, VerifyResidentIdInput input) {
        Spouse spouse = dataSource.getSpouseByCitizenId(input)
                .orElse(null);

        validator.validateAndStopExecution(spouse != null, VerifyResidentIdErrorCode.SPOUSE_NOT_FOUND_WITH_CITIZEN_ID);
        return aVerifyResidentIdMapper.toOutput(spouse);
    }
}
