package com.cplerings.core.common.pair;

import java.util.function.Consumer;

public interface Pair<L, R> {

    L getLeft();

    R getRight();

    boolean isLeft();

    boolean isRight();

    <S extends Pair<L, R>> S ifLeft(Consumer<L> consumer);

    <S extends Pair<L, R>> S ifRight(Consumer<R> consumer);

    class Builder<L, R> {

        private L left;
        private R right;

        private Builder() {
        }

        public Builder<L, R> left(L left) {
            this.left = left;
            return this;
        }

        public Builder<L, R> right(R right) {
            this.right = right;
            return this;
        }

        public Pair<L, R> defaultBuild() {
            return new DefaultPair<>(left, right);
        }
    }

    static <L, R> Builder<L, R> builder() {
        return new Builder<>();
    }
}
