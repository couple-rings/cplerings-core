package com.cplerings.core.application.authentication;

import com.cplerings.core.application.authentication.input.JWTInput;
import com.cplerings.core.application.authentication.output.AccountOutput;
import com.cplerings.core.application.shared.usecase.ErrorCodes;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.pair.Pair;

@UseCase(code = "CPLERINGS-2")
public interface AuthenticateUserJWTUseCase {

    Pair<AccountOutput, ErrorCodes> authenticate(JWTInput input);
}
