package com.cplerings.core.application.diamond.error;

import com.cplerings.core.application.shared.errorcode.ErrorCode;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum CreateDiamondErrorCode implements ErrorCode {

    GIA_DOCUMENT_ID_REQUIRED("002", "createDiamond.error.giaDocumentIdRequired", Type.VALIDATION),
    DIAMOND_SPECIFICATION_ID_REQUIRED("003", "createDiamond.error.diamondSpecificationIdRequired", Type.VALIDATION),
    BRANCH_ID_REQUIRED("004", "createDiamond.error.brandIdRequired", Type.VALIDATION),
    GIA_REPORT_NUMBER_REQUIRED("005", "createDiamond.error.giaReportNumberRequired", Type.VALIDATION),
    INVALID_GIA_DOCUMENT_ID("006", "createDiamond.error.invalidGIADocumentId", Type.VALIDATION),
    INVALID_DIAMOND_SPECIFICATION_ID("007", "createDiamond.error.invalidDiamondSpecificationId", Type.VALIDATION),
    INVALID_BRANCH_ID("008", "createDiamond.error.invalidBranchId", Type.VALIDATION),
    GIA_DOCUMENT_NOT_FOUND("009", "createDiamond.error.giaDocumentNotFound", Type.VALIDATION),
    DIAMOND_SPECIFICATION_NOT_FOUND("010", "createDiamond.error.diamondSpecificationNotFound", Type.VALIDATION),
    BRANCH_NOT_FOUND("011", "createDiamond.error.brandNotFound", Type.VALIDATION),
    ;

    private final String code;
    private final String descriptionLocale;
    private final ErrorCode.Type type;
}
