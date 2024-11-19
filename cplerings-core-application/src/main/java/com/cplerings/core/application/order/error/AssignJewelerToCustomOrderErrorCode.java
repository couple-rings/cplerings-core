package com.cplerings.core.application.order.error;

import com.cplerings.core.application.shared.errorcode.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AssignJewelerToCustomOrderErrorCode implements ErrorCode {

    CUSTOM_ORDER_ID_REQUIRED("002", "assignJewelerToCustomOrder.error.customOrderIdRequired", Type.VALIDATION),
    CUSTOM_ORDER_ID_WRONG_POSITIVE_NUMBER("003", "assignJewelerToCustomOrder.error.customOrderIdWrongPositiveNumber", Type.VALIDATION),
    JEWELER_ID_REQUIRED("004", "assignJewelerToCustomOrder.error.jewelerIdRequired", Type.VALIDATION),
    JEWELER_ID_WRONG_POSITIVE_NUMBER("005", "assignJewelerToCustomOrder.error.jewelerIdWrongPositiveNumber", Type.VALIDATION),
    INVALID_JEWELER_ID("006", "assignJewelerToCustomOrder.error.invalidJewelerId", Type.BUSINESS),
    NOT_A_JEWELER("007", "assignJewelerToCustomOrder.error.notAJeweler", Type.BUSINESS),
    INVALID_CUSTOM_ORDER_ID("008", "assignJewelerToCustomOrder.error.invalidCustomOrderId", Type.BUSINESS),
    INVALID_CUSTOM_ORDER_STATUS_FOR_ASSIGNING("009", "assignJewelerToCustomOrder.error.invalidCustomOrderStatusForAssign", Type.BUSINESS),
    CUSTOM_ORDER_HAS_BEEN_ASSIGNED("010", "assignJewelerToCustomOrder.error.customOrderHasBeenAssigned", Type.BUSINESS),
    ;

    private final String code;
    private final String descriptionLocale;
    private final ErrorCode.Type type;
}
