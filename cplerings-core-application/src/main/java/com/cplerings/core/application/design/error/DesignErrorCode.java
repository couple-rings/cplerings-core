package com.cplerings.core.application.design.error;

import com.cplerings.core.application.shared.errorcode.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DesignErrorCode implements ErrorCode {

    INVALID_METAL_SPECIFICATION_ID("002", "design.error.invalidMetalSpecId", Type.VALIDATION),
    INVALID_COLLECTION_ID("003", "design.error.invalidCollectionId", Type.VALIDATION),
    INVALID_PAGE("004", "design.error.invalidPage", Type.VALIDATION),
    INVALID_PAGE_SIZE("005", "design.error.invalidPageSize", Type.VALIDATION),
    MIN_PRICE_LARGER_EQUAL_MAX_PRICE("006", "design.error.minPriceLargerOrEqualMaxPrice", Type.VALIDATION),
    CUSTOMER_NOT_FOUND("007", "design.error.customerNotFound", Type.BUSINESS),
    CUSTOMER_NOT_ACTIVE("008", "design.error.customerNotActive", Type.BUSINESS),
    EXIST_UNUSED_DESIGN_SESSION("010", "design.error.existUnusedDesignSession", Type.BUSINESS),
    PAYMENT_RECEIVER_REQUIRED("011", "design.error.paymentReceiverRequired", Type.VALIDATION),
    INCORRECT_PAYMENT_RECEIVER("012", "design.error.incorrectPaymentReceiver", Type.VALIDATION),
    INVALID_ACCOUNT_ID("013", "design.error.invalidAccountId", Type.BUSINESS),
    ACCOUNT_WITH_ID_NOT_FOUND("014", "design.error.accountWithIdNotFound", Type.BUSINESS),
    ACCOUNT_NOT_ACTIVE("015", "design.error.accountNotActive", Type.BUSINESS),
    ;

    private final String code;
    private final String descriptionLocale;
    private final ErrorCode.Type type;
}
