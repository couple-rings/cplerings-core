package com.cplerings.core.application.shared.mapper;

import com.cplerings.core.common.mapper.SpringMapperConfiguration;
import com.cplerings.core.domain.shared.valueobject.Weight;

import org.mapstruct.Mapper;

import java.math.BigDecimal;
import java.util.Objects;

@Mapper(config = SpringMapperConfiguration.class)
public interface WeightMapper {

    default BigDecimal toWeightValue(Weight weight) {
        if (weight == null) {
            return BigDecimal.ZERO;
        }
        return weight.getWeightValue();
    }

    default Weight toWeight(BigDecimal weightValue) {
        return Weight.create(Objects.requireNonNullElse(weightValue, BigDecimal.ZERO));
    }
}
