package com.cplerings.core.application.shared.usecase;

import com.cplerings.core.application.shared.errorcode.ErrorCode;
import com.cplerings.core.application.shared.errorcode.ErrorCodes;
import com.cplerings.core.application.shared.transaction.Session;
import com.cplerings.core.application.shared.transaction.SessionInformation;
import com.cplerings.core.application.shared.transaction.TransactionManager;
import com.cplerings.core.common.either.Either;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.function.Function;

@Slf4j
@Component
public abstract class AbstractUseCase<I, O> {

    private static final String VALIDATION_FAILED = "Validation failed for use case {} with correlation ID {}";
    private static final String STEP_FAILED = "Step failed for use case {} with correlation ID {}";

    private final Collection<ErrorCode> validationErrorCodes = new ArrayList<>();
    private final Collection<Function<Object, Object>> steps = new ArrayList<>();

    private TransactionManager transactionManager;

    @SuppressWarnings("unchecked")
    protected static <T, R> Function<Object, Object> createStep(Function<T, R> function) {
        return (Function<Object, Object>) function;
    }

    @Autowired
    public void setTransactionManager(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    @SuppressWarnings("java:S4276")
    protected final void addStep(Function<Object, Object> step) {
        Objects.requireNonNull(step, "Step must not be null");
        steps.add(step);
    }

    @SuppressWarnings({ "unchecked", "java:S4276" })
    protected final Either<O, ErrorCodes> executeSteps(I input) {
        if (CollectionUtils.isEmpty(steps)) {
            throw new IllegalStateException("Steps are empty");
        }
        final Session session = transactionManager.createSession(SessionInformation.DEFAULT);
        final Either<I, ErrorCodes> validationEither = validateInputInternal(input);
        if (validationEither.isRight()) {
            session.rollback();
            log.info(VALIDATION_FAILED, this.getClass().getSimpleName(), MDC.get("correlationId"));
            return Either.<O, ErrorCodes>builder()
                    .right(validationEither.getRight())
                    .build();
        }
        Object result = input;
        for (Function<Object, Object> step : steps) {
            try {
                result = step.apply(result);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                validate(false, ErrorCode.SYSTEM_ERROR);
            }
            if (hasErrors()) {
                steps.clear();
                session.rollback();
                log.info(STEP_FAILED, this.getClass().getSimpleName(), MDC.get("correlationId"));
                return Either.<O, ErrorCodes>builder()
                        .right(extractAndEmptyErrorCodes())
                        .build();
            }
        }
        steps.clear();
        try {
            session.commit();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            validate(false, ErrorCode.SYSTEM_ERROR);
            return Either.<O, ErrorCodes>builder()
                    .right(extractAndEmptyErrorCodes())
                    .build();
        }
        return Either.<O, ErrorCodes>builder()
                .left((O) result)
                .build();
    }

    protected final Either<I, ErrorCodes> validateInputInternal(I input) {
        validateInput(input);
        if (hasErrors()) {
            return Either.<I, ErrorCodes>builder()
                    .right(extractAndEmptyErrorCodes())
                    .build();
        }
        return Either.<I, ErrorCodes>builder()
                .left(input)
                .build();
    }

    protected final boolean validate(boolean validCondition, ErrorCode errorCode) {
        Objects.requireNonNull(errorCode);
        if (!validCondition) {
            validationErrorCodes.add(errorCode);
        }
        return validCondition;
    }

    protected final boolean hasErrors() {
        return !validationErrorCodes.isEmpty();
    }

    protected final ErrorCodes extractAndEmptyErrorCodes() {
        final Collection<ErrorCode> tmpErrorCodes = new ArrayList<>(validationErrorCodes);
        validationErrorCodes.clear();
        return ErrorCodes.create(tmpErrorCodes);
    }

    protected void validateInput(I input) {
        // To be implemented
    }
}
