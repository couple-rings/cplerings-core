package com.cplerings.core.common.payment;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PaymentUtils {

    public static String hashQueriesWithSHA512(Map<String, Object> queries, String secretKey) {
        final HmacUtils hmacUtils = new HmacUtils(HmacAlgorithms.HMAC_SHA_512, secretKey);
        if (MapUtils.isEmpty(queries)) {
            hmacUtils.hmacHex(StringUtils.EMPTY);
        }
        final String queryString = joinQueriesWithURLEncoded(queries);
        return hmacUtils.hmacHex(queryString);
    }

    public static String joinQueriesWithURLEncoded(Map<String, Object> queries) {
        return queries.entrySet()
                .stream()
                .filter(Objects::nonNull)
                .map(entry -> StringUtils.joinWith("=",
                        entry.getKey(),
                        URLEncoder.encode(Objects.toString(entry.getValue()), StandardCharsets.US_ASCII)))
                .collect(Collectors.joining("&"));
    }

    public static String hashQueriesWithSHA256(Map<String, Object> queries, String secretKey) {
        final HmacUtils hmacUtils = new HmacUtils(HmacAlgorithms.HMAC_SHA_256, secretKey);
        if (MapUtils.isEmpty(queries)) {
            hmacUtils.hmacHex(StringUtils.EMPTY);
        }
        final String queryString = joinQueriesWithURLEncoded(queries);
        return hmacUtils.hmacHex(queryString);
    }
}
