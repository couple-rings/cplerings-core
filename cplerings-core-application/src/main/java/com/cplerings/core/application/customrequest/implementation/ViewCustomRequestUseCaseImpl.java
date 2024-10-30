package com.cplerings.core.application.customrequest.implementation;

import com.cplerings.core.application.customrequest.ViewCustomRequestUseCase;
import com.cplerings.core.application.customrequest.datasource.ViewCustomRequestDataSource;
import com.cplerings.core.application.customrequest.error.ViewCustomRequestErrorCode;
import com.cplerings.core.application.customrequest.input.ViewCustomRequestInput;
import com.cplerings.core.application.customrequest.mapper.AViewCustomRequestMapper;
import com.cplerings.core.application.customrequest.output.ViewCustomRequestOutput;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.domain.design.request.CustomRequest;

import lombok.RequiredArgsConstructor;

@UseCaseImplementation
@RequiredArgsConstructor
public class ViewCustomRequestUseCaseImpl extends AbstractUseCase<ViewCustomRequestInput, ViewCustomRequestOutput>
        implements ViewCustomRequestUseCase {

    private final ViewCustomRequestDataSource viewCustomRequestDataSource;
    private final AViewCustomRequestMapper aViewCustomRequestMapper;

    @Override
    protected void validateInput(UseCaseValidator validator, ViewCustomRequestInput input) {
        super.validateInput(validator, input);
        validator.validate(input.customRequestId() > 0, ViewCustomRequestErrorCode.WRONG_ID_POSITIVE_INTEGER);
    }

    @Override
    protected ViewCustomRequestOutput internalExecute(UseCaseValidator validator, ViewCustomRequestInput input) {
        CustomRequest customRequest = viewCustomRequestDataSource.getCustomRequestById(input.customRequestId())
                .orElse(null);
        validator.validateAndStopExecution(customRequest != null, ViewCustomRequestErrorCode.INVALID_CUSTOM_REQUEST_ID);
        return aViewCustomRequestMapper.toOutput(customRequest);
    }
}