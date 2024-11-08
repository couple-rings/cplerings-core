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

    /*
       Uploading path
    */
    public static final String FILES_PATH = "/files";

    /*
        Custom requests' paths
     */
    public static final String CUSTOM_REQUEST_PATH = "/custom-requests";
    public static final String CUSTOM_SINGLE_REQUEST_PATH = CUSTOM_REQUEST_PATH + "/{customRequestId}";
<<<<<<< HEAD
    public static final String VIEW_CUSTOM_REQUESTS_PATH = CUSTOM_REQUEST_PATH;
=======
    public static final String DETERMINE_CUSTOM_REQUEST_PATH = CUSTOM_REQUEST_PATH + "/determination/{customRequestId}";
>>>>>>> 8f77d5c (Add function determine custom request  and the IT)

    /*
        Crafting request
     */
    public static final String CRAFTING_REQUEST_PATH = "/crafting-requests";
    public static final String ACCEPT_CRAFTING_REQUEST_PATH = CRAFTING_REQUEST_PATH + "/determination";
}
