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
}
