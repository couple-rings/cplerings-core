package com.cplerings.core.test.shared.hello;

import static com.cplerings.core.application.shared.errorcode.ErrorCode.SYSTEM_ERROR;

import com.cplerings.core.application.shared.usecase.AbstractNewUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;

import lombok.RequiredArgsConstructor;

import org.springframework.boot.test.context.TestComponent;

@TestComponent
@RequiredArgsConstructor
public class TestFailedValidationUseCase extends AbstractNewUseCase<String, String> {

    private final HelloRepository helloRepository;

    @Override
    protected void validateInput(UseCaseValidator validator, String input) {
        super.validateInput(validator, input);
        final Hello hello = Hello.builder()
                .name(input)
                .createdBy(input)
                .build();
        helloRepository.save(hello);
        validator.validate(false, SYSTEM_ERROR);
    }

    @Override
    protected String internalExecute(UseCaseValidator validator, String input) {
        return "Hello";
    }
}
