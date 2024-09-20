package com.cplerings.core.api.shared;

import java.time.Instant;

import com.cplerings.core.common.fluentapi.AbstractSelf;
import com.cplerings.core.common.pagination.Buildable;
import com.cplerings.core.common.temporal.TemporalUtils;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractResponse {

    public enum Type {

        DATA, PAGINATED_DATA, INFO, ERROR
    }

    private final Instant timestamp = TemporalUtils.getCurrentInstantUTC();

    private Type type;

    @Getter(AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public abstract static class AbstractResponseBuilder<S extends AbstractResponseBuilder<S, R>, R extends AbstractResponse>
            extends AbstractSelf<S> implements Buildable<R> {

    }
}
