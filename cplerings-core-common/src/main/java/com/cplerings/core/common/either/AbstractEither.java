package com.cplerings.core.common.either;

import lombok.Getter;

import java.util.Objects;
import java.util.function.Consumer;

@Getter
abstract class AbstractEither<L, R> implements Either<L, R> {

    private final L left;
    private final R right;

    public AbstractEither(L left, R right) {
        if ((left == null) && (right == null) || (left != null) && (right != null)) {
            throw new IllegalArgumentException("Either left or right must be non-null and other as null");
        }
        this.left = left;
        this.right = right;
    }

    @Override
    public final L getLeft() {
        return left;
    }

    @Override
    public final R getRight() {
        return right;
    }

    @Override
    public final boolean isLeft() {
        return (left != null);
    }

    @Override
    public final boolean isRight() {
        return (right != null);
    }

    @Override
    public final <S extends Either<L, R>> S ifLeft(Consumer<L> consumer) {
        Objects.requireNonNull(consumer, "Consumer must not be null");
        if (isLeft()) {
            consumer.accept(left);
        }
        return self();
    }

    @SuppressWarnings("unchecked")
    protected final <S extends Either<L, R>> S self() {
        return (S) this;
    }

    @Override
    public final <S extends Either<L, R>> S ifRight(Consumer<R> consumer) {
        Objects.requireNonNull(consumer, "Consumer must not be null");
        if (isRight()) {
            consumer.accept(right);
        }
        return self();
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof final Either<?, ?> that)) {
            return false;
        }
        return Objects.equals(left, that.getLeft())
                && Objects.equals(right, that.getRight());
    }
}
