package com.cplerings.core.api;

import com.cplerings.core.common.temporal.TemporalUtils;

import lombok.Getter;
import lombok.Setter;

@Getter
public abstract class AbstractResponse {

    protected final String timestamp = String.valueOf(TemporalUtils.getCurrentInstantUTC().toEpochMilli());
    @Setter
    protected Type type;

    public enum Type {

        DATA, PAGINATED_DATA, INFO, ERROR
    }
}
