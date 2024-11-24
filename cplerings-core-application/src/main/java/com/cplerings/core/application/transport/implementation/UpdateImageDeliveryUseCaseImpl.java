package com.cplerings.core.application.transport.implementation;

import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.application.transport.UpdateImageDeliveryUseCase;
import com.cplerings.core.application.transport.datasource.UpdateImageDeliveryDataSource;
import com.cplerings.core.application.transport.error.UpdateImageDeliveryErrorCode;
import com.cplerings.core.application.transport.input.UpdateImageDeliveryInput;
import com.cplerings.core.application.transport.mapper.AUpdateImageDeliveryMapper;
import com.cplerings.core.application.transport.output.UpdateImageDeliveryOutput;
import com.cplerings.core.domain.file.Image;
import com.cplerings.core.domain.order.TransportStatus;
import com.cplerings.core.domain.order.TransportationOrder;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCaseImplementation
public class UpdateImageDeliveryUseCaseImpl extends AbstractUseCase<UpdateImageDeliveryInput, UpdateImageDeliveryOutput> implements UpdateImageDeliveryUseCase {

    private final AUpdateImageDeliveryMapper aUpdateImageDeliveryMapper;
    private final UpdateImageDeliveryDataSource updateImageDeliveryDataSource;

    @Override
    protected void validateInput(UseCaseValidator validator, UpdateImageDeliveryInput input) {
        super.validateInput(validator, input);
        validator.validate(input.transportationOrderId() != null, UpdateImageDeliveryErrorCode.TRANSPORTATION_ORDER_ID_REQUIRED);
        validator.validate(input.imageId() != null, UpdateImageDeliveryErrorCode.IMAGE_ID_REQUIRED);
        validator.clearAndThrowErrorCodes();
        validator.validateAndStopExecution(input.transportationOrderId() > 0, UpdateImageDeliveryErrorCode.TRANSPORTATION_ORDER_WRONG_INTEGER);
        validator.validateAndStopExecution(input.imageId() > 0, UpdateImageDeliveryErrorCode.IMAGE_ID_WRONG_INTEGER);
    }


    @Override
    protected UpdateImageDeliveryOutput internalExecute(UseCaseValidator validator, UpdateImageDeliveryInput input) {
        TransportationOrder transportationOrder = updateImageDeliveryDataSource.getTransportationOrder(input.transportationOrderId())
                .orElse(null);
        validator.validateAndStopExecution(transportationOrder != null, UpdateImageDeliveryErrorCode.TRANSPORTATION_ORDER_NOT_FOUND);
        validator.validateAndStopExecution(transportationOrder.getStatus() == TransportStatus.DELIVERING, UpdateImageDeliveryErrorCode.WRONG_STATUS);
        Image image = updateImageDeliveryDataSource.getImageById(input.imageId())
                        .orElse(null);
        validator.validateAndStopExecution(image != null, UpdateImageDeliveryErrorCode.IMAGE_NOT_FOUND);
        transportationOrder.setImage(image);
        return aUpdateImageDeliveryMapper.toOutput(updateImageDeliveryDataSource.save(transportationOrder));
    }
}
