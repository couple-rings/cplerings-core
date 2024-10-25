package com.cplerings.core.application.shared.mapper;

import com.cplerings.core.common.mapper.SpringMapperConfiguration;
import com.cplerings.core.domain.shared.valueobject.Money;

import org.mapstruct.Mapper;

import java.math.BigDecimal;

@Mapper(config = SpringMapperConfiguration.class)
public interface MoneyMapper {

    default Money toMoney(Long amount) {
        if (amount == null) {
            return Money.create(BigDecimal.ZERO);
        }
        return Money.create(BigDecimal.valueOf(amount));
    }

    default BigDecimal toAmount(Money money) {
        if (money == null) {
            return BigDecimal.ZERO;
        }
        return money.getAmount();
    }
}
