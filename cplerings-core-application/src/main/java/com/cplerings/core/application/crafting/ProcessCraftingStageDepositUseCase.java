package com.cplerings.core.application.crafting;

import com.cplerings.core.application.payment.input.PaymentSuccessfulResultInput;
import com.cplerings.core.application.shared.output.NoOutput;
import com.cplerings.core.application.shared.usecase.UseCase;

public interface ProcessCraftingStageDepositUseCase extends UseCase<PaymentSuccessfulResultInput, NoOutput> {

}
