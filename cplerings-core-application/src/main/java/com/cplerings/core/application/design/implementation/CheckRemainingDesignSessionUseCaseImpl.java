package com.cplerings.core.application.design.implementation;

import static com.cplerings.core.application.design.error.DesignErrorCode.CUSTOMER_NOT_ACTIVE;
import static com.cplerings.core.application.design.error.DesignErrorCode.CUSTOMER_NOT_FOUND;

import com.cplerings.core.application.design.CheckRemainingDesignSessionUseCase;
import com.cplerings.core.application.design.datasource.CheckRemainingDesignSessionDataSource;
import com.cplerings.core.application.design.mapper.ACheckRemainingDesignSessionMapper;
import com.cplerings.core.application.design.output.CheckRemainingDesignSessionOutput;
import com.cplerings.core.application.shared.input.NoInput;
import com.cplerings.core.application.shared.service.security.SecurityService;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.account.AccountStatus;

import lombok.RequiredArgsConstructor;

@UseCaseImplementation
@RequiredArgsConstructor
public class CheckRemainingDesignSessionUseCaseImpl extends AbstractUseCase<NoInput, CheckRemainingDesignSessionOutput>
        implements CheckRemainingDesignSessionUseCase {

    private final CheckRemainingDesignSessionDataSource checkRemainingDesignSessionDataSource;
    private final SecurityService securityService;
    private final ACheckRemainingDesignSessionMapper aCheckRemainingDesignSessionMapper;

    @Override
    protected CheckRemainingDesignSessionOutput internalExecute(UseCaseValidator validator, NoInput input) {
        Account customer = checkRemainingDesignSessionDataSource.getAccountByEmail(securityService.getCurrentUser().email())
                .orElse(null);
        validator.validateAndStopExecution(customer != null, CUSTOMER_NOT_FOUND);
        validator.validateAndStopExecution(customer.getStatus() == AccountStatus.ACTIVE, CUSTOMER_NOT_ACTIVE);

        int remainingDesignSessionsCount = checkRemainingDesignSessionDataSource.getRemainingDesignSessionsCount(customer.getId());
        return aCheckRemainingDesignSessionMapper.toOutput(remainingDesignSessionsCount);
    }
}
