package com.cplerings.core.common.database;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DatabaseConstant {

    public static final int SEQ_ALLOCATION_SIZE = 10;

    public static final int DEFAULT_COMMENT_LENGTH = 500;

    public static final String LOB_DEFINITION = "TEXT";

    public static final int DEFAULT_ENUM_LENGTH = 12;
}
