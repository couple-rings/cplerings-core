package com.cplerings.core.application.transport.error;

import com.cplerings.core.application.shared.errorcode.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ViewTransportationOrdersErrorCode implements ErrorCode{

    NOT_DONE_CUSTOM_ORDER("002", "viewTransportationOrders.error.oneOfTheCustomOrderNotDone", ErrorCode.Type.BUSINESS),
    ;
    private final String code;
    private final String descriptionLocale;
    private final ErrorCode.Type type;
}
