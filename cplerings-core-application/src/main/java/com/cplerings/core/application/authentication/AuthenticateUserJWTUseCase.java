package com.cplerings.core.application.authentication;

import com.cplerings.core.application.authentication.input.JWTInput;
import com.cplerings.core.application.authentication.output.AccountOutput;
import com.cplerings.core.application.shared.usecase.UseCase;

public interface AuthenticateUserJWTUseCase extends UseCase<JWTInput, AccountOutput> {

}
