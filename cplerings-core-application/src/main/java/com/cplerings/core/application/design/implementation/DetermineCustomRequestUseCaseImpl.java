package com.cplerings.core.application.design.implementation;

import com.cplerings.core.application.design.DetermineCustomRequestUseCase;
import com.cplerings.core.application.design.datasource.DetermineCustomRequestDataSource;
import com.cplerings.core.application.design.error.DetermineCustomRequestErrorCode;
import com.cplerings.core.application.design.input.DetermineCustomRequestInput;
import com.cplerings.core.application.design.mapper.ADetermineCustomRequestMapper;
import com.cplerings.core.application.design.output.DetermineCustomRequestOutput;
import com.cplerings.core.application.shared.entity.design.request.ACustomRequestStatus;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.domain.design.request.CustomRequest;
import com.cplerings.core.domain.design.request.CustomRequestStatus;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCaseImplementation
public class DetermineCustomRequestUseCaseImpl extends AbstractUseCase<DetermineCustomRequestInput, DetermineCustomRequestOutput> implements DetermineCustomRequestUseCase {

    private final DetermineCustomRequestDataSource determineCustomRequestDataSource;
    private final ADetermineCustomRequestMapper aDetermineCustomRequestMapper;

    @Override
    protected void validateInput(UseCaseValidator validator, DetermineCustomRequestInput input) {
        super.validateInput(validator, input);
        validator.validateAndStopExecution(input.customRequestId() != null, DetermineCustomRequestErrorCode.CUSTOM_REQUEST_ID_REQUIRED);
        validator.validateAndStopExecution(input.customRequestId() > 0, DetermineCustomRequestErrorCode.CUSTOM_REQUEST_ID_WRONG_POSITIVE_INTEGER);
        validator.validateAndStopExecution(input.customRequestStatus() == ACustomRequestStatus.APPROVED || input.customRequestStatus() == ACustomRequestStatus.REJECTED, DetermineCustomRequestErrorCode.WRONG_STATUS);
        validator.validateAndStopExecution(input.comment() != null, DetermineCustomRequestErrorCode.COMMENT_REQUIRED);
    }

    @Override
    protected DetermineCustomRequestOutput internalExecute(UseCaseValidator validator, DetermineCustomRequestInput input) {
        CustomRequest customRequest = determineCustomRequestDataSource.getCraftingRequestById(input.customRequestId())
                .orElse(null);
        validator.validateAndStopExecution(customRequest != null, DetermineCustomRequestErrorCode.INVALID_CUSTOM_REQUEST_ID);
        validator.validateAndStopExecution(customRequest.getStatus() == CustomRequestStatus.PENDING, DetermineCustomRequestErrorCode.INVALID_CUSTOM_REQUEST_STATUS);
        customRequest.setComment(input.comment());
        if (input.customRequestStatus() == ACustomRequestStatus.APPROVED) {
            customRequest.setStatus(CustomRequestStatus.APPROVED);
        }

        if (input.customRequestStatus() == ACustomRequestStatus.REJECTED) {
            customRequest.setStatus(CustomRequestStatus.REJECTED);
        }
        CustomRequest customRequestUpdated = determineCustomRequestDataSource.updateCraftingRequest(customRequest);
        return aDetermineCustomRequestMapper.toOutput(customRequestUpdated);
    }
}
