package com.cplerings.core.application.transport.implementation;

import static com.cplerings.core.application.design.error.DesignErrorCode.INVALID_PAGE;
import static com.cplerings.core.application.design.error.DesignErrorCode.INVALID_PAGE_SIZE;

import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.application.transport.ViewTransportationNotesUseCase;
import com.cplerings.core.application.transport.datasource.ViewTransportationNotesDataSource;
import com.cplerings.core.application.transport.input.ViewTransportationNotesInput;
import com.cplerings.core.application.transport.mapper.AViewTransportationNotesMapper;
import com.cplerings.core.application.transport.output.ViewTransportationNotesOutput;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCaseImplementation
public class ViewTransportationNotesUseCaseImpl extends AbstractUseCase<ViewTransportationNotesInput, ViewTransportationNotesOutput> implements ViewTransportationNotesUseCase {

    private final ViewTransportationNotesDataSource viewTransportationNotesDataSource;
    private final AViewTransportationNotesMapper aViewTransportationNotesMapper;

    @Override
    protected void validateInput(UseCaseValidator validator, ViewTransportationNotesInput input) {
        super.validateInput(validator, input);
        validator.validate(input.getPage() >= 0, INVALID_PAGE);
        validator.validate(input.getPageSize() > 0, INVALID_PAGE_SIZE);
    }

    @Override
    protected ViewTransportationNotesOutput internalExecute(UseCaseValidator validator, ViewTransportationNotesInput input) {
        var result = viewTransportationNotesDataSource.getTransportationNotes(input);
        return aViewTransportationNotesMapper.toOutput(result);
    }
}
