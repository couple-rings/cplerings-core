package com.cplerings.core.application.contract.error;

import com.cplerings.core.application.shared.errorcode.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DetermineContractErrorCode implements ErrorCode {

    DOCUMENT_ID_REQUIRED("002", "contract.error.documentIdRequired", ErrorCode.Type.VALIDATION),
    SIGNATURE_REQUIRED("003", "contract.error.signatureRequired", ErrorCode.Type.VALIDATION),
    SIGNED_DATE_REQUIRED("004", "contract.error.signedDateRequired", ErrorCode.Type.VALIDATION),
    DOCUMENT_ID_WRONG_POSITIVE_INTEGER("005", "contract.error.documentIdWrongPositiveInteger", ErrorCode.Type.VALIDATION),
    CONTRACT_ID_WRONG_POSITIVE_INTEGER("006", "contract.error.contractIdWrongPositiveInteger", ErrorCode.Type.VALIDATION),
    INVALID_CONTRACT_ID("007", "contract.error.invalidContractId", ErrorCode.Type.VALIDATION),
    INVALID_DOCUMENT_ID("008", "contract.error.invalidDocumentId", ErrorCode.Type.VALIDATION),
    ;

    private final String code;
    private final String descriptionLocale;
    private final ErrorCode.Type type;
}
