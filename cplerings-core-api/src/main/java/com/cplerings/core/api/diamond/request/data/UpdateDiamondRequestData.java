package com.cplerings.core.api.diamond.request.data;

import lombok.Builder;

@Builder
public record UpdateDiamondRequestData(String giaReportNumber, Long giaDocumentId, Long diamondSpecificationId) {
}
