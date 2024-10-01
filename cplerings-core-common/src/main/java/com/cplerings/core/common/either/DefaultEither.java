package com.cplerings.core.common.either;

public final class DefaultEither<L, R> extends AbstractEither<L, R> {

    public DefaultEither(L left, R right) {
        super(left, right);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }
}
