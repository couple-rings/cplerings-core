package com.cplerings.core.domain.shared.valueobject;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

@Getter
@Setter(AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Embeddable
public final class Money {

    @Column(name = "amount", nullable = false, precision = 12, scale = 3)
    private BigDecimal amount;

    public Money() {
        this.amount = sanitizeMoney(BigDecimal.ZERO);
    }

    private static BigDecimal sanitizeMoney(BigDecimal amount) {
        return Objects.requireNonNullElse(amount, BigDecimal.ZERO)
                .setScale(3, RoundingMode.HALF_EVEN);
    }

    public Money divide(BigDecimal divisor) {
        final BigDecimal current = this.amount;
        final BigDecimal sanitizedDivisor = sanitizeMoney(divisor);
        return Money.create(current.divide(sanitizedDivisor, RoundingMode.HALF_EVEN));
    }

    public Money multiply(BigDecimal product) {
        final BigDecimal current = this.amount;
        final BigDecimal sanitizedDivisor = sanitizeMoney(product);
        return Money.create(current.multiply(sanitizedDivisor));
    }

    public static Money create(BigDecimal amount) {
        return new Money(sanitizeMoney(amount));
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(amount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if ((o == null) || (getClass() != o.getClass())) {
            return false;
        }
        final Money money = (Money) o;
        return Objects.equals(amount, money.amount);
    }
}
