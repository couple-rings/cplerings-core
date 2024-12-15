package com.cplerings.core.application.account.implementation;

import com.cplerings.core.application.account.ViewCustomersUseCase;
import com.cplerings.core.application.account.datasource.ViewCustomersDataSource;
import com.cplerings.core.application.account.input.ViewCustomersInput;
import com.cplerings.core.application.account.mapper.AViewCustomersMapper;
import com.cplerings.core.application.account.output.ViewCustomersOutput;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCaseImplementation
public class ViewCustomersUseCaseImpl extends AbstractUseCase<ViewCustomersInput, ViewCustomersOutput> implements ViewCustomersUseCase {

    private final ViewCustomersDataSource viewCustomersDataSource;
    private final AViewCustomersMapper aViewCustomersMapper;

    @Override
    protected ViewCustomersOutput internalExecute(UseCaseValidator validator, ViewCustomersInput input) {
        var customers = viewCustomersDataSource.getCustomers(input);
        return aViewCustomersMapper.toOutput(customers);
    }
}
