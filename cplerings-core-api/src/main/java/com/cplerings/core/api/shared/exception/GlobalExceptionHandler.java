package com.cplerings.core.api.shared.exception;

import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.NoData;
import com.cplerings.core.api.shared.NoRequest;
import com.cplerings.core.api.shared.NoResponse;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.application.shared.errorcode.ErrorCodes;
import com.cplerings.core.application.shared.input.NoInput;
import com.cplerings.core.application.shared.output.NoOutput;
import com.cplerings.core.application.shared.usecase.UseCase;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<Object> handleException(Throwable e) {
        log.error(e.getMessage(), e);
        return ExceptionController.INSTANCE.internalHandleException();
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    private static final class ExceptionController extends AbstractController<NoInput, NoOutput, NoData, NoRequest, NoResponse> {

        private static final ExceptionController INSTANCE = new ExceptionController();

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
}
