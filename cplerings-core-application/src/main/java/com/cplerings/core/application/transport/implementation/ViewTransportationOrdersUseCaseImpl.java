package com.cplerings.core.application.transport.implementation;

import static com.cplerings.core.application.design.error.DesignErrorCode.INVALID_PAGE;
import static com.cplerings.core.application.design.error.DesignErrorCode.INVALID_PAGE_SIZE;

import com.cplerings.core.application.design.input.ViewCustomDesignsInput;
import com.cplerings.core.application.shared.entity.transport.ATransportationOrderStatus;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.application.transport.ViewTransportationOrdersUseCase;
import com.cplerings.core.application.transport.datasource.ViewTransportationOrdersDataSource;
import com.cplerings.core.application.transport.error.ViewTransportationOrdersErrorCode;
import com.cplerings.core.application.transport.input.ViewTransportationOrdersInput;
import com.cplerings.core.application.transport.mapper.AViewTransportationOrdersMapper;
import com.cplerings.core.application.transport.output.ViewTransportationOrdersOutput;
import com.cplerings.core.domain.order.CustomOrderStatus;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCaseImplementation
public class ViewTransportationOrdersUseCaseImpl extends AbstractUseCase<ViewTransportationOrdersInput, ViewTransportationOrdersOutput> implements ViewTransportationOrdersUseCase {

    private final ViewTransportationOrdersDataSource viewTransportationOrdersDataSource;
    private final AViewTransportationOrdersMapper viewTransportationOrdersMapper;

    @Override
    protected void validateInput(UseCaseValidator validator, ViewTransportationOrdersInput input) {
        super.validateInput(validator, input);
        validator.validate(input.getPage() >= 0, INVALID_PAGE);
        validator.validate(input.getPageSize() > 0, INVALID_PAGE_SIZE);
    }

    @Override
    protected ViewTransportationOrdersOutput internalExecute(UseCaseValidator validator, ViewTransportationOrdersInput input) {
        var result = viewTransportationOrdersDataSource.getTransportationOrders(input);
        if (input.getStatus() == ATransportationOrderStatus.PENDING) {
            result.getTransportationOrders().forEach(transportationOrder -> {
                validator.validateAndStopExecution(transportationOrder.getCustomOrder().getStatus() == CustomOrderStatus.DONE, ViewTransportationOrdersErrorCode.NOT_DONE_CUSTOM_ORDER);
            });
        }
        return viewTransportationOrdersMapper.toOutput(result);
    }
}
