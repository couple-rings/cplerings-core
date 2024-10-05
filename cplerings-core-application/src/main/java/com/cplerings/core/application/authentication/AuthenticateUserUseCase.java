package com.cplerings.core.application.authentication;

import com.cplerings.core.application.authentication.input.JWTInput;
import com.cplerings.core.application.authentication.output.AuthenticatedAccountOutput;
import com.cplerings.core.application.shared.usecase.UseCase;

public interface AuthenticateUserUseCase extends UseCase<JWTInput, AuthenticatedAccountOutput> {

}
