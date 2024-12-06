package com.cplerings.core.application.order.error;

import com.cplerings.core.application.shared.errorcode.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CancelStandardOrderErrorCode implements ErrorCode {

    STANDARD_ORDER_NOT_FOUND("002", "cancelStandardOrder.error.standardOrderNotFound", Type.BUSINESS),
    STANDARD_ORDER_WRONG_STATUS_FOR_CANCEL("003", "cancelStandardOrder.error.standardOrderWrongStatusForCancel", Type.BUSINESS),
    ;

    private final String code;
    private final String descriptionLocale;
    private final ErrorCode.Type type;
}
