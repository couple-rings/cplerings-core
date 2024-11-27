package com.cplerings.core.application.crafting.error;

import com.cplerings.core.application.shared.errorcode.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CraftingRingErrorCode implements ErrorCode {

    BRANCH_ID_REQUIRED("002", "craftingRing.error.branchIdRequired", Type.VALIDATION),
    CUSTOMER_ID_REQUIRED("003", "craftingRing.error.customerIdRequired", Type.VALIDATION),
    SELF_REQUIRED("004", "craftingRing.error.selfRequired", Type.VALIDATION),
    PARTNER_REQUIRED("005", "craftingRing.error.partnerRequired", Type.VALIDATION),
    DIAMOND_SPEC_ID_REQUIRED("006", "craftingRing.error.diamondSpecIdRequired", Type.VALIDATION),
    DESIGN_ID_REQUIRED("007", "craftingRing.error.designIdRequired", Type.VALIDATION),
    METAL_SPEC_ID_REQUIRED("008", "craftingRing.error.metalSpecId", Type.VALIDATION),
    FINGER_SIZE_REQUIRED("009", "craftingRing.error.fingerSizeRequired", Type.VALIDATION),
    SPOUSE_ID_REQUIRED("010", "craftingRing.error.spouseIdRequired", Type.VALIDATION),
    DIAMOND_SPEC_ID_WRONG_INTEGER("011", "craftingRing.error.diamondSpecIdWrongInteger", Type.VALIDATION),
    DESIGN_ID_WRONG_INTEGER("012", "craftingRing.error.designIdWrongInteger", Type.VALIDATION),
    METAL_SPEC_ID_WRONG_INTEGER("013", "craftingRing.error.metalSpecWrongInteger", Type.VALIDATION),
    FINGER_SIZE_WRONG_INTEGER("014", "craftingRing.error.fingerSizeWrongInteger", Type.VALIDATION),
    SPOUSE_ID_WRONG_INTEGER("015", "craftingRing.error.spouseIdWrongInteger", Type.VALIDATION),
    CUSTOMER_NOT_FOUND("016", "craftingRing.error.customerNotFound", Type.BUSINESS),
    BRANCH_NOT_FOUND("017", "craftingRing.error.branchNotFound", Type.BUSINESS),
    SPOUSE_NOT_FOUND("018", "craftingRing.error.spouseNotFound", Type.BUSINESS),
    DESIGN_NOT_FOUND("019", "craftingRing.error.designNotFound", Type.BUSINESS),
    METAL_SPEC_NOT_FOUND("020", "craftingRing.error.metalSpecNotFound", Type.BUSINESS),
    DIAMOND_SPEC_NOT_FOUND("021", "craftingRing.error.diamondSpecNotFound", Type.BUSINESS),
    ;

    private final String code;
    private final String descriptionLocale;
    private final Type type;
}
