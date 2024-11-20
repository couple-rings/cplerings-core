package com.cplerings.core.api.diamond.request;

import lombok.Builder;

@Builder
public record CreateDiamondRequest(Long giaDocumentId, String giaReportNumber, Long diamondSpecificationId,
                                   Long branchId) {

}
