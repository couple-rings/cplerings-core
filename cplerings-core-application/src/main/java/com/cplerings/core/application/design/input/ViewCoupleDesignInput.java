package com.cplerings.core.application.design.input;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ViewCoupleDesignInput {

    private int page;
    private int pageSize;
    private Long collectionId;
    private Long metalSpecificationId;
    private Double minPrice ;
    private Double maxPrice;
}
