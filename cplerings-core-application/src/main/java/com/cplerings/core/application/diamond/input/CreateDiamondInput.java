package com.cplerings.core.application.diamond.input;

import lombok.Builder;

@Builder
public record CreateDiamondInput(Long giaDocumentId, String giaReportNumber, Long diamondSpecificationId,
                                 Long branchId) {

}
