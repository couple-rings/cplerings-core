package com.cplerings.core.application.shared.usecase;

import static com.cplerings.core.application.shared.errorcode.ErrorCode.System.ERROR;

import com.cplerings.core.application.shared.errorcode.ErrorCodes;
import com.cplerings.core.application.shared.transaction.Session;
import com.cplerings.core.application.shared.transaction.SessionInformation;
import com.cplerings.core.application.shared.transaction.SessionPropagation;
import com.cplerings.core.application.shared.transaction.TransactionManager;
import com.cplerings.core.common.either.Either;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public abstract class AbstractUseCase<I, O> implements UseCase<I, O> {

    private TransactionManager transactionManager;

    @Override
    public final Either<O, ErrorCodes> execute(I input) {
        log.info("Executing UseCase {}", getClass().getSimpleName());
        final UseCaseValidator validator = new UseCaseValidator();
        final SessionInformation sessionInformation = customizeSessionInformation();
        final Session session = transactionManager.createSession(sessionInformation);
        try {
            validator.validateAndStopExecution(input != null, ERROR);
            validateInput(validator, input);
            validator.clearAndThrowErrorCodes();
            final O output = internalExecute(validator, input);
            session.commit();
            return Either.left(output);
        } catch (ErrorCodeException e) {
            log.error(e.getMessage(), e);
            session.rollback();
            return Either.right(e.getErrorCodes());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            session.rollback();
            return Either.right(ErrorCodes.SYSTEM_ERROR);
        } finally {
            log.info("Done UseCase {}", getClass().getSimpleName());
        }
    }

    /**
     * Returns customizable {@code SessionInformation} to manage the transaction of this execution.
     * <p>
     * Default value is {@link SessionInformation#DEFAULT}.
     *
     * @return custom {@code SessionInformation}
     *
     * @see Session
     * @see SessionPropagation
     */
    protected SessionInformation customizeSessionInformation() {
        return SessionInformation.DEFAULT;
    }

    protected void validateInput(UseCaseValidator validator, I input) {
        // To be implemented by actual use case implementation
    }

    protected abstract O internalExecute(UseCaseValidator validator, I input);

    @Autowired
    public void setTransactionManager(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }
}
