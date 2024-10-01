package com.cplerings.core.common.either;

import java.util.function.Consumer;

public interface Either<L, R> {

    static <L, R> Builder<L, R> builder() {
        return new Builder<>();
    }

    static <L, R> Either<L, R> left(L left) {
        return new DefaultEither<>(left, null);
    }

    static <L, R> Either<L, R> right(R right) {
        return new DefaultEither<>(null, right);
    }

    L getLeft();

    R getRight();

    boolean isLeft();

    boolean isRight();

    <S extends Either<L, R>> S ifLeft(Consumer<L> consumer);

    <S extends Either<L, R>> S ifRight(Consumer<R> consumer);

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

        public Either<L, R> build() {
            return new DefaultEither<>(left, right);
        }
    }
}
