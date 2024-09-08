package com.cplerings.core.test.integration.environment;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;

import com.cplerings.core.application.shared.errorcode.ErrorCodes;
import com.cplerings.core.common.pair.Either;
import com.cplerings.core.test.integration.AbstractLoggingIT;
import com.cplerings.core.test.integration.internal.hello.HelloRepository;
import com.cplerings.core.test.integration.internal.hello.TestFailedStepUseCase;
import com.cplerings.core.test.integration.internal.hello.TestFailedValidationUseCase;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class TransactionManagerIT extends AbstractLoggingIT {

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

        final Either<String, ErrorCodes> result = testFailedValidationUseCase.sayHelloFailedAtValidationStep(NAME);

        thenResultIsFailed(result);
        thenThereIsValidationRollbackLog(transactionId);
    }

    private void thenResultIsFailed(Either<String, ErrorCodes> result) {
        Assertions.assertThat(result.isRight())
                .isTrue();
    }

    private void thenThereIsValidationRollbackLog(String transactionId) {
        Assertions.assertThat(getUseCaseLogAppender().getLoggingEvents())
                .anyMatch(iLoggingEvent -> {
                    final String message = iLoggingEvent.getFormattedMessage();
                    return StringUtils.containsIgnoreCase(message, "validation failed")
                            && StringUtils.containsIgnoreCase(message, transactionId);
                });
    }

    @Test
    void givenTransaction_whenFailedAtStep() {
        final String transactionId = UUID.randomUUID().toString();
        MDC.put(CORRELATION_ID, transactionId);

        final Either<String, ErrorCodes> result = testFailedStepUseCase.sayHelloFailedAtExecutionStep(NAME);

        thenResultIsFailed(result);
        thenThereIsStepRollbackLog(transactionId);
        thenNoChangesToDatabaseWasMade();
    }

    private void thenThereIsStepRollbackLog(String transactionId) {
        Assertions.assertThat(getUseCaseLogAppender().getLoggingEvents())
                .anyMatch(iLoggingEvent -> {
                    final String message = iLoggingEvent.getFormattedMessage();
                    return StringUtils.containsIgnoreCase(message, "step failed")
                            && StringUtils.containsIgnoreCase(message, transactionId);
                });
    }

    private void thenNoChangesToDatabaseWasMade() {
        Assertions.assertThat(helloRepository.existsByName(NAME))
                .isFalse();
    }
}
