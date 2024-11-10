package com.cplerings.core.application.design.implementation;

import static com.cplerings.core.application.design.error.DesignErrorCode.INVALID_PAGE;
import static com.cplerings.core.application.design.error.DesignErrorCode.INVALID_PAGE_SIZE;

import com.cplerings.core.application.design.ViewCustomRequestsUseCase;
import com.cplerings.core.application.design.datasource.ViewCustomRequestsDataSource;
import com.cplerings.core.application.design.error.ViewCustomRequestsErrorCode;
import com.cplerings.core.application.design.input.ViewCustomRequestsInput;
import com.cplerings.core.application.design.mapper.AViewCustomRequestsMapper;
import com.cplerings.core.application.design.output.ViewCustomRequestsOutput;
import com.cplerings.core.application.shared.entity.design.request.ACustomRequestStatus;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.domain.account.Account;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCaseImplementation
public class ViewCustomRequestsUseCaseImpl extends AbstractUseCase<ViewCustomRequestsInput, ViewCustomRequestsOutput> implements ViewCustomRequestsUseCase {

    private final ViewCustomRequestsDataSource viewCustomRequestsDataSource;
    private final AViewCustomRequestsMapper aViewCustomRequestsMapper;

    @Override
    protected void validateInput(UseCaseValidator validator, ViewCustomRequestsInput input) {
        super.validateInput(validator, input);
        validator.validate(input.getPage() >= 0, INVALID_PAGE);
        validator.validate(input.getPageSize() > 0, INVALID_PAGE_SIZE);

        if (input.getStatus() != null) {
            validator.validateAndStopExecution(input.getStatus() == ACustomRequestStatus.PENDING || input.getStatus() == ACustomRequestStatus.APPROVED || input.getStatus() == ACustomRequestStatus.REJECTED, ViewCustomRequestsErrorCode.INVALID_STATUS);
        }

        if (input.getCustomerId() != null) {
            validator.validateAndStopExecution(input.getCustomerId() > 0, ViewCustomRequestsErrorCode.CUSTOMER_ID_WRONG_POSITIVE_INTEGER);
        }
    }

    @Override
    protected ViewCustomRequestsOutput internalExecute(UseCaseValidator validator, ViewCustomRequestsInput input) {
        if (input.getCustomerId() != null) {
            Account customer = viewCustomRequestsDataSource.getCustomerById(input.getCustomerId())
                    .orElse(null);
            validator.validate(customer != null, ViewCustomRequestsErrorCode.INVALID_CUSTOMER_ID);
        }

        var result = viewCustomRequestsDataSource.getCustomRequests(input);
        return aViewCustomRequestsMapper.toOutput(result);
    }
}
