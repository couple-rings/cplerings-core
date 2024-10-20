package com.cplerings.core.common.ip;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Random;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class IPUtils {

    private static final Random RANDOM = new Random();

    private static final int MAX_OCTET = 256;
    private static final int MAX_OCTET_COUNT = 4;

    public static String generateRandomIP() {
        final StringBuilder ipBuilder = new StringBuilder();
        ipBuilder.append(RANDOM.nextInt(0, MAX_OCTET));
        for (int i = 0; i < (MAX_OCTET_COUNT - 1); i++) {
            ipBuilder.append('.');
            ipBuilder.append(RANDOM.nextInt(0, MAX_OCTET));
        }
        return ipBuilder.toString();
    }
}
