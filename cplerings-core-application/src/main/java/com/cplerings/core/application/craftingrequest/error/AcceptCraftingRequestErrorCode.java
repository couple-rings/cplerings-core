package com.cplerings.core.application.craftingrequest.error;

import com.cplerings.core.application.shared.errorcode.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AcceptCraftingRequestErrorCode implements ErrorCode {

    CRAFTING_REQUEST_ID_WRONG_POSITIVE_INTEGER("002", "crafting.request.craftingRequestIdWrongPositiveInteger", Type.VALIDATION),
    STATUS_REQUIRED("003", "crafting.request.statusRequired", Type.VALIDATION),
    COMMENT_REQUIRED("004", "crafting.request.commentRequired", Type.VALIDATION),
    BRANCH_ID_REQUIRED("005", "crafting.request.branchIdRequired", Type.VALIDATION),
    INVALID_CRAFTING_REQUEST_ID("006", "crafting.request.craftingRequestIdIsNotReal", Type.BUSINESS),
    INVALID_BRANCH_ID("007", "crafting.request.branchIdIsNotReal", Type.BUSINESS),
    WRONG_STATUS("008", "crafting.request.wrongStatus", Type.BUSINESS),
    INVALID_CRAFTING_REQUEST_STATUS("009", "crafting.request.invalidCraftingStatus", Type.BUSINESS),
    ;

    private final String code;
    private final String descriptionLocale;
    private final Type type;
}
