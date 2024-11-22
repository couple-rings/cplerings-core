package com.cplerings.core.api.agreement.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignAgreementRequest {

    private Long agreementId;
    private String mainName;
    private Long mainSignatureId;
    private String partnerName;
    private Long partnerSignatureId;
}
