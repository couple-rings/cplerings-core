package com.cplerings.core.api;

import java.util.Collection;

import com.cplerings.core.application.shared.usecase.ErrorCode;
import com.cplerings.core.application.shared.usecase.ErrorCodes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("${cplerings.api.path}")
public abstract class AbstractRestController {

    protected final ResponseEntity<?> handleErrorCodes(ErrorCodes errorCodes) {
        final Collection<ErrorCode> errors = errorCodes.getErrors();
        final ErrorCodesResponse response = ErrorCodesResponse.create(errorCodes);
        if (errors.stream().anyMatch(errorCode -> ErrorCode.Type.SYSTEM == errorCode.getType())) {
            return ResponseEntity.internalServerError().body(response);
        }
        return ResponseEntity.badRequest().body(response);
    }
}
