package com.cplerings.core.common.fluentapi;

public abstract class AbstractSelf<S extends AbstractSelf<S>> {

    @SuppressWarnings("unchecked")
    protected final S self() {
        return (S) this;
    }
}
