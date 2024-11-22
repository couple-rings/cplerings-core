package com.cplerings.core.application.agreement.input;

import lombok.Builder;

@Builder
public record SignAgreementInput(Long agreementId, String mainName, Long mainSignatureId, String partnerName,
                                 Long partnerSignatureId) {

}
