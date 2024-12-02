package com.cplerings.core.application.design;

import com.cplerings.core.application.payment.input.PaymentSuccessfulResultInput;
import com.cplerings.core.application.shared.output.NoOutput;
import com.cplerings.core.application.shared.usecase.UseCase;

public interface ProcessDesignSessionPaymentUseCase extends UseCase<PaymentSuccessfulResultInput, NoOutput> {

}
