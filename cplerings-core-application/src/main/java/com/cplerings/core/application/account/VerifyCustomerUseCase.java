package com.cplerings.core.application.account;

import com.cplerings.core.application.account.input.VerifyCustomerInput;
import com.cplerings.core.application.authentication.output.AuthenticationTokenOutput;
import com.cplerings.core.application.shared.usecase.UseCase;

public interface VerifyCustomerUseCase extends UseCase<VerifyCustomerInput, AuthenticationTokenOutput> {

}
