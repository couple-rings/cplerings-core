package com.cplerings.core.application.design.input;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ViewCoupleDesignInput {

    private Long collectionId;
    private Integer metalSpecificationId;
    private Double minPrice ;
    private Double maxPrice;
}
