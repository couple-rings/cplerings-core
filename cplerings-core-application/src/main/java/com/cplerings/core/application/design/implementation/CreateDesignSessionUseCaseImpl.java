package com.cplerings.core.application.design.implementation;

import static com.cplerings.core.application.design.error.DesignErrorCode.CUSTOMER_NOT_ACTIVE;
import static com.cplerings.core.application.design.error.DesignErrorCode.CUSTOMER_NOT_FOUND;
import static com.cplerings.core.application.design.error.DesignErrorCode.EXIST_UNUSED_DESIGN_SESSION;

import com.cplerings.core.application.design.CreateDesignSessionUseCase;
import com.cplerings.core.application.design.datasource.CreateDesignSessionDataSource;
import com.cplerings.core.application.design.mapper.ACreateDesignSessionMapper;
import com.cplerings.core.application.design.output.CreateDesignSessionOutput;
import com.cplerings.core.application.shared.input.NoInput;
import com.cplerings.core.application.shared.service.configuration.ConfigurationService;
import com.cplerings.core.application.shared.service.payment.PaymentInfo;
import com.cplerings.core.application.shared.service.payment.PaymentRequest;
import com.cplerings.core.application.shared.service.payment.PaymentRequestService;
import com.cplerings.core.application.shared.service.security.SecurityService;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.account.AccountStatus;
import com.cplerings.core.domain.payment.PaymentReceiver;
import com.cplerings.core.domain.payment.PaymentReceiverType;
import com.cplerings.core.domain.shared.valueobject.Money;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCaseImplementation
public class CreateDesignSessionUseCaseImpl extends AbstractUseCase<NoInput, CreateDesignSessionOutput>
        implements CreateDesignSessionUseCase {

    private final CreateDesignSessionDataSource createDesignSessionDataSource;
    private final ACreateDesignSessionMapper aCreateDesignSessionMapper;
    private final SecurityService securityService;
    private final PaymentRequestService paymentRequestService;
    private final ConfigurationService configurationService;

    @Override
    protected void validateInput(UseCaseValidator validator, NoInput input) {
        super.validateInput(validator, input);
    }

    @Override
    protected CreateDesignSessionOutput internalExecute(UseCaseValidator validator, NoInput input) {
        // Check customer is real or not
        Account customer = createDesignSessionDataSource.getAccountByEmail(securityService.getCurrentUser().email())
                .orElse(null);
        validator.validateAndStopExecution(customer != null, CUSTOMER_NOT_FOUND);
        validator.validateAndStopExecution(customer.getStatus() == AccountStatus.ACTIVE, CUSTOMER_NOT_ACTIVE);

        validator.validateAndStopExecution(createDesignSessionDataSource.thereIsNoUnusedDesignSession(customer.getId()), EXIST_UNUSED_DESIGN_SESSION);

        final Money designFee = configurationService.getDesignFee();
        final PaymentRequest paymentRequest = paymentRequestService.requestPayment(PaymentInfo.builder()
                .description(customer.getEmail() + " Design payment fee")
                .amount(designFee)
                .build());

        final PaymentReceiver paymentReceiver = PaymentReceiver.builder()
                .receiverId(String.valueOf(customer.getId()))
                .receiverType(PaymentReceiverType.DESIGN_FEE)
                .payment(paymentRequest.getPayment())
                .build();
        createDesignSessionDataSource.save(paymentReceiver);
        return aCreateDesignSessionMapper.toOutput(paymentRequest);
    }
}
