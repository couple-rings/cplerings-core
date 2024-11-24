package com.cplerings.core.api.design.request;

import com.cplerings.core.api.shared.AbstractPaginatedRequest;
import com.cplerings.core.application.shared.entity.design.ADesignStatus;
import com.cplerings.core.common.pagination.FilterableByPrice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ViewDesignCouplesRequest extends AbstractPaginatedRequest implements FilterableByPrice {

    private Long collectionId;
    private Long metalSpecificationId;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private ADesignStatus status;
}
