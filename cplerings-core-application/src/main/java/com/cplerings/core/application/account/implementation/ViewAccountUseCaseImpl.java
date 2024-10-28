package com.cplerings.core.application.account.implementation;

import static com.cplerings.core.application.account.error.AccountErrorCode.ACCOUNT_ID_REQUIRED;
import static com.cplerings.core.application.account.error.AccountErrorCode.ACCOUNT_WITH_ID_NOT_FOUND;
import static com.cplerings.core.application.account.error.AccountErrorCode.INVALID_ACCOUNT_ID;

import com.cplerings.core.application.account.ViewAccountUseCase;
import com.cplerings.core.application.account.datasource.ViewAccountDataSource;
import com.cplerings.core.application.account.input.ViewAccountInput;
import com.cplerings.core.application.account.mapper.AViewAccountMapper;
import com.cplerings.core.application.account.output.AccountOutput;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.account.Role;

import lombok.RequiredArgsConstructor;

@UseCaseImplementation
@RequiredArgsConstructor
public class ViewAccountUseCaseImpl extends AbstractUseCase<ViewAccountInput, AccountOutput>
        implements ViewAccountUseCase {

    private final ViewAccountDataSource viewAccountDataSource;
    private final AViewAccountMapper aViewAccountMapper;

    @Override
    protected void validateInput(UseCaseValidator validator, ViewAccountInput input) {
        super.validateInput(validator, input);
        validator.validate(input.id() != null, ACCOUNT_ID_REQUIRED);
        validator.validate(input.id() > 0, INVALID_ACCOUNT_ID);
    }

    @Override
    protected AccountOutput internalExecute(UseCaseValidator validator, ViewAccountInput input) {
        final Account account = viewAccountDataSource.getAccountById(input.id())
                .orElse(null);
        validator.validateAndStopExecution(account != null, ACCOUNT_WITH_ID_NOT_FOUND);
        final AccountOutput accountOutput = aViewAccountMapper.toOutput(account);
        if (account.getRole() == Role.CUSTOMER) {
            accountOutput.setHasSpouse(viewAccountDataSource.accountHasSpouse(account.getId()));
        } else {
            accountOutput.setHasSpouse(false);
        }
        return accountOutput;
    }
}
