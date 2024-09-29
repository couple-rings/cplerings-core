package com.cplerings.core.application.shared.usecase;

import com.cplerings.core.application.shared.errorcode.ErrorCodes;
import com.cplerings.core.application.shared.transaction.Session;
import com.cplerings.core.application.shared.transaction.SessionInformation;
import com.cplerings.core.application.shared.transaction.SessionPropagation;
import com.cplerings.core.application.shared.transaction.TransactionManager;
import com.cplerings.core.common.either.Either;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractNewUseCase<I, O> implements UseCase<I, O> {

    private TransactionManager transactionManager;

    @Override
    public final Either<O, ErrorCodes> execute(I input) {
        final SessionInformation sessionInformation = customizeSessionInformation();
        final Session session = transactionManager.createSession(sessionInformation);
        try {
            final UseCaseValidator validator = new UseCaseValidator();
            validateInput(validator, input);
            validator.clearAndThrowErrorCodes();
            final O output = internalExecute(validator, input);
            session.commit();
            return Either.left(output);
        } catch (ErrorCodeException e) {
            session.rollback();
            return Either.right(e.getErrorCodes());
        } catch (Exception e) {
            session.rollback();
            return Either.right(ErrorCodes.SYSTEM_ERROR);
        }
    }

    protected void validateInput(UseCaseValidator validator, I input) {
        // To be implemented by actual use case implementation
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

    protected abstract O internalExecute(UseCaseValidator validator, I input);

    @Autowired
    public void setTransactionManager(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }
}
