package com.cplerings.core.test.integration.environment;

import com.cplerings.core.application.shared.errorcode.ErrorCodes;
import com.cplerings.core.common.either.Either;
import com.cplerings.core.test.integration.shared.AbstractIT;
import com.cplerings.core.test.integration.shared.hello.HelloRepository;
import com.cplerings.core.test.integration.shared.hello.TestFailedStepUseCase;
import com.cplerings.core.test.integration.shared.hello.TestFailedValidationUseCase;

import lombok.extern.slf4j.Slf4j;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Slf4j
class TransactionManagerIT extends AbstractIT {

    private static final String NAME = "Micheal";
    private static final String CORRELATION_ID = "correlationId";

    @Autowired
    private TestFailedValidationUseCase testFailedValidationUseCase;

    @Autowired
    private TestFailedStepUseCase testFailedStepUseCase;

    @Autowired
    private HelloRepository helloRepository;

    @Test
    void givenTransaction_whenFailedAtValidationStep() {
        final String transactionId = UUID.randomUUID().toString();
        MDC.put(CORRELATION_ID, transactionId);

        final Either<String, ErrorCodes> result = testFailedValidationUseCase.execute(NAME);

        thenResultIsFailed(result);
        thenNoChangesToDatabaseWasMade();
    }

    private void thenResultIsFailed(Either<String, ErrorCodes> result) {
        Assertions.assertThat(result.isRight())
                .isTrue();
    }

    @Test
    void givenTransaction_whenFailedAtStep() {
        final String transactionId = UUID.randomUUID().toString();
        MDC.put(CORRELATION_ID, transactionId);

        final Either<String, ErrorCodes> result = testFailedStepUseCase.execute(NAME);

        thenResultIsFailed(result);
        thenNoChangesToDatabaseWasMade();
    }

    private void thenNoChangesToDatabaseWasMade() {
        Assertions.assertThat(helloRepository.existsByName(NAME))
                .isFalse();
    }
}
