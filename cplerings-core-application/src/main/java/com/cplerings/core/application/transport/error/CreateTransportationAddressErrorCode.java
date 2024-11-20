package com.cplerings.core.application.transport.error;

import com.cplerings.core.application.shared.errorcode.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CreateTransportationAddressErrorCode implements ErrorCode {

    ADDRESS_REQUIRED("002", "createTransportationAddress.error.addressRequired", Type.VALIDATION),
    RECEIVER_PHONE_NUMBER_REQUIRED("003", "createTransportationAddress.error.receiverPhoneRequired", Type.VALIDATION),
    RECEIVER_NAME_REQUIRED("004", "createTransportationAddress.error.receiverNameRequired", Type.VALIDATION),
    DISTRICT_CODE_REQUIRED("005", "createTransportationAddress.error.districtCodeRequired", Type.VALIDATION),
    DISTRICT_REQUIRED("006", "createTransportationAddress.error.districtRequired", Type.VALIDATION),
    WARD_REQUIRED("007", "createTransportationAddress.error.wardRequired", Type.VALIDATION),
    WARD_CODE_REQUIRED("008", "createTransportationAddress.error.wardCodeRequired", Type.VALIDATION),
    CUSTOMER_ID_REQUIRED("008", "createTransportationAddress.error.customerIdRequired", Type.VALIDATION),
    CUSTOMER_ID_WRONG_POSITIVE_NUMBER("008", "createTransportationAddress.error.customerIdWrongPositiveNumber", Type.VALIDATION),
    INVALID_CUSTOMER_ID("008", "createTransportationAddress.error.invalidCustomer", Type.VALIDATION),
    ;

    private final String code;
    private final String descriptionLocale;
    private final ErrorCode.Type type;
}
