package com.cplerings.core.application.authentication;

import com.cplerings.core.application.authentication.input.LoginCredentialInput;
import com.cplerings.core.application.authentication.output.AuthenticationTokenOutput;
import com.cplerings.core.application.shared.usecase.ErrorCodes;
import com.cplerings.core.application.shared.usecase.UserStory;
import com.cplerings.core.application.shared.usecase.UserStoryCode;
import com.cplerings.core.common.pair.Pair;

@UserStory(code = UserStoryCode.CPLERINGS_1)
public interface LoginUserStory {

    Pair<AuthenticationTokenOutput, ErrorCodes> login(LoginCredentialInput input);
}
