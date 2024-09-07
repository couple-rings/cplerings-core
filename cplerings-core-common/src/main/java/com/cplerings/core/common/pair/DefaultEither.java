package com.cplerings.core.common.pair;

import java.util.Objects;

public final class DefaultEither<L, R> extends AbstractEither<L, R> {

    public DefaultEither(L left, R right) {
        super(left, right);
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Either<L, R> either = (Either<L, R>) obj;
        return Objects.equals(getLeft(), either.getLeft())
                && Objects.equals(getRight(), either.getRight());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLeft(), getRight());
    }
}
