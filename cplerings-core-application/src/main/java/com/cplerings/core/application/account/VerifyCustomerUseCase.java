package com.cplerings.core.application.account;

import com.cplerings.core.application.account.input.CustomerVerificationInput;
import com.cplerings.core.application.authentication.output.AuthenticationTokenOutput;
import com.cplerings.core.application.shared.usecase.UseCase;

public interface VerifyCustomerUseCase extends UseCase<CustomerVerificationInput, AuthenticationTokenOutput> {

}
