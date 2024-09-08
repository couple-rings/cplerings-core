package com.cplerings.core.application.shared.transaction;

public interface TransactionManager {

    Session createSession(SessionInformation sessionInformation);
}
