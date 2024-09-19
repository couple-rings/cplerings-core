package com.cplerings.core.api.shared;

import com.cplerings.core.common.temporal.TemporalUtils;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public abstract class AbstractResponse {

    public enum Type {

        DATA, PAGINATED_DATA, INFO, ERROR
    }

    private final Instant timestamp = TemporalUtils.getCurrentInstantUTC();

    private Type type;
}
