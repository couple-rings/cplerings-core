package com.cplerings.core.test.integration.shared.hello;

import com.cplerings.core.application.shared.errorcode.ErrorCodes;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.common.either.Either;

import lombok.RequiredArgsConstructor;

import org.springframework.boot.test.context.TestComponent;

@TestComponent
@RequiredArgsConstructor
public class TestFailedStepUseCase extends AbstractUseCase<String, String> {

    private final HelloRepository helloRepository;

    public Either<String, ErrorCodes> sayHelloFailedAtExecutionStep(String name) {
        addStep(AbstractUseCase.<String, Hello>createStep(s -> {
            final Hello hello = Hello.builder()
                    .name(name)
                    .createdBy(name)
                    .build();
            return helloRepository.save(hello);
        }));
        addStep(AbstractUseCase.<Hello, Void>createStep(o -> {
            throw new RuntimeException("Intentionally throws here");
        }));
        return executeSteps(name);
    }
}
