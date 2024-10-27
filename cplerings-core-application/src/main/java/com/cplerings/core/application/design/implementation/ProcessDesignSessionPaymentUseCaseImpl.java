package com.cplerings.core.application.design.implementation;

import static com.cplerings.core.application.design.error.DesignErrorCode.ACCOUNT_NOT_ACTIVE;
import static com.cplerings.core.application.design.error.DesignErrorCode.ACCOUNT_WITH_ID_NOT_FOUND;
import static com.cplerings.core.application.design.error.DesignErrorCode.INCORRECT_PAYMENT_RECEIVER;
import static com.cplerings.core.application.design.error.DesignErrorCode.INVALID_ACCOUNT_ID;
import static com.cplerings.core.application.design.error.DesignErrorCode.PAYMENT_RECEIVER_REQUIRED;

import com.cplerings.core.application.design.ProcessDesignSessionPaymentUseCase;
import com.cplerings.core.application.design.datasource.ProcessDesignSessionPaymentDataSource;
import com.cplerings.core.application.payment.input.PaymentReceiverInput;
import com.cplerings.core.application.shared.output.NoOutput;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.account.AccountStatus;
import com.cplerings.core.domain.design.request.CustomRequest;
import com.cplerings.core.domain.design.request.CustomRequestStatus;
import com.cplerings.core.domain.design.session.DesignSession;
import com.cplerings.core.domain.design.session.DesignSessionStatus;
import com.cplerings.core.domain.payment.PaymentReceiverType;

import lombok.RequiredArgsConstructor;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

@UseCaseImplementation
@RequiredArgsConstructor
public class ProcessDesignSessionPaymentUseCaseImpl extends AbstractUseCase<PaymentReceiverInput, NoOutput>
        implements ProcessDesignSessionPaymentUseCase {

    private static final int MAX_DESIGN_SESSIONS = 3;

    private final ProcessDesignSessionPaymentDataSource processDesignSessionPaymentDataSource;

    @Override
    protected void validateInput(UseCaseValidator validator, PaymentReceiverInput input) {
        super.validateInput(validator, input);
        validator.validate(input.paymentReceiver() != null, PAYMENT_RECEIVER_REQUIRED);
        validator.validate(input.paymentReceiver().getReceiverType() == PaymentReceiverType.DESIGN_FEE, INCORRECT_PAYMENT_RECEIVER);
        validator.validate(StringUtils.isNotBlank(input.paymentReceiver().getReceiverId()), INCORRECT_PAYMENT_RECEIVER);
    }

    @Override
    protected NoOutput internalExecute(UseCaseValidator validator, PaymentReceiverInput input) {
        long accountId = -1;
        try {
            accountId = Long.parseLong(input.paymentReceiver().getReceiverId());
        } catch (NumberFormatException e) {
            validator.validateAndStopExecution(false, INVALID_ACCOUNT_ID);
        }
        Account account = processDesignSessionPaymentDataSource.getAccountById(accountId)
                .orElse(null);
        validator.validateAndStopExecution(account != null, ACCOUNT_WITH_ID_NOT_FOUND);
        validator.validateAndStopExecution(account.getStatus() == AccountStatus.ACTIVE, ACCOUNT_NOT_ACTIVE);
        final UUID designSessionId = UUID.randomUUID();
        final Collection<DesignSession> designSessions = new ArrayList<>();
        for (int i = 0; i < MAX_DESIGN_SESSIONS; i++) {
            designSessions.add(DesignSession.builder()
                    .sessionId(designSessionId)
                    .customer(account)
                    .status(DesignSessionStatus.UNUSED)
                    .build());
        }
        processDesignSessionPaymentDataSource.saveAll(designSessions);
        final CustomRequest customRequest = CustomRequest.builder()
                .status(CustomRequestStatus.PENDING)
                .customer(account)
                .build();
        processDesignSessionPaymentDataSource.save(customRequest);
        return NoOutput.INSTANCE;
    }

}
