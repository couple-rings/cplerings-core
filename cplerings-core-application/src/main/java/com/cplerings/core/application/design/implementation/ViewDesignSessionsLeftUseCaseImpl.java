package com.cplerings.core.application.design.implementation;

import com.cplerings.core.application.design.ViewDesignSessionsLeftUseCase;
import com.cplerings.core.application.design.datasource.ViewDesignSessionsLeftDataSource;
import com.cplerings.core.application.design.error.ViewDesignSessionsLeftErrorCode;
import com.cplerings.core.application.design.input.ViewDesignSessionsLeftInput;
import com.cplerings.core.application.design.mapper.AViewDesignSessionsLeftMapper;
import com.cplerings.core.application.design.output.ViewDesignSessionsLeftOutput;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.domain.account.Account;

import lombok.RequiredArgsConstructor;

@UseCaseImplementation
@RequiredArgsConstructor
public class ViewDesignSessionsLeftUseCaseImpl extends AbstractUseCase<ViewDesignSessionsLeftInput, ViewDesignSessionsLeftOutput> implements ViewDesignSessionsLeftUseCase {

    private final AViewDesignSessionsLeftMapper aViewDesignSessionsLeftMapper;
    private final ViewDesignSessionsLeftDataSource viewDesignSessionsLeftDataSource;

    @Override
    protected void validateInput(UseCaseValidator validator, ViewDesignSessionsLeftInput input) {
        super.validateInput(validator, input);
        validator.validate(input.customerId() != null, ViewDesignSessionsLeftErrorCode.CUSTOMER_ID_REQUIRED);
        validator.validateAndStopExecution(input.customerId() > 0, ViewDesignSessionsLeftErrorCode.WRONG_CUSTOMER_ID_POSITIVE_INTEGER);
    }

    @Override
    protected ViewDesignSessionsLeftOutput internalExecute(UseCaseValidator validator, ViewDesignSessionsLeftInput input) {
        Account customer = viewDesignSessionsLeftDataSource.getCustomerById(input.customerId())
                .orElse(null);
        validator.validateAndStopExecution(customer != null, ViewDesignSessionsLeftErrorCode.INVALID_CUSTOMER_ID);
        int numOfSessionsLeft = viewDesignSessionsLeftDataSource.getRemainingDesignSessionsCount(input.customerId());
        return aViewDesignSessionsLeftMapper.toOutput(numOfSessionsLeft);
    }
}
