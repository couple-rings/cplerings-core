package com.cplerings.core.application.authentication;

import com.cplerings.core.application.authentication.input.LoginCredentialInput;
import com.cplerings.core.application.authentication.output.AuthenticationTokenOutput;
import com.cplerings.core.application.shared.usecase.UseCase;

public interface LoginUseCase extends UseCase<LoginCredentialInput, AuthenticationTokenOutput> {

}
