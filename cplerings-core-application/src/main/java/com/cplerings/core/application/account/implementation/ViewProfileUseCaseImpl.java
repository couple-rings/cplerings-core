package com.cplerings.core.application.account.implementation;

import org.apache.commons.lang3.StringUtils;

import com.cplerings.core.application.account.ViewProfileUseCase;
import com.cplerings.core.application.account.datasource.ViewProfileDataSource;
import com.cplerings.core.application.account.error.AccountErrorCode;
import com.cplerings.core.application.account.input.ViewProfileInput;
import com.cplerings.core.application.account.mapper.AViewProfileMapper;
import com.cplerings.core.application.account.output.AccountProfileOutput;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.domain.account.Account;

import lombok.RequiredArgsConstructor;

@UseCaseImplementation
@RequiredArgsConstructor
public class ViewProfileUseCaseImpl extends AbstractUseCase<ViewProfileInput, AccountProfileOutput>
        implements ViewProfileUseCase {

    private final ViewProfileDataSource viewProfileDataSource;
    private final AViewProfileMapper aViewProfileMapper;

    @Override
    protected AccountProfileOutput internalExecute(UseCaseValidator validator, ViewProfileInput input) {
        final Account account = viewProfileDataSource.getAccountById(input.id())
                .orElse(null);
        validator.validateAndStopExecution(account != null, AccountErrorCode.ACCOUNT_WITH_ID_NOT_FOUND);
        return aViewProfileMapper.toOutput(account);
    }
}
