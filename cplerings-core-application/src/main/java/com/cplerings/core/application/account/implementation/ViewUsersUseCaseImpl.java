package com.cplerings.core.application.account.implementation;

import com.cplerings.core.application.account.ViewUsersUseCase;
import com.cplerings.core.application.account.datasource.ViewUsersDataSource;
import com.cplerings.core.application.account.error.ViewUsersErrorCode;
import com.cplerings.core.application.account.input.ViewUsersInput;
import com.cplerings.core.application.account.mapper.AViewUsersMapper;
import com.cplerings.core.application.account.output.ViewUsersOutput;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCaseImplementation
public class ViewUsersUseCaseImpl extends AbstractUseCase<ViewUsersInput, ViewUsersOutput> implements ViewUsersUseCase {

    private final AViewUsersMapper aMapper;
    private final ViewUsersDataSource dataSource;

    @Override
    protected ViewUsersOutput internalExecute(UseCaseValidator validator, ViewUsersInput input) {
        var users = dataSource.getUsers(input.userIds());
        validator.validateAndStopExecution(users.users().size() == input.userIds().size(), ViewUsersErrorCode.NOT_FOUND_SOME_USERS);
        return aMapper.toOutput(users);
    }
}
