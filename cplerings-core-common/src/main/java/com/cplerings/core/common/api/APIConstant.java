package com.cplerings.core.common.api;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@SuppressWarnings("java:S1075")
public final class APIConstant {

    public static final String APPLICATION_JSON = "application/json";

    /*
        Dev' paths
     */
    public static final String DEV_PATH = "/dev";
    public static final String DEV_HELLO_PATH = DEV_PATH + "/hello";
    public static final String DEV_TOKEN_PATH = DEV_PATH + "/token";
    public static final String DEV_PAYMENTS_PATH = DEV_PATH + "/payments";
    public static final String DEV_VNPAY_PATH = DEV_PAYMENTS_PATH + "/vnpay";

    /*
        Accounts' paths
     */
    public static final String ACCOUNTS_PATH = "/accounts";
    public static final String CUSTOMER_PATH = ACCOUNTS_PATH + "/customer";
    public static final String VERIFY_CUSTOMER_PATH = CUSTOMER_PATH + "/verification";
    public static final String RESEND_CUSTOMER_VERIFICATION_CODE_PATH = VERIFY_CUSTOMER_PATH + "/resend";
    public static final String REGISTER_CUSTOMER_PATH = CUSTOMER_PATH + "/registration";
    public static final String CUSTOMER_PASSWORD_PATH = CUSTOMER_PATH + "/password";
    public static final String REQUEST_RESET_PASSWORD_PATH = CUSTOMER_PASSWORD_PATH + "/forget";
    public static final String RESET_PASSWORD_PATH = CUSTOMER_PASSWORD_PATH + "/reset";
    public static final String ACCOUNT_PATH = ACCOUNTS_PATH + "/{id}";
    public static final String CURRENT_PROFILE_PATH = ACCOUNTS_PATH + "/profile/current";
    public static final String TRANSPORTERS_PATH = ACCOUNTS_PATH + "/transporters";
    public static final String JEWELERS_PATH = ACCOUNTS_PATH + "/jewelers";
    public static final String USERS_PATH = ACCOUNTS_PATH + "/users";
    public static final String STAFFS_PATH = ACCOUNTS_PATH + "/staffs";
    public static final String GET_RANDOM_STAFF_PATH = STAFFS_PATH + "/random";
    public static final String GET_DESIGN_STAFFS_PATH = STAFFS_PATH + "/designers";


    /*
        Authentication's paths
     */
    public static final String AUTH_PATH = "/auth";
    public static final String LOGIN_PATH = AUTH_PATH + "/login";
    public static final String REFRESH_TOKEN_PATH = AUTH_PATH + "/refresh";

    /*
        Spouses' paths
     */
    public static final String SPOUSES_PATH = "/spouses";
    public static final String VERIFY_SPOUSE_PATH = SPOUSES_PATH + "/{citizenId}";

    /*
        Payments' paths
     */
    public static final String PAYMENTS_PATH = "/payments";
    public static final String VNPAY_PATH = PAYMENTS_PATH + "/vnpay";

    /*
        Design's paths
     */
    public static final String DESIGN_PATH = "/designs";
    public static final String DESIGN_COUPLE_PATH = DESIGN_PATH + "/couple";
    public static final String DESIGN_SESSION_PATH = DESIGN_PATH + "/sessions";
    public static final String CREATE_DESIGN_SESSION_PATH = DESIGN_SESSION_PATH + "/create";
    public static final String DESIGN_VERSION_PATH = DESIGN_PATH + "/versions";
    public static final String VIEW_SINGLE_DESIGN_VERSION_PATH = DESIGN_VERSION_PATH + "/{designVersionId}";
    public static final String CUSTOM_DESIGN_PATH = DESIGN_PATH + "/customs";
    public static final String CREATE_CUSTOM_DESIGN_PATH = CUSTOM_DESIGN_PATH;
    public static final String VIEW_CURRENT_DESIGN_VERSIONS_PATH = DESIGN_VERSION_PATH;
    public static final String ACCEPT_SINGLE_DESIGN_VERSION_PATH = DESIGN_VERSION_PATH + "/determination";
    public static final String VIEW_CUSTOM_DESIGNS_PATH = CUSTOM_DESIGN_PATH;
    public static final String VIEW_CUSTOM_DESIGN_PATH = CUSTOM_DESIGN_PATH + "/{customDesignId}";
    public static final String VIEW_DESIGN_SESSIONS_LEFT_PATH = DESIGN_SESSION_PATH + "/customers/{customerId}/left";
    public static final String VIEW_DESIGN_PATH = DESIGN_PATH + "/{designId}";
    public static final String VIEW_DESIGN_COLLECTION_PATH = DESIGN_PATH + "/collections";

    /*
       Uploading path
     */
    public static final String FILES_PATH = "/files";

