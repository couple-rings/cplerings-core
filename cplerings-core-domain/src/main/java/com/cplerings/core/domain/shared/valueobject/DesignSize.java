package com.cplerings.core.domain.shared.valueobject;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Getter
@Setter(AccessLevel.PRIVATE)
@Embeddable
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class DesignSize {

    private static final int MIN_SIZE = 16;
    private static final int MAX_SIZE = 60;

    @Column(name = "size", nullable = false)
    private Integer size;

    public DesignSize() {
        this.size = MIN_SIZE;
    }

    public static DesignSize create(Integer size) {
        if (size == null || size < MIN_SIZE) {
            return new DesignSize(MIN_SIZE);
        }
        if (size > MAX_SIZE) {
            return new DesignSize(MAX_SIZE);
        }
        return new DesignSize(size);
    }
}
