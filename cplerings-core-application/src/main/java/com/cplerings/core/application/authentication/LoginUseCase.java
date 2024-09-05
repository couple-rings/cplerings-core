package com.cplerings.core.application.authentication;

import com.cplerings.core.application.authentication.input.LoginCredentialInput;
import com.cplerings.core.application.authentication.output.AuthenticationTokenOutput;
import com.cplerings.core.application.shared.usecase.ErrorCodes;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.pair.Pair;

@UseCase(code = "CPLERINGS-1")
public interface LoginUseCase {

    Pair<AuthenticationTokenOutput, ErrorCodes> login(LoginCredentialInput input);
}
