package com.cplerings.core.domain.account;

public enum Role {

    MANAGER, STAFF, CUSTOMER, JEWELER, ADMIN, TRANSPORTER;

    public static boolean isRoleAsStringNotValid(String roleAsString) {
        try {
            Role.valueOf(roleAsString);
            return false;
        } catch (Exception e) {
            return true;
        }
    }
}
