package com.cplerings.core.application.shared.transaction;

public interface Session {

    void commit();

    void rollback();
}
