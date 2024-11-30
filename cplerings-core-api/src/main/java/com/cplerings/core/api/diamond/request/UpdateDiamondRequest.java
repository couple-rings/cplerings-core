package com.cplerings.core.api.diamond.request;

import lombok.Builder;

@Builder
public record UpdateDiamondRequest(Long diamondId, String giaReportNumber, Long giaDocumentId, Long diamondSpecificationId) {
}
