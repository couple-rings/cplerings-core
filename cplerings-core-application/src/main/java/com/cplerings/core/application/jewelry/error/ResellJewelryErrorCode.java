package com.cplerings.core.application.jewelry.error;

import com.cplerings.core.application.shared.errorcode.ErrorCode;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum ResellJewelryErrorCode implements ErrorCode {

    JEWELRY_ID_REQUIRED("002", "resellJewelry.error.jewelryIdRequired", Type.VALIDATION),
    CUSTOMER_ID_REQUIRED("003", "resellJewelry.error.customerIdRequired", Type.VALIDATION),
    PROOF_IMAGE_ID_REQUIRED("004", "resellJewelry.error.proofImageIdRequired", Type.VALIDATION),
    PAYMENT_METHOD_REQUIRED("005", "resellJewelry.error.paymentMethodRequired", Type.VALIDATION),
    NOTE_REQUIRED("006", "resellJewelry.error.noteRequired", Type.VALIDATION),
    INVALID_JEWELRY_ID("007", "resellJewelry.error.invalidJewelryId", Type.VALIDATION),
    INVALID_CUSTOMER_ID("008", "resellJewelry.error.invalidCustomerId", Type.VALIDATION),
    INVALID_PROOF_IMAGE_ID("009", "resellJewelry.error.invalidProofImageId", Type.VALIDATION),
    JEWELRY_NOT_FOUND("010", "resellJewelry.error.jewelryNotFound", Type.BUSINESS),
    JEWELRY_NOT_PURCHASED("011", "resellJewelry.error.jewelryNotPurchased", Type.BUSINESS),
    CUSTOMER_NOT_FOUND("012", "resellJewelry.error.customerNotFound", Type.BUSINESS),
    PROOF_IMAGE_NOT_FOUND("013", "resellJewelry.error.proofImageNotFound", Type.BUSINESS),
    ;

    private final String code;
    private final String descriptionLocale;
    private final ErrorCode.Type type;
}
