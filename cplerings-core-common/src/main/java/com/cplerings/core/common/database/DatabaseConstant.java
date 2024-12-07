package com.cplerings.core.common.database;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DatabaseConstant {

    public static final int SEQ_ALLOCATION_SIZE = 10;

    public static final int DEFAULT_COMMENT_LENGTH = 500;

    public static final String LOB_DEFINITION = "TEXT";

    public static final int DEFAULT_ENUM_LENGTH = 12;

    public static final int DEFAULT_DESCRIPTION_LENGTH = 1000;

    public static final int DEFAULT_BANK_CODE_LENGTH = 20;

    public static final int DEFAULT_CARD_TYPE_LENGTH = 20;

    public static final int DEFAULT_PAYMENT_SECURE_HASH_LENGTH = 256;

    public static final int DEFAULT_CONFIGURATION_KEY_LENGTH = 10;

    public static final int DEFAULT_ENTITY_NO_LENGTH = 8;

    public static final int DEFAULT_ENGRAVING_LENGTH = 15;
}
