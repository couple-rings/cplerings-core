package com.cplerings.core.domain.shared.valueobject;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

@Embeddable
public final class MetalWeight {

    public static MetalWeight create(BigDecimal weightValue) {
        return new MetalWeight(weightValue);
    }

    private static BigDecimal sanitizeWeightValue(BigDecimal weight) {
        return Objects.requireNonNullElse(weight, BigDecimal.ZERO)
                .setScale(2, RoundingMode.HALF_EVEN);
    }

    @Column(name = "metal_weight", nullable = false, precision = 10, scale = 2)
    private BigDecimal weight;

    public MetalWeight() {
        this.weight = sanitizeWeightValue(null);
    }

    private MetalWeight(BigDecimal weight) {
        this.weight = sanitizeWeightValue(weight);
    }

    public BigDecimal getWeightValue() {
        return weight;
    }

    private void setWeightValue(BigDecimal weight) {
        this.weight = sanitizeWeightValue(weight);
    }
}
