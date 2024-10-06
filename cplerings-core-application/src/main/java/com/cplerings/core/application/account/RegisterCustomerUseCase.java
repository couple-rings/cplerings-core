package com.cplerings.core.application.account;

import com.cplerings.core.application.account.input.RegisterCustomerInput;
import com.cplerings.core.application.account.output.CustomerRegistrationOutput;
import com.cplerings.core.application.shared.usecase.UseCase;

public interface RegisterCustomerUseCase extends UseCase<RegisterCustomerInput, CustomerRegistrationOutput> {

}
