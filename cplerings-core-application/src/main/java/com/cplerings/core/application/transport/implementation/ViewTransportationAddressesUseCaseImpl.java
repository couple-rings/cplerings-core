package com.cplerings.core.application.transport.implementation;

import static com.cplerings.core.application.design.error.DesignErrorCode.INVALID_PAGE;
import static com.cplerings.core.application.design.error.DesignErrorCode.INVALID_PAGE_SIZE;

import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.application.transport.ViewTransportationAddressesUseCase;
import com.cplerings.core.application.transport.datasource.ViewTransportationAddressesDataSource;
import com.cplerings.core.application.transport.input.ViewTransportationAddressesInput;
import com.cplerings.core.application.transport.mapper.AViewTransportationAddressesMapper;
import com.cplerings.core.application.transport.output.ViewTransportationAddressesOutput;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCaseImplementation
public class ViewTransportationAddressesUseCaseImpl extends AbstractUseCase<ViewTransportationAddressesInput, ViewTransportationAddressesOutput> implements ViewTransportationAddressesUseCase {

    private final AViewTransportationAddressesMapper aViewTransportationAddressesMapper;
    private final ViewTransportationAddressesDataSource dataSource;

    @Override
    protected void validateInput(UseCaseValidator validator, ViewTransportationAddressesInput input) {
        super.validateInput(validator, input);
        validator.validate(input.getPage() >= 0, INVALID_PAGE);
        validator.validate(input.getPageSize() > 0, INVALID_PAGE_SIZE);
    }

    @Override
    protected ViewTransportationAddressesOutput internalExecute(UseCaseValidator validator, ViewTransportationAddressesInput input) {
        var result = dataSource.getTransportationAddresses(input);
        return aViewTransportationAddressesMapper.toOutput(result);
    }
}
