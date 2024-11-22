package com.cplerings.core.api.shared.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.ExceptionResponse;
import com.cplerings.core.api.shared.NoData;
import com.cplerings.core.api.shared.NoRequest;
import com.cplerings.core.api.shared.NoResponse;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.application.shared.errorcode.ErrorCodes;
import com.cplerings.core.application.shared.input.NoInput;
import com.cplerings.core.application.shared.output.NoOutput;
import com.cplerings.core.application.shared.usecase.ErrorCodeException;
import com.cplerings.core.application.shared.usecase.UseCase;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ErrorCodeException.class)
    public ResponseEntity<Object> handleException(ErrorCodeException e) {
        log.error(e.getMessage(), e);
        return ErrorCodeExceptionController.INSTANCE.internalHandleException();
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    private static final class ErrorCodeExceptionController extends AbstractController<NoInput, NoOutput, NoData, NoRequest, NoResponse> {

        private static final ErrorCodeExceptionController INSTANCE = new ErrorCodeExceptionController();

        @Override
        protected UseCase<NoInput, NoOutput> getUseCase() {
            return null;
        }

        @Override
        protected APIMapper<NoInput, NoOutput, NoData, NoRequest, NoResponse> getMapper() {
            return null;
        }

        public ResponseEntity<Object> internalHandleException() {
            return handleErrorCodes(ErrorCodes.SYSTEM_ERROR);
        }
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception e) {
        return ResponseEntity.internalServerError()
                .body(new ExceptionResponse(e));
    }
}
