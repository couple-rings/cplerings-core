package com.cplerings.core.application.account.implementation;

import static com.cplerings.core.application.account.error.GetRandomStaffErrorCode.STAFF_NOT_FOUND;

import com.cplerings.core.application.account.GetRandomStaffUseCase;
import com.cplerings.core.application.account.datasource.GetRandomStaffDataSource;
import com.cplerings.core.application.account.output.GetRandomStaffOutput;
import com.cplerings.core.application.shared.input.NoInput;
import com.cplerings.core.application.shared.mapper.AAccountMapper;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.domain.account.Account;

import lombok.RequiredArgsConstructor;

@UseCaseImplementation
@RequiredArgsConstructor
public class GetRandomStaffUseCaseImpl extends AbstractUseCase<NoInput, GetRandomStaffOutput>
        implements GetRandomStaffUseCase {

    private final GetRandomStaffDataSource dataSource;
    private final AAccountMapper mapper;

    @Override
    protected GetRandomStaffOutput internalExecute(UseCaseValidator validator, NoInput input) {
        final Account staff = dataSource.getRandomStaff()
                .orElse(null);
        validator.validateAndStopExecution(staff != null, STAFF_NOT_FOUND);
        return GetRandomStaffOutput.builder()
                .staff(mapper.toAccount(staff))
                .build();
    }
}
