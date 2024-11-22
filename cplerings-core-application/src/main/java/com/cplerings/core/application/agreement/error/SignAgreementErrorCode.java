package com.cplerings.core.application.agreement.error;

import com.cplerings.core.application.shared.errorcode.ErrorCode;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum SignAgreementErrorCode implements ErrorCode {

    MAIN_NAME_REQUIRED("002", "signAgreement.error.mainNameRequired", Type.VALIDATION),
    PARTNER_NAME_REQUIRED("003", "signAgreement.error.partnerNameRequired", Type.VALIDATION),
    MAIN_SIGNATURE_ID_REQUIRED("004", "signAgreement.error.mainSignatureIdRequired", Type.VALIDATION),
    PARTNER_SIGNATURE_ID_REQUIRED("005", "signAgreement.error.partnerSignatureIdRequired", Type.VALIDATION),
    INVALID_MAIN_SIGNATURE_ID("006", "signAgreement.error.invalidMainSignatureId", Type.VALIDATION),
    INVALID_PARTNER_SIGNATURE_ID("007", "signAgreement.error.invalidPartnerSignatureId", Type.VALIDATION),
    AGREEMENT_ID_REQUIRED("010", "signAgreement.error.agreementIdRequired", Type.VALIDATION),
    INVALID_AGREEMENT_ID("011", "signAgreement.error.invalidAgreementId", Type.VALIDATION),
    AGREEMENT_NOT_FOUND("012", "signAgreement.error.agreementNotFound", Type.VALIDATION),
    TWO_SIGNATURES_ARE_THE_SAME("013", "signAgreement.error.twoSignaturesAreTheSame", Type.VALIDATION),
    SIGNATURES_NOT_FOUND("014", "signAgreement.error.signaturesNotFound", Type.VALIDATION),
    AGREEMENT_ALREADY_SIGNED("015", "signAgreement.error.agreementAlreadySigned", Type.BUSINESS),
    NOT_SAME_CUSTOMER_TO_SIGN("016", "signAgreement.error.notSameCustomerToSign", Type.BUSINESS),
    ;

    private final String code;
    private final String descriptionLocale;
    private final Type type;
}
