package com.cplerings.core.common.pagination;

import java.math.BigDecimal;

public interface FilterableByPrice {

    BigDecimal getMinPrice();

    void setMinPrice(BigDecimal minPrice);

    BigDecimal getMaxPrice();

    void setMaxPrice(BigDecimal maxPrice);
}
