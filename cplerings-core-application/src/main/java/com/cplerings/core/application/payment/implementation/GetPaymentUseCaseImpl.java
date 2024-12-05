package com.cplerings.core.application.payment.implementation;

import com.cplerings.core.application.payment.GetPaymentUseCase;
import com.cplerings.core.application.payment.datasource.GetPaymentDataSource;
import com.cplerings.core.application.payment.error.GetPaymentErrorCode;
import com.cplerings.core.application.payment.input.GetPaymentInput;
import com.cplerings.core.application.payment.mapper.AGetPaymentMapper;
import com.cplerings.core.application.payment.output.GetPaymentOutput;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.domain.payment.Payment;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCaseImplementation
public class GetPaymentUseCaseImpl extends AbstractUseCase<GetPaymentInput, GetPaymentOutput> implements GetPaymentUseCase {

    private final GetPaymentDataSource dataSource;
    private final AGetPaymentMapper aGetPaymentMapper;

    @Override
    protected void validateInput(UseCaseValidator validator, GetPaymentInput input) {
        super.validateInput(validator, input);
        validator.validate(input.paymentId() != null, GetPaymentErrorCode.PAYMENT_ID_REQUIRED);
        validator.clearAndThrowErrorCodes();
        validator.validateAndStopExecution(input.paymentId() > 0, GetPaymentErrorCode.PAYMENT_ID_WRONG_INTEGER);
    }

    @Override
    protected GetPaymentOutput internalExecute(UseCaseValidator validator, GetPaymentInput input) {
        Payment payment = dataSource.getPaymentById(input.paymentId())
                .orElse(null);
        validator.validateAndStopExecution(payment != null, GetPaymentErrorCode.PAYMENT_NOT_FOUND);
        return aGetPaymentMapper.toOutput(payment);
    }
}
