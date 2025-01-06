package com.cplerings.core.application.dashboard.implementation;

import com.cplerings.core.application.dashboard.ViewTotalInForAllUseCase;
import com.cplerings.core.application.dashboard.datasource.ViewTotalInForAllDataSource;
import com.cplerings.core.application.dashboard.output.ViewTotalInForAllOutput;
import com.cplerings.core.application.shared.input.NoInput;
import com.cplerings.core.application.shared.service.security.SecurityService;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.domain.account.Account;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCaseImplementation
public class ViewTotalInForAllUseCaseImpl extends AbstractUseCase<NoInput, ViewTotalInForAllOutput> implements ViewTotalInForAllUseCase {

    private final ViewTotalInForAllDataSource dataSource;
    private final SecurityService securityService;

    @Override
    protected ViewTotalInForAllOutput internalExecute(UseCaseValidator validator, NoInput input) {
        var user = securityService.getCurrentUser();
        Account manager = dataSource.getAccountById(user.id());
        var result = dataSource.getTotalForAll(manager.getBranch().getId());
        return new ViewTotalInForAllOutput(result);
    }
}
