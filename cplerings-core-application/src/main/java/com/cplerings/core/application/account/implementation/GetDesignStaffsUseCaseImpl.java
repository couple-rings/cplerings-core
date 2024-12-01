package com.cplerings.core.application.account.implementation;

import com.cplerings.core.application.account.GetDesignStaffsUseCase;
import com.cplerings.core.application.account.datasource.GetDesignStaffsDataSource;
import com.cplerings.core.application.account.error.ViewTransportersErrorCode;
import com.cplerings.core.application.account.input.GetDesignStaffsInput;
import com.cplerings.core.application.account.mapper.AGetDesignStaffsMapper;
import com.cplerings.core.application.account.output.GetDesignStaffsOutput;
import com.cplerings.core.application.account.output.result.DesignStaffsOutputResult;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCaseImplementation
public class GetDesignStaffsUseCaseImpl extends AbstractUseCase<GetDesignStaffsInput, GetDesignStaffsOutput> implements GetDesignStaffsUseCase {

    private final GetDesignStaffsDataSource getDesignStaffsDataSource;
    private final AGetDesignStaffsMapper aGetDesignStaffsMapper;

    @Override
    protected void validateInput(UseCaseValidator validator, GetDesignStaffsInput input) {
        super.validateInput(validator, input);
        validator.validateAndStopExecution(input.getBranchId() != null, ViewTransportersErrorCode.BRANCH_ID_REQUIRED);
        validator.validateAndStopExecution(input.getBranchId() > 0, ViewTransportersErrorCode.BRANCH_ID_WRONG_POSITIVE_INTEGER);
    }

    @Override
    protected GetDesignStaffsOutput internalExecute(UseCaseValidator validator, GetDesignStaffsInput input) {
        var result = getDesignStaffsDataSource.getDesignStaffs(input);
        DesignStaffsOutputResult designStaffsOutputResult = aGetDesignStaffsMapper.toDesignStaff(result);
        designStaffsOutputResult.getStaffs().forEach(x -> {
            x.setNumberOfHandledCustomRequest(getDesignStaffsDataSource.calculateNoOfHandleCustomRequest(x));
        });
        return aGetDesignStaffsMapper.toOutput(designStaffsOutputResult);
    }
}
