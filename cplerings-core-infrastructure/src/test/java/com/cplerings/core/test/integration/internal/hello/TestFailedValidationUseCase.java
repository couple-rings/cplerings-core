package com.cplerings.core.test.integration.internal.hello;

import com.cplerings.core.application.shared.errorcode.ErrorCode;
import com.cplerings.core.application.shared.errorcode.ErrorCodes;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.common.either.Either;

import lombok.RequiredArgsConstructor;

import org.springframework.boot.test.context.TestComponent;

@TestComponent
@RequiredArgsConstructor
public class TestFailedValidationUseCase extends AbstractUseCase<String, String> {

    private final HelloRepository helloRepository;

    public Either<String, ErrorCodes> sayHelloFailedAtValidationStep(String name) {
        addStep(AbstractUseCase.<Hello, Void>createStep(s -> {
            // Intentionally blank
            return null;
        }));
        return executeSteps(name);
    }

    @Override
    protected void validateInput(String input) {
        super.validateInput(input);
        final Hello hello = Hello.builder()
                .name(input)
                .createdBy(input)
                .build();
        helloRepository.save(hello);
        validate(false, ErrorCode.SYSTEM_ERROR);
    }
}
