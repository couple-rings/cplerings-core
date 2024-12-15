package com.cplerings.core.api.jewelry.request;

import lombok.Builder;

@Builder
public record GetJewelryByProductNoRequest(String productNo) {
}
