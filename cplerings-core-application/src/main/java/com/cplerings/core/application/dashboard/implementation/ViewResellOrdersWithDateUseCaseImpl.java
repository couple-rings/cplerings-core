package com.cplerings.core.application.dashboard.implementation;

import com.cplerings.core.application.dashboard.ViewResellOrdersWithDateUseCase;
import com.cplerings.core.application.dashboard.datasource.ViewResellOrdersWithDateDataSource;
import com.cplerings.core.application.dashboard.input.ViewResellOrdersWithDateInput;
import com.cplerings.core.application.dashboard.mapper.AViewResellOrdersWithDateMapper;
import com.cplerings.core.application.dashboard.output.ViewResellOrdersWithDateOutput;
import com.cplerings.core.application.shared.service.security.SecurityService;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.domain.account.Account;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCaseImplementation
public class ViewResellOrdersWithDateUseCaseImpl extends AbstractUseCase<ViewResellOrdersWithDateInput, ViewResellOrdersWithDateOutput> implements ViewResellOrdersWithDateUseCase {

    private final ViewResellOrdersWithDateDataSource dataSource;
    private final SecurityService securityService;
    private final AViewResellOrdersWithDateMapper aViewResellOrdersWithDateMapper;

    @Override
    protected ViewResellOrdersWithDateOutput internalExecute(UseCaseValidator validator, ViewResellOrdersWithDateInput input) {
        var user = securityService.getCurrentUser();
        Account manager = dataSource.getAccountById(user.id());
        var result = dataSource.geResellOrders(input, manager.getBranch().getId());
        return aViewResellOrdersWithDateMapper.toOutput(result);
    }
}
