package com.cplerings.core.api.shared;

import com.cplerings.core.api.mapper.APIMapper;
import com.cplerings.core.application.shared.errorcode.ErrorCodes;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.either.Either;

import org.springframework.http.ResponseEntity;

@SuppressWarnings("java:S119")
public abstract class AbstractDataController<IN, OUT, DATA, REQ, RES>
        extends AbstractRestController {

    protected final ResponseEntity<Object> handleRequest(REQ request) {
        UseCase<IN, OUT> useCase = getUseCase();
        APIMapper<IN, OUT, DATA, REQ, RES> mapper = getMapper();
        final IN input = mapper.toInput(request);
        final Either<OUT, ErrorCodes> result = useCase.execute(input);
        if (result.isLeft()) {
            final OUT output = result.getLeft();
            final DATA data = mapper.toData(output);
            return ResponseEntity.ok(mapper.toResponse(data));
        } else {
            return handleErrorCodes(result.getRight());
        }
    }

    protected abstract UseCase<IN, OUT> getUseCase();

    protected abstract APIMapper<IN, OUT, DATA, REQ, RES> getMapper();
}
