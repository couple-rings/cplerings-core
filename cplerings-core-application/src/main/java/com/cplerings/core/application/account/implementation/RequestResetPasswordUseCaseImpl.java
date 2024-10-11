package com.cplerings.core.application.account.implementation;

import static com.cplerings.core.application.account.error.AccountErrorCode.ACCOUNT_WITH_EMAIL_NOT_FOUND;
import static com.cplerings.core.application.account.error.AccountErrorCode.EMAIL_REQUIRED;

import com.cplerings.core.application.account.RequestResetPasswordUseCase;
import com.cplerings.core.application.account.datasource.RequestResetPasswordDataSource;
import com.cplerings.core.application.account.input.RequestResetPasswordInput;
import com.cplerings.core.application.account.output.RequestResetPasswordOutput;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.account.AccountStatus;

import lombok.RequiredArgsConstructor;

import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

@UseCaseImplementation
@RequiredArgsConstructor
public class RequestResetPasswordUseCaseImpl extends AbstractUseCase<RequestResetPasswordInput, RequestResetPasswordOutput>
        implements RequestResetPasswordUseCase {

    private final RequestResetPasswordDataSource requestResetPasswordDataSource;

    @Override
    protected void validateInput(UseCaseValidator validator, RequestResetPasswordInput input) {
        super.validateInput(validator, input);
        validator.validate(StringUtils.isNotBlank(input.email()), EMAIL_REQUIRED);
    }

    @Override
    protected RequestResetPasswordOutput internalExecute(UseCaseValidator validator, RequestResetPasswordInput input) {
        final Optional<Account> accountOptional = requestResetPasswordDataSource.findByEmail(input.email());
        validator.validateAndStopExecution(accountOptional.isPresent(), ACCOUNT_WITH_EMAIL_NOT_FOUND);
        final Account account = accountOptional.get();
        validator.validateAndStopExecution(account.getStatus() != AccountStatus.ACTIVE, );
        return null;
    }
}
