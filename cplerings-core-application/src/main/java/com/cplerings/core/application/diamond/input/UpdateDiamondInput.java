package com.cplerings.core.application.diamond.input;

import lombok.Builder;

@Builder
public record UpdateDiamondInput(Long diamondId, String giaReportNumber, Long giaDocumentId, Long diamondSpecificationId) {
}
