package com.cplerings.core.common.api;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class APIConstant {

    public static final String APPLICATION_JSON = "application/json";

    /*
        Accounts' paths
     */
    public static final String ACCOUNTS_PATH = "/accounts";
    public static final String CUSTOMER_PATH = ACCOUNTS_PATH + "/customer";
    public static final String VERIFY_CUSTOMER_PATH = CUSTOMER_PATH + "/verification";
    public static final String REGISTER_CUSTOMER_PATH = CUSTOMER_PATH + "/registration";
    public static final String PASSWORD_PATH = ACCOUNTS_PATH + "/password";
    public static final String REQUEST_RESET_PASSWORD_PATH = PASSWORD_PATH + "/forget";
    public static final String RESEND_VERIFICATION_CODE_PATH = VERIFY_CUSTOMER_PATH + "/resend";

    /*
        Authentication's paths
     */
    public static final String AUTH_PATH = "/auth";
    public static final String LOGIN_PATH = AUTH_PATH + "/login";
    public static final String REFRESH_TOKEN_PATH = AUTH_PATH + "/refresh";
}
