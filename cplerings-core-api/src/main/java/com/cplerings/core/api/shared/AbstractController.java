package com.cplerings.core.api.shared;

import com.cplerings.core.api.shared.mapper.APICustomRequestMapper;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.application.shared.errorcode.ErrorCode;
import com.cplerings.core.application.shared.errorcode.ErrorCodes;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.either.Either;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;

@RequestMapping("${cplerings.api.path}")
public abstract class AbstractController<IN, OUT, DATA, REQ, RES> {

    protected final <CUSTOM_REQ> ResponseEntity<Object> handleRequest(CUSTOM_REQ customRequest, APICustomRequestMapper<CUSTOM_REQ, REQ> apiCustomRequestMapper) {
        Objects.requireNonNull(apiCustomRequestMapper);
        final REQ request = apiCustomRequestMapper.map(customRequest);
        return handleRequest(request);
    }

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

    protected final ResponseEntity<Object> handleErrorCodes(ErrorCodes errorCodes) {
        final Collection<ErrorCode> errors = errorCodes.getErrors();
        final Optional<ErrorCode> exceptionError = errors.stream()
                .filter(errorCode -> (ErrorCode.Type.SYSTEM == errorCode.getType()))
                .findFirst();
        if (exceptionError.isPresent()) {
            final ErrorCodes exceptionErrorCodes = ErrorCodes.create(Collections.singletonList(exceptionError.get()));
            return ResponseEntity.internalServerError()
                    .body(ErrorCodesResponse.create(exceptionErrorCodes));
        }
        final ErrorCodesResponse response = ErrorCodesResponse.create(errorCodes);
        return ResponseEntity.badRequest().body(response);
    }

    @SuppressWarnings("unchecked")
    protected final ResponseEntity<Object> handleRequest() {
        return handleRequest((REQ) NoRequest.INSTANCE);
    }
}
