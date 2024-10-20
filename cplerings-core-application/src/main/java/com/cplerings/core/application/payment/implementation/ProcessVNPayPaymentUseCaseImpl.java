package com.cplerings.core.application.payment.implementation;

import static com.cplerings.core.application.payment.error.PaymentErrorCode.INVALID_PAYMENT_RESULT;
import static com.cplerings.core.application.payment.error.PaymentErrorCode.PAYMENT_WITH_ID_NOT_FOUND;
import static com.cplerings.core.application.payment.error.PaymentErrorCode.RESULT_CODE_REQUIRED;
import static com.cplerings.core.application.payment.error.PaymentErrorCode.SECURE_HASH_REQUIRED;
import static com.cplerings.core.application.payment.error.PaymentErrorCode.TERMINAL_CODE_REQUIRED;

import com.cplerings.core.application.payment.ProcessVNPayPaymentUseCase;
import com.cplerings.core.application.payment.datasource.ProcessVNPayPaymentDataSource;
import com.cplerings.core.application.payment.input.VNPayPaymentInput;
import com.cplerings.core.application.shared.output.NoOutput;
import com.cplerings.core.application.shared.service.payment.PaymentVerificationService;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.common.payment.VNPayConstant;
import com.cplerings.core.domain.payment.Payment;
import com.cplerings.core.domain.payment.PaymentStatus;
import com.cplerings.core.domain.payment.transaction.VNPayTransaction;

import lombok.RequiredArgsConstructor;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.Optional;

@UseCaseImplementation
@RequiredArgsConstructor
public class ProcessVNPayPaymentUseCaseImpl extends AbstractUseCase<VNPayPaymentInput, NoOutput>
        implements ProcessVNPayPaymentUseCase {

    private final PaymentVerificationService<VNPayPaymentInput> paymentVerificationService;
    private final ProcessVNPayPaymentDataSource processVNPayPaymentDataSource;

    @Override
    protected void validateInput(UseCaseValidator validator, VNPayPaymentInput input) {
        super.validateInput(validator, input);
        validator.validate(StringUtils.isNotBlank(input.getTerminalCode()), TERMINAL_CODE_REQUIRED);
        validator.validate(StringUtils.isNotBlank(input.getSecureHash()), SECURE_HASH_REQUIRED);
        validator.validate(StringUtils.isNotBlank(input.getResponseCode()), RESULT_CODE_REQUIRED);
    }

    @Override
    protected NoOutput internalExecute(UseCaseValidator validator, VNPayPaymentInput input) {
        validator.validateAndStopExecution(paymentVerificationService.paymentIsValid(input), INVALID_PAYMENT_RESULT);
        final Optional<Payment> paymentOptional = processVNPayPaymentDataSource.findPaymentByIdWithVNPayTransaction(input.getPaymentId());
        validator.validateAndStopExecution(paymentOptional.isPresent(), PAYMENT_WITH_ID_NOT_FOUND);
        Payment payment = paymentOptional.get();
        payment.setStatus(switch (input.getResponseCode()) {
            case VNPayConstant.RESPONSE_CODE_SUCCESSFUL,
                 VNPayConstant.RESPONSE_CODE_ABNORMAL_SUCCESSFUL -> PaymentStatus.SUCCESSFUL;
            case VNPayConstant.RESPONSE_CODE_EXPIRED -> PaymentStatus.EXPIRED;
            case VNPayConstant.RESPONSE_CODE_CANCELLED -> PaymentStatus.CANCELLED;
            default -> PaymentStatus.FAILED;
        });
        processVNPayPaymentDataSource.save(payment);
        final VNPayTransaction vnPayTransaction = VNPayTransaction.builder()
                .payDate(input.getPayDate())
                .amount(input.getAmount().divide(BigDecimal.valueOf(100)))
                .bankCode(input.getBankCode())
                .transactionId(input.getTransactionId())
                .bankTransferId(input.getBankTransferId())
                .orderInfo(input.getOrderInfo())
                .cardType(input.getCardType())
                .payment(payment)
                .secureHash(input.getSecureHash())
                .build();
        processVNPayPaymentDataSource.save(vnPayTransaction);
        return NoOutput.INSTANCE;
    }
}
