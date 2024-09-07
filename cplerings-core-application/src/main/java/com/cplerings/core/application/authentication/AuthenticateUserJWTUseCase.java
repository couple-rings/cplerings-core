package com.cplerings.core.application.authentication;

import com.cplerings.core.application.authentication.input.JWTInput;
import com.cplerings.core.application.authentication.output.AccountOutput;
import com.cplerings.core.application.shared.errorcode.ErrorCodes;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.pair.Either;

@UseCase(code = "CPLERINGS-2")
public interface AuthenticateUserJWTUseCase {

    Either<AccountOutput, ErrorCodes> authenticate(JWTInput input);
}
