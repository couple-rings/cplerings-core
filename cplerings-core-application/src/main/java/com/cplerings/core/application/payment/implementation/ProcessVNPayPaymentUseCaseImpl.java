package com.cplerings.core.application.payment.implementation;

import static com.cplerings.core.application.payment.error.PaymentErrorCode.INVALID_PAYMENT_RESULT;
import static com.cplerings.core.application.payment.error.PaymentErrorCode.PAYMENT_RECEIVER_HANDLER_FAILED;
import static com.cplerings.core.application.payment.error.PaymentErrorCode.PAYMENT_WITH_ID_NOT_FOUND;
import static com.cplerings.core.application.payment.error.PaymentErrorCode.RESULT_CODE_REQUIRED;
import static com.cplerings.core.application.payment.error.PaymentErrorCode.SECURE_HASH_REQUIRED;
import static com.cplerings.core.application.payment.error.PaymentErrorCode.TERMINAL_CODE_REQUIRED;

import java.math.BigDecimal;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import com.cplerings.core.application.crafting.ProcessCraftingStageDepositUseCase;
import com.cplerings.core.application.design.ProcessDesignSessionPaymentUseCase;
import com.cplerings.core.application.order.ProcessPayStandardOrderUseCase;
import com.cplerings.core.application.payment.ProcessVNPayPaymentUseCase;
import com.cplerings.core.application.payment.datasource.ProcessVNPayPaymentDataSource;
import com.cplerings.core.application.payment.input.PaymentSuccessfulResultInput;
import com.cplerings.core.application.payment.input.VNPayPaymentInput;
import com.cplerings.core.application.payment.output.VNPayPaymentOutput;
import com.cplerings.core.application.shared.errorcode.ErrorCodes;
import com.cplerings.core.application.shared.mapper.APaymentStatusMapper;
import com.cplerings.core.application.shared.output.NoOutput;
import com.cplerings.core.application.shared.service.payment.PaymentVerificationService;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.common.either.Either;
import com.cplerings.core.common.payment.VNPayConstant;
import com.cplerings.core.domain.payment.Payment;
import com.cplerings.core.domain.payment.PaymentStatus;
import com.cplerings.core.domain.payment.transaction.VNPayTransaction;

import lombok.RequiredArgsConstructor;

@UseCaseImplementation
@RequiredArgsConstructor
public class ProcessVNPayPaymentUseCaseImpl extends AbstractUseCase<VNPayPaymentInput, VNPayPaymentOutput>
        implements ProcessVNPayPaymentUseCase {

    private final PaymentVerificationService<VNPayPaymentInput> paymentVerificationService;
    private final ProcessVNPayPaymentDataSource processVNPayPaymentDataSource;
    private final APaymentStatusMapper aPaymentStatusMapper;
    private final ProcessDesignSessionPaymentUseCase processDesignSessionPaymentUseCase;
    private final ProcessCraftingStageDepositUseCase processCraftingStageDepositUseCase;
    private final ProcessPayStandardOrderUseCase processPayStandardOrderUseCase;

    @Override
    protected void validateInput(UseCaseValidator validator, VNPayPaymentInput input) {
        super.validateInput(validator, input);
        validator.validate(StringUtils.isNotBlank(input.getTerminalCode()), TERMINAL_CODE_REQUIRED);
        validator.validate(StringUtils.isNotBlank(input.getSecureHash()), SECURE_HASH_REQUIRED);
        validator.validate(StringUtils.isNotBlank(input.getResponseCode()), RESULT_CODE_REQUIRED);
    }

    @Override
    protected VNPayPaymentOutput internalExecute(UseCaseValidator validator, VNPayPaymentInput input) {
        validator.validateAndStopExecution(paymentVerificationService.paymentIsValid(input), INVALID_PAYMENT_RESULT);
        final Optional<Payment> paymentOptional = processVNPayPaymentDataSource.findPaymentByIdWithVNPayTransaction(input.getPaymentId());
        validator.validateAndStopExecution(paymentOptional.isPresent(), PAYMENT_WITH_ID_NOT_FOUND);
        Payment payment = paymentOptional.get();
        boolean paymentIsSuccessful = false;
        final PaymentStatus paymentStatus = (switch (input.getResponseCode()) {
            case VNPayConstant.RESPONSE_CODE_SUCCESSFUL,
                 VNPayConstant.RESPONSE_CODE_ABNORMAL_SUCCESSFUL -> {
                paymentIsSuccessful = true;
                yield PaymentStatus.SUCCESSFUL;
            }
            case VNPayConstant.RESPONSE_CODE_EXPIRED -> PaymentStatus.EXPIRED;
            case VNPayConstant.RESPONSE_CODE_CANCELLED -> PaymentStatus.CANCELLED;
            default -> PaymentStatus.FAILED;
        });
        payment.setStatus(paymentStatus);
        payment = processVNPayPaymentDataSource.save(payment);
        if (paymentIsSuccessful) {
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
            handlePaymentReceiver(validator, payment);
        }
        return VNPayPaymentOutput.builder()
                .paymentStatus(aPaymentStatusMapper.toStatus(paymentStatus))
                .build();
    }

    private void handlePaymentReceiver(UseCaseValidator validator, Payment payment) {
        final PaymentSuccessfulResultInput input = PaymentSuccessfulResultInput.builder()
                .payment(payment)
                .build();
        switch (payment.getPaymentReceiverType()) {
            case DESIGN_FEE -> {
                final Either<NoOutput, ErrorCodes> result = processDesignSessionPaymentUseCase.execute(input);
                validator.validateAndStopExecution(result.isLeft(), PAYMENT_RECEIVER_HANDLER_FAILED);
            }
            case CRAFT_STAGE -> {
                final Either<NoOutput, ErrorCodes> result = processCraftingStageDepositUseCase.execute(input);
                validator.validateAndStopExecution(result.isLeft(), PAYMENT_RECEIVER_HANDLER_FAILED);
            }
            case STANDARD -> {
                final Either<NoOutput, ErrorCodes> result = processPayStandardOrderUseCase.execute(input);
                validator.validateAndStopExecution(result.isLeft(), PAYMENT_RECEIVER_HANDLER_FAILED);
            }
            default -> validator.validateAndStopExecution(false, PAYMENT_WITH_ID_NOT_FOUND);
        }
    }
}
