package com.cplerings.core.domain.shared.valueobject;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

@Embeddable
public final class Weight {

    @Column(name = "weight", nullable = false, precision = 10, scale = 2)
    private BigDecimal weight;

    public Weight() {
        this.weight = sanitizeWeightValue(null);
    }

    private static BigDecimal sanitizeWeightValue(BigDecimal weight) {
        return Objects.requireNonNullElse(weight, BigDecimal.ZERO)
                .setScale(2, RoundingMode.HALF_EVEN);
    }

    private Weight(BigDecimal weight) {
        this.weight = sanitizeWeightValue(weight);
    }

    public static Weight create(BigDecimal weightValue) {
        return new Weight(weightValue);
    }

    public BigDecimal getWeightValue() {
        return weight;
    }

    private void setWeightValue(BigDecimal weight) {
        this.weight = sanitizeWeightValue(weight);
    }
}
