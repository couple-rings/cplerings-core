package com.cplerings.core.application.transport.implementation;

import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.application.transport.CreateTransportationNoteUseCase;
import com.cplerings.core.application.transport.datasource.CreateTransportationNoteDataSource;
import com.cplerings.core.application.transport.error.CreateTransportationNoteErrorCode;
import com.cplerings.core.application.transport.input.CreateTransportationNoteInput;
import com.cplerings.core.application.transport.mapper.ACreateTransportationNoteMapper;
import com.cplerings.core.application.transport.output.CreateTransportationNoteOutput;
import com.cplerings.core.common.temporal.TemporalUtils;
import com.cplerings.core.domain.order.TransportStatus;
import com.cplerings.core.domain.order.TransportationOrder;
import com.cplerings.core.domain.order.status.TransportationNote;

import lombok.RequiredArgsConstructor;

@UseCaseImplementation
@RequiredArgsConstructor
public class CreateTransportationNoteUseCaseImpl extends AbstractUseCase<CreateTransportationNoteInput, CreateTransportationNoteOutput> implements CreateTransportationNoteUseCase {

    private final CreateTransportationNoteDataSource createTransportationNoteDataSource;
    private final ACreateTransportationNoteMapper aCreateTransportationNoteMapper;

    @Override
    protected void validateInput(UseCaseValidator validator, CreateTransportationNoteInput input) {
        super.validateInput(validator, input);
        validator.validate(input.transportationOrderId() != null, CreateTransportationNoteErrorCode.TRANSPORTATION_ORDER_ID_REQUIRED);
        validator.validate(input.note() != null, CreateTransportationNoteErrorCode.NOTE_REQUIRED);
        validator.clearAndThrowErrorCodes();
        validator.validate(input.transportationOrderId() > 0, CreateTransportationNoteErrorCode.TRANSPORTATION_ORDER_ID_WRONG_INTEGER);
    }

    @Override
    protected CreateTransportationNoteOutput internalExecute(UseCaseValidator validator, CreateTransportationNoteInput input) {
        TransportationOrder transportationOrder = createTransportationNoteDataSource.getTransportationOrder(input.transportationOrderId())
                .orElse(null);
        validator.validateAndStopExecution(transportationOrder != null, CreateTransportationNoteErrorCode.TRANSPORTATION_ORDER_NOT_FOUND);
        validator.validateAndStopExecution(transportationOrder.getStatus() == TransportStatus.ON_GOING || transportationOrder.getStatus() == TransportStatus.DELIVERING, CreateTransportationNoteErrorCode.WRONG_STATUS_FOR_CREATE);
        TransportationNote transportationNote = TransportationNote.builder()
                .note(input.note())
                .transportationOrder(transportationOrder)
                .date(TemporalUtils.getCurrentInstantUTC())
                .build();
        TransportationNote transportationNoteCreated = createTransportationNoteDataSource.save(transportationNote);
        return aCreateTransportationNoteMapper.toOutput(transportationNoteCreated);
    }
}
