package com.cplerings.core.application.dashboard.implementation;

import com.cplerings.core.application.dashboard.ViewCustomOrdersWithDateUseCase;
import com.cplerings.core.application.dashboard.datasource.ViewCustomOrdersWithDateDataSource;
import com.cplerings.core.application.dashboard.input.ViewCustomOrdersWithDateInput;
import com.cplerings.core.application.dashboard.mapper.AViewCustomOrdersWithDateMapper;
import com.cplerings.core.application.dashboard.output.ViewCustomOrdersWithDateOutput;
import com.cplerings.core.application.shared.service.security.SecurityService;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.domain.account.Account;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCaseImplementation
public class ViewCustomOrdersWithDateUseCaseImpl extends AbstractUseCase<ViewCustomOrdersWithDateInput, ViewCustomOrdersWithDateOutput> implements ViewCustomOrdersWithDateUseCase {

    private final ViewCustomOrdersWithDateDataSource dataSource;
    private final SecurityService securityService;
    private final AViewCustomOrdersWithDateMapper aViewCustomOrdersWithDateMapper;

    @Override
    protected ViewCustomOrdersWithDateOutput internalExecute(UseCaseValidator validator, ViewCustomOrdersWithDateInput input) {
        var user = securityService.getCurrentUser();
        Account manager = dataSource.getAccountById(user.id());
        var result = dataSource.getCustomOrders(input, manager.getBranch().getId());
        return aViewCustomOrdersWithDateMapper.toOutput(result);
    }
}
