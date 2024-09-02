package com.cplerings.core.common.pair;

import java.util.Objects;
import java.util.function.Consumer;

import lombok.Getter;

@Getter
abstract class AbstractPair<L, R> implements Pair<L, R> {

    private final L left;
    private final R right;

    public AbstractPair(L left, R right) {
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
    @SuppressWarnings("unchecked")
    public final <S extends Pair<L, R>> S ifLeft(Consumer<L> consumer) {
        Objects.requireNonNull(consumer, "Consumer must not be null");
        if (isLeft()) {
            consumer.accept(left);
        }
        return (S) this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public final <S extends Pair<L, R>> S ifRight(Consumer<R> consumer) {
        Objects.requireNonNull(consumer, "Consumer must not be null");
        if (isRight()) {
            consumer.accept(right);
        }
        return (S) this;
    }
}
