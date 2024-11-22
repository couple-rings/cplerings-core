package com.cplerings.core.application.agreement.output;

import com.cplerings.core.application.shared.entity.agreement.AAgreement;

import lombok.Builder;

@Builder
public record SignAgreementOutput(AAgreement agreement) {

}
