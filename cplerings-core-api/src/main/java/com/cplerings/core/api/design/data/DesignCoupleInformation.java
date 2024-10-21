package com.cplerings.core.api.design.data;

import lombok.Builder;

@Builder
public record DesignCoupleInformation(String description, String name, String imageUrl) {

}
