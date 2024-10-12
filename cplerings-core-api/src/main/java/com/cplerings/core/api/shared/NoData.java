package com.cplerings.core.api.shared;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@JsonSerialize(using = NoDataSerializer.class)
public final class NoData {

    @SuppressWarnings("InstantiationOfUtilityClass")
    public static final NoData INSTANCE = new NoData();
}
