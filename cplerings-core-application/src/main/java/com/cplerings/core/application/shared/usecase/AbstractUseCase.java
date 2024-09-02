package com.cplerings.core.application.shared.usecase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.function.Function;

import com.cplerings.core.common.pair.Pair;
import org.apache.commons.collections4.CollectionUtils;

public abstract class AbstractUseCase<I, O, E extends ErrorCode> {

    private final Collection<E> validationErrorCodes = new ArrayList<>();
    private final Collection<Function<Object, Object>> steps = new ArrayList<>();

    protected final void validate(boolean validCondition, E errorCode) {
        Objects.requireNonNull(errorCode);
        if (!validCondition) {
            validationErrorCodes.add(errorCode);
        }
    }

    protected final boolean hasErrors() {
        return !validationErrorCodes.isEmpty();
    }

    protected final ErrorCodes extractAndEmptyErrorCodes() {
        final Collection<ErrorCode> tmpErrorCodes = new ArrayList<>(validationErrorCodes);
        validationErrorCodes.clear();
        return ErrorCodes.create(tmpErrorCodes);
    }

    protected void validateInputInternal(I input) {
        // To be implemented
    }

    protected final Pair<I, ErrorCodes> validateInput(I input) {
        validateInputInternal(input);
        if (hasErrors()) {
            return Pair.<I, ErrorCodes>builder()
                    .right(extractAndEmptyErrorCodes())
                    .defaultBuild();
        }
        return Pair.<I, ErrorCodes>builder()
                .left(input)
                .defaultBuild();
    }

    @SuppressWarnings("unchecked")
    protected static <T, R> Function<Object, Object> createStep(Function<T, R> function) {
        return (Function<Object, Object>) function;
    }

    protected final void addStep(Function<Object, Object> step) {
        Objects.requireNonNull(step, "Step must not be null");
        steps.add(step);
    }

    @SuppressWarnings("unchecked")
    protected final Pair<O, ErrorCodes> executeSteps(I input) {
        if (CollectionUtils.isEmpty(steps)) {
            throw new IllegalStateException("Steps are empty");
        }
        final Pair<I, ErrorCodes> validationPair = validateInput(input);
        if (validationPair.isRight()) {
            return Pair.<O, ErrorCodes>builder()
                    .right(validationPair.getRight())
                    .defaultBuild();
        }
        Object result = input;
        for (Function<Object, Object> step : steps) {
            result = step.apply(result);
            if (hasErrors()) {
                return Pair.<O, ErrorCodes>builder()
                        .right(extractAndEmptyErrorCodes())
                        .defaultBuild();
            }
        }
        return Pair.<O, ErrorCodes>builder()
                .left((O) result)
                .defaultBuild();
    }
}
