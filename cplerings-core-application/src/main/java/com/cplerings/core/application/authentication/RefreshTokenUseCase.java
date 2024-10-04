package com.cplerings.core.application.authentication;

import com.cplerings.core.application.authentication.input.RefreshTokenInput;
import com.cplerings.core.application.authentication.output.AuthenticationTokenOutput;
import com.cplerings.core.application.shared.usecase.UseCase;

public interface RefreshTokenUseCase extends UseCase<RefreshTokenInput, AuthenticationTokenOutput> {

}
