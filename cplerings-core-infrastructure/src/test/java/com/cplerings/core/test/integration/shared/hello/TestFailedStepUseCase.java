package com.cplerings.core.test.integration.shared.hello;

import com.cplerings.core.application.shared.usecase.AbstractNewUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;

import lombok.RequiredArgsConstructor;

import org.springframework.boot.test.context.TestComponent;

@TestComponent
@RequiredArgsConstructor
public class TestFailedStepUseCase extends AbstractNewUseCase<String, String> {

    private final HelloRepository helloRepository;

    @Override
    protected String internalExecute(UseCaseValidator validator, String name) {
        final Hello hello = Hello.builder()
                .name(name)
                .createdBy(name)
                .build();
        helloRepository.save(hello);
        throw new RuntimeException("Intentionally throws here");
    }
}
