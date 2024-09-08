package com.cplerings.core.application.shared.transaction;

import org.springframework.transaction.TransactionDefinition;

/**
 * @see TransactionDefinition
 */
public enum SessionPropagation {

    REQUIRED, SUPPORTS, MANDATORY, REQUIRES_NEW, NOT_SUPPORTED, NEVER, NESTED
}
