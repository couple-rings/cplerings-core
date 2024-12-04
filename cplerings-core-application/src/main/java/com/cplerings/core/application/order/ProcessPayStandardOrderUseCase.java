package com.cplerings.core.application.order;

import com.cplerings.core.application.payment.input.PaymentSuccessfulResultInput;
import com.cplerings.core.application.shared.output.NoOutput;
import com.cplerings.core.application.shared.usecase.UseCase;

public interface ProcessPayStandardOrderUseCase extends UseCase<PaymentSuccessfulResultInput, NoOutput> {

}
