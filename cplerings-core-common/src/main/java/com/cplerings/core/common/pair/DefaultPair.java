package com.cplerings.core.common.pair;

import java.util.Objects;

public final class DefaultPair<L, R> extends AbstractPair<L, R> {

    public DefaultPair(L left, R right) {
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
        Pair<L, R> pair = (Pair<L, R>) obj;
        return Objects.equals(getLeft(), pair.getLeft())
                && Objects.equals(getRight(), pair.getRight());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLeft(), getRight());
    }
}
