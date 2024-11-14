package com.cplerings.core.application.transport.error;

import com.cplerings.core.application.shared.errorcode.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UpdateTransportationOrdersToOngoingErrorCode implements ErrorCode {

    LIST_TRANSPORTATION_ORDER_IDS_REQUIRED("002", "updateTransportOrdersToOngoing.error.listTransportationIdsRequired", Type.VALIDATION),
    ONE_OF_THE_IDS_IS_INVALID("003", "updateTransportOrdersToOngoing.error.oneOfTheIdsIsInvalid", Type.BUSINESS),
    ONE_OF_THE_TRANSPORTATIONS_IS_NOT_WAITING("002", "updateTransportOrdersToOngoing.error.oneOfTheIdsIsNotWaiting", Type.BUSINESS),
    ;
    private final String code;
    private final String descriptionLocale;
    private final ErrorCode.Type type;
}
