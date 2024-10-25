package com.cplerings.core.common.pagination;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PaginationUtils {

    public static <T extends FilterableByPrice> void sanitizeFilterByPrice(T filterable) {
        if (filterable == null) {
            return;
        }
        if (filterable.getMinPrice() == null || filterable.getMinPrice().compareTo(BigDecimal.valueOf(-1)) < 0) {
            filterable.setMinPrice(BigDecimal.valueOf(-1));
        }
        if (filterable.getMaxPrice() == null || filterable.getMaxPrice().compareTo(BigDecimal.valueOf(-1)) < 0) {
            filterable.setMaxPrice(BigDecimal.valueOf(-1));
        }
    }

    public static long getOffset(int page, int pageSize) {
        return ((long) page * pageSize);
    }
}