    /*
        Custom requests' paths
     */
    public static final String CUSTOM_REQUEST_PATH = "/custom-requests";
    public static final String CUSTOM_SINGLE_REQUEST_PATH = CUSTOM_REQUEST_PATH + "/{customRequestId}";
    public static final String VIEW_CUSTOM_REQUESTS_PATH = CUSTOM_REQUEST_PATH;
    public static final String DETERMINE_CUSTOM_REQUEST_PATH = CUSTOM_REQUEST_PATH + "/determination/{customRequestId}";
    public static final String CANCEL_CUSTOM_REQUEST_PATH = CUSTOM_REQUEST_PATH + "/{customRequestId}/rejection";

    /*
        Crafting request
     */
    public static final String CRAFTING_REQUEST_PATH = "/crafting-requests";
    public static final String ACCEPT_CRAFTING_REQUEST_PATH = CRAFTING_REQUEST_PATH + "/determination";
    public static final String VIEW_CRAFTING_REQUESTS_PATH = CRAFTING_REQUEST_PATH;
    public static final String VIEW_CRAFTING_REQUEST_PATH = VIEW_CRAFTING_REQUESTS_PATH + "/{craftingRequestId}";
    public static final String VIEW_CRAFTING_REQUEST_GROUPS_PATH = VIEW_CRAFTING_REQUESTS_PATH + "/groups";

    /*
        Crafting stage
     */
    public static final String CRAFTING_STAGE_PATH = "/crafting-stages";
    public static final String DEPOSIT_CRAFTING_STAGE_PATH = CRAFTING_STAGE_PATH + "/deposit";
    public static final String COMPLETE_CRAFTING_STAGE_PATH = CRAFTING_STAGE_PATH + "/{craftingStageId}/completion";

    /*
       Diamond specification
     */
    public static final String DIAMOND_SPECIFICATION_PATH = "/diamond-specifications";

    /*
       Metal specification
     */
    public static final String METAL_SPECIFICATION_PATH = "/metal-specifications";

    /*
       Contract
    */
    public static final String CONTRACT_PATH = "/contracts";
    public static final String SIGNING_CONTRACT_PATH = CONTRACT_PATH + "/{contractId}/signing";

    /*
       transport paths
     */
    public static final String TRANSPORTATION_ORDER_PATH = "/transportation-orders";
    public static final String ASSIGN_TRANSPORTATION_ORDER_PATH = TRANSPORTATION_ORDER_PATH + "/{transportationOrderId}/assigning";
    public static final String UPDATE_TRANSPORTATION_ORDER_TO_ONGOING_PATH = TRANSPORTATION_ORDER_PATH + "/ongoing";
    public static final String UPDATE_TRANSPORTATION_ORDER_STATUS = TRANSPORTATION_ORDER_PATH + "/{transportationOrderId}/status";
    public static final String VIEW_TRANSPORTATION_ORDER_BY_CUSTOM_ORDER_ID = TRANSPORTATION_ORDER_PATH + "/custom-orders/{customOrderId}";
    public static final String VIEW_TRANSPORTATION_ORDER_DETAIL = TRANSPORTATION_ORDER_PATH + "/{transportationOrderId}";
    public static final String UPDATE_TRANSPORTATION_DELIVERY_IMAGE_ORDER_PATH = TRANSPORTATION_ORDER_PATH + "/{transportationOrderId}/delivery-image";
    public static final String CREATE_TRANSPORTATION_NOTE_PATH = TRANSPORTATION_ORDER_PATH + "/notes";
    public static final String TRANSPORTATION_NOTE_PATH = CREATE_TRANSPORTATION_NOTE_PATH;

    /*
       custom order paths
     */
    public static final String CUSTOM_ORDERS_PATH = "/custom-orders";
    public static final String VIEW_A_CUSTOM_ORDER_PATH = CUSTOM_ORDERS_PATH + "/{customOrderId}";
    public static final String ASSIGN_CUSTOM_ORDER_TO_JEWELER_PATH = CUSTOM_ORDERS_PATH + "/{customOrderId}/assigning";

    /*
       agreements paths
     */
    public static final String AGREEMENTS_PATH = "/agreements";
    public static final String SIGN_AGREEMENT_PATH = AGREEMENTS_PATH + "/{agreementId}";

    /*
      branches paths
     */
    public static final String BRANCHES_PATH = "/branches";

    /*
      finger sizes paths
     */
    public static final String FINGER_SIZES_PATH = "/finger-sizes";

    /*
     address paths
     */
    public static final String ADDRESS_PATH = "/addresses";

    /*
        Diamond paths
     */
    public static final String DIAMONDS_PATH = "/diamonds";
    public static final String SINGLE_DIAMOND_PATH = DIAMONDS_PATH + "/{diamondId}";

    /*
        crafting ring
     */
    public static final String CRAFTING_RING_PATH = "/crafting/rings";

    /*
        jewelries' paths
     */
    public static final String JEWELRIES_PATH = "/jewelries";

    /*
        jewelries' paths
     */
    public static final String STANDARD_ORDER_PATH = "/standard-orders";
}

