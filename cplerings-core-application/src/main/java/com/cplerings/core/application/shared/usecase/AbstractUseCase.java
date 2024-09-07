package com.cplerings.core.application.shared.usecase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.function.Function;

import org.apache.commons.collections4.CollectionUtils;

import com.cplerings.core.application.shared.errorcode.ErrorCode;
import com.cplerings.core.application.shared.errorcode.ErrorCodes;
import com.cplerings.core.common.pair.Either;

public abstract class AbstractUseCase<I, O> {

    private final Collection<ErrorCode> validationErrorCodes = new ArrayList<>();
    private final Collection<Function<Object, Object>> steps = new ArrayList<>();

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

    protected final Either<I, ErrorCodes> validateInputInternal(I input) {
        validateInput(input);
        if (hasErrors()) {
            return Either.<I, ErrorCodes>builder()
                    .right(extractAndEmptyErrorCodes())
                    .defaultBuild();
        }
        return Either.<I, ErrorCodes>builder()
                .left(input)
                .defaultBuild();
    }

    @SuppressWarnings("unchecked")
    protected static <T, R> Function<Object, Object> createStep(Function<T, R> function) {
        return (Function<Object, Object>) function;
    }

    @SuppressWarnings("java:S4276")
    protected final void addStep(Function<Object, Object> step) {
        Objects.requireNonNull(step, "Step must not be null");
        steps.add(step);
    }

    @SuppressWarnings({"unchecked", "java:S4276"})
    protected final Either<O, ErrorCodes> executeSteps(I input) {
        if (CollectionUtils.isEmpty(steps)) {
            throw new IllegalStateException("Steps are empty");
        }
        final Either<I, ErrorCodes> validationEither = validateInputInternal(input);
        if (validationEither.isRight()) {
            return Either.<O, ErrorCodes>builder()
                    .right(validationEither.getRight())
                    .defaultBuild();
        }
        Object result = input;
        for (Function<Object, Object> step : steps) {
            try {
                result = step.apply(result);
            } catch (Exception e) {
                validate(false, ErrorCode.SYSTEM_ERROR);
            }
            if (hasErrors()) {
                steps.clear();
                return Either.<O, ErrorCodes>builder()
                        .right(extractAndEmptyErrorCodes())
                        .defaultBuild();
            }
        }
        steps.clear();
        return Either.<O, ErrorCodes>builder()
                .left((O) result)
                .defaultBuild();
    }
}
