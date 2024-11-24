package com.cplerings.core.application.transport.error;

import com.cplerings.core.application.shared.errorcode.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UpdateImageDeliveryErrorCode implements ErrorCode {

    TRANSPORTATION_ORDER_ID_REQUIRED("002", "updateImageDelivery.error.transportationOrderIdRequired", Type.VALIDATION),
    IMAGE_ID_REQUIRED("003", "updateImageDelivery.error.imageIdRequired", Type.VALIDATION),
    TRANSPORTATION_ORDER_WRONG_INTEGER("004", "updateImageDelivery.error.transportationOrderIdWrongInteger", Type.VALIDATION),
    IMAGE_ID_WRONG_INTEGER("005", "updateImageDelivery.error.imageIdWrongInteger", Type.VALIDATION),
    TRANSPORTATION_ORDER_NOT_FOUND("006", "updateImageDelivery.error.transportationOrderNotFound", Type.BUSINESS),
    IMAGE_NOT_FOUND("007", "updateImageDelivery.error.imageNotFound", Type.BUSINESS),
    WRONG_STATUS("008", "updateImageDelivery.error.wrongStatusForUpdate", Type.BUSINESS),
    ;

    private final String code;
    private final String descriptionLocale;
    private final ErrorCode.Type type;
}
