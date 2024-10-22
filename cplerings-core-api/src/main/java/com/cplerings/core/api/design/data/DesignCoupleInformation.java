package com.cplerings.core.api.design.data;

import com.cplerings.core.application.design.output.DesignCollectionInformation;
import com.cplerings.core.application.design.output.MetalSpecificationInformation;

import lombok.Builder;

@Builder
public record DesignCoupleInformation(String description, String name, String imageUrl, MetalSpecificationInformation metalSpecification, DesignCollectionInformation designCollection) {

}
