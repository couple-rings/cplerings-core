package com.cplerings.core.api.shared;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@JsonSerialize(using = NoDataSerializer.class)
public final class NoData {

    @SuppressWarnings("InstantiationOfUtilityClass")
    public static final NoData INSTANCE = new NoData();
}
