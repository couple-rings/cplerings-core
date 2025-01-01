package com.cplerings.core.application.order.implementation;

import static com.cplerings.core.application.order.error.ViewCustomOrderPaymentsErrorCode.CUSTOM_ORDER_ID_REQUIRED;
import static com.cplerings.core.application.order.error.ViewCustomOrderPaymentsErrorCode.CUSTOM_ORDER_NOT_FOUND;
import static com.cplerings.core.application.order.error.ViewCustomOrderPaymentsErrorCode.INVALID_CUSTOM_ORDER_ID;

import com.cplerings.core.application.order.ViewCustomOrderPaymentsUseCase;
import com.cplerings.core.application.order.datasource.ViewCustomOrderPaymentsDataSource;
import com.cplerings.core.application.order.input.ViewCustomOrderPaymentsInput;
import com.cplerings.core.application.order.output.ViewCustomOrderPaymentsOutput;
import com.cplerings.core.application.shared.mapper.APaymentMapper;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.common.number.NumberUtils;
import com.cplerings.core.domain.payment.Payment;

import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCaseImplementation
@RequiredArgsConstructor
public class ViewCustomOrderPaymentsUseCaseImpl extends AbstractUseCase<ViewCustomOrderPaymentsInput, ViewCustomOrderPaymentsOutput>
        implements ViewCustomOrderPaymentsUseCase {

    private final ViewCustomOrderPaymentsDataSource dataSource;
    private final APaymentMapper paymentMapper;

    @Override
    protected void validateInput(UseCaseValidator validator, ViewCustomOrderPaymentsInput input) {
        super.validateInput(validator, input);
        validator.validate(input.customOrderId() != null, CUSTOM_ORDER_ID_REQUIRED);
        validator.clearAndThrowErrorCodes();

        validator.validate(NumberUtils.isPositive(input.customOrderId()), INVALID_CUSTOM_ORDER_ID);
    }

    @Override
    protected ViewCustomOrderPaymentsOutput internalExecute(UseCaseValidator validator, ViewCustomOrderPaymentsInput input) {
        if (dataSource.customOrderDoesNotExist(input.customOrderId())) {
            validator.validateAndStopExecution(false, CUSTOM_ORDER_NOT_FOUND);
        }

        final List<Payment> payments = dataSource.getPaymentsOfCustomOrder(input.customOrderId());
        return ViewCustomOrderPaymentsOutput.builder()
                .payments(payments.stream()
                        .map(paymentMapper::toPaymentInfo)
                        .toList())
                .build();
    }
}
