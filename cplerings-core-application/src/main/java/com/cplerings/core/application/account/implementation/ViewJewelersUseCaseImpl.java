package com.cplerings.core.application.account.implementation;

import com.cplerings.core.application.account.ViewJewelersUseCase;
import com.cplerings.core.application.account.datasource.ViewJewelersUseDataSource;
import com.cplerings.core.application.account.error.ViewTransportersErrorCode;
import com.cplerings.core.application.account.input.ViewJewelersInput;
import com.cplerings.core.application.account.mapper.AViewJewelersMapper;
import com.cplerings.core.application.account.output.ViewJewelersOutput;
import com.cplerings.core.application.account.output.ViewTransportersOutput;
import com.cplerings.core.application.account.output.result.DesignStaffsOutputResult;
import com.cplerings.core.application.account.output.result.JewelersOutputResult;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCaseImplementation
public class ViewJewelersUseCaseImpl extends AbstractUseCase<ViewJewelersInput, ViewJewelersOutput> implements ViewJewelersUseCase {

    private final ViewJewelersUseDataSource viewJewelersUseDataSource;
    private final AViewJewelersMapper aViewJewelersMapper;

    @Override
    protected void validateInput(UseCaseValidator validator, ViewJewelersInput input) {
        super.validateInput(validator, input);
        validator.validateAndStopExecution(input.getBranchId() != null, ViewTransportersErrorCode.BRANCH_ID_REQUIRED);
        validator.validateAndStopExecution(input.getBranchId() > 0, ViewTransportersErrorCode.BRANCH_ID_WRONG_POSITIVE_INTEGER);
    }

    @Override
    protected ViewJewelersOutput internalExecute(UseCaseValidator validator, ViewJewelersInput input) {
        var result = viewJewelersUseDataSource.getJewelers(input);
        JewelersOutputResult jewelersOutputResult = aViewJewelersMapper.toJewelers(result);
        jewelersOutputResult.getJewelers().forEach(x -> {
            x.setNumberOfHandleCustomOrder(viewJewelersUseDataSource.calculateNoOfHandleCustomOrder(x));
        });
        return aViewJewelersMapper.toOutput(jewelersOutputResult);
    }
}
