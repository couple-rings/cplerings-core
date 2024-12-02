package com.cplerings.core.application.design.implementation;

import static com.cplerings.core.application.design.error.ProcessDesignSessionPaymentErrorCode.ACCOUNT_NOT_ACTIVE;
import static com.cplerings.core.application.design.error.ProcessDesignSessionPaymentErrorCode.DESIGN_SESSION_PAYMENT_NOT_FOUND;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import com.cplerings.core.application.design.ProcessDesignSessionPaymentUseCase;
import com.cplerings.core.application.design.datasource.ProcessDesignSessionPaymentDataSource;
import com.cplerings.core.application.payment.error.PaymentErrorCode;
import com.cplerings.core.application.payment.input.PaymentSuccessfulResultInput;
import com.cplerings.core.application.shared.output.NoOutput;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.domain.account.AccountStatus;
import com.cplerings.core.domain.design.session.DesignSession;
import com.cplerings.core.domain.design.session.DesignSessionStatus;
import com.cplerings.core.domain.payment.DesignSessionPayment;

import lombok.RequiredArgsConstructor;

@UseCaseImplementation
@RequiredArgsConstructor
public class ProcessDesignSessionPaymentUseCaseImpl extends AbstractUseCase<PaymentSuccessfulResultInput, NoOutput>
        implements ProcessDesignSessionPaymentUseCase {

    private static final int MAX_DESIGN_SESSIONS = 3;

    private final ProcessDesignSessionPaymentDataSource dataSource;

    @Override
    protected void validateInput(UseCaseValidator validator, PaymentSuccessfulResultInput input) {
        super.validateInput(validator, input);
        validator.validate(input.payment() != null, PaymentErrorCode.PAYMENT_REQUIRED);
    }

    @Override
    protected NoOutput internalExecute(UseCaseValidator validator, PaymentSuccessfulResultInput input) {
        final DesignSessionPayment designSessionPayment = input.payment().getDesignSessionPayment();
        validator.validateAndStopExecution(designSessionPayment != null, DESIGN_SESSION_PAYMENT_NOT_FOUND);
        validator.validateAndStopExecution(designSessionPayment.getCustomer().getStatus() == AccountStatus.ACTIVE, ACCOUNT_NOT_ACTIVE);
        final Collection<DesignSession> designSessions = new ArrayList<>();
        for (int i = 0; i < MAX_DESIGN_SESSIONS; i++) {
            designSessions.add(DesignSession.builder()
                    .sessionId(designSessionPayment.getDesignSessionId())
                    .customer(designSessionPayment.getCustomer())
                    .status(DesignSessionStatus.UNUSED)
                    .build());
        }
        dataSource.saveAll(designSessions);
        return NoOutput.INSTANCE;
    }
}
