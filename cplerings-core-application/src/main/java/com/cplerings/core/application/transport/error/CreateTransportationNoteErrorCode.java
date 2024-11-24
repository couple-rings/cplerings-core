package com.cplerings.core.application.transport.error;

import com.cplerings.core.application.shared.errorcode.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum CreateTransportationNoteErrorCode implements ErrorCode {

    TRANSPORTATION_ORDER_ID_REQUIRED("002", "createTransportationNote.error.transportationOrderIdRequired", Type.VALIDATION),
    NOTE_REQUIRED("003", "createTransportationNote.error.noteRequired", Type.VALIDATION),
    TRANSPORTATION_ORDER_ID_WRONG_INTEGER("004", "createTransportationNote.error.transportationOrderIdWrongInteger", Type.VALIDATION),
    TRANSPORTATION_ORDER_NOT_FOUND("005", "createTransportationNote.error.transportationOrderNotFound", Type.BUSINESS),
    WRONG_STATUS_FOR_CREATE("006", "createTransportationNote.error.wrongStatusForCreateNote", Type.BUSINESS),
    ;

    private final String code;
    private final String descriptionLocale;
    private final ErrorCode.Type type;
}
