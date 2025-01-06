package com.cplerings.core.application.dashboard.implementation;

import com.cplerings.core.application.dashboard.ViewTotalInUseCase;
import com.cplerings.core.application.dashboard.datasource.ViewTotalInDataSource;
import com.cplerings.core.application.dashboard.input.ViewTotalInInput;
import com.cplerings.core.application.dashboard.output.ViewTotalInOutput;
import com.cplerings.core.application.shared.service.security.SecurityService;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.domain.account.Account;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCaseImplementation
public class ViewTotalInUseCaseImpl extends AbstractUseCase<ViewTotalInInput, ViewTotalInOutput> implements ViewTotalInUseCase {

    private final ViewTotalInDataSource dataSource;
    private final SecurityService securityService;

    @Override
    protected ViewTotalInOutput internalExecute(UseCaseValidator validator, ViewTotalInInput input) {
        var user = securityService.getCurrentUser();
        Account manager = dataSource.getAccountById(user.id());
        var result = dataSource.getTotal(input.startDate(), input.endDate(), manager.getBranch().getId());
        return new ViewTotalInOutput(result);
    }
}
