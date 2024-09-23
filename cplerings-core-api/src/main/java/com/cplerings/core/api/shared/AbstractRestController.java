package com.cplerings.core.api.shared;

import com.cplerings.core.application.shared.errorcode.ErrorCode;
import com.cplerings.core.application.shared.errorcode.ErrorCodes;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@RequestMapping("${cplerings.api.path}")
public abstract class AbstractRestController {

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
}
