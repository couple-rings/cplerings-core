package com.cplerings.core.application.shared.usecase;

import com.cplerings.core.application.shared.errorcode.ErrorCodes;
import com.cplerings.core.common.either.Either;

public interface UseCase<I, O> {

    Either<O, ErrorCodes> execute(I input);
}
