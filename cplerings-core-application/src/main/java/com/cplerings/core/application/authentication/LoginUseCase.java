package com.cplerings.core.application.authentication;

import com.cplerings.core.application.authentication.input.LoginCredentialInput;
import com.cplerings.core.application.authentication.output.AuthenticationTokenOutput;
import com.cplerings.core.application.shared.errorcode.ErrorCodes;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.either.Either;

@UseCase(code = "CPLERINGS-1")
public interface LoginUseCase {

    Either<AuthenticationTokenOutput, ErrorCodes> login(LoginCredentialInput input);
}
