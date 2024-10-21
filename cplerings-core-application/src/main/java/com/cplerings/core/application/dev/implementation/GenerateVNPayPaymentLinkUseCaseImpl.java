package com.cplerings.core.application.dev.implementation;

import static com.cplerings.core.application.dev.error.DevErrorCode.MONEY_MUST_BE_AT_LEAST_10K;
import static com.cplerings.core.application.dev.error.DevErrorCode.MONEY_REQUIRED;

import com.cplerings.core.application.dev.GenerateVNPayPaymentLinkUseCase;
import com.cplerings.core.application.dev.input.GenerateVNPayPaymentLinkInput;
import com.cplerings.core.application.shared.service.payment.PaymentInfo;
import com.cplerings.core.application.shared.service.payment.PaymentRequestService;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.common.payment.PaymentConstant;
import com.cplerings.core.domain.shared.valueobject.Money;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.math.BigDecimal;

@UseCaseImplementation
public class GenerateVNPayPaymentLinkUseCaseImpl extends AbstractUseCase<GenerateVNPayPaymentLinkInput, String> implements GenerateVNPayPaymentLinkUseCase {

    private PaymentRequestService paymentRequestService;

    @Override
    protected void validateInput(UseCaseValidator validator, GenerateVNPayPaymentLinkInput input) {
        super.validateInput(validator, input);
        validator.validate(input.amount() != null, MONEY_REQUIRED);
        validator.validate(input.amount().compareTo(BigDecimal.valueOf(10000)) > 0, MONEY_MUST_BE_AT_LEAST_10K);
    }

    @Override
    protected String internalExecute(UseCaseValidator validator, GenerateVNPayPaymentLinkInput input) {
        return paymentRequestService.requestPayment(PaymentInfo.builder()
                        .amount(Money.create(input.amount()))
                        .description("Amount for " + input.amount().toPlainString())
                        .build())
                .getPaymentLink();
    }

    @Autowired
    @Qualifier(PaymentConstant.VNPAY_PAYMENT_SERVICE_NAME)
    public void setPaymentRequestService(PaymentRequestService paymentRequestService) {
        this.paymentRequestService = paymentRequestService;
    }
}
