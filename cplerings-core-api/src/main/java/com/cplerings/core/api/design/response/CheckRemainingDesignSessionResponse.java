package com.cplerings.core.api.design.response;

import com.cplerings.core.api.design.data.RemainingDesignSessionData;
import com.cplerings.core.api.shared.AbstractDataResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CheckRemainingDesignSessionResponse extends AbstractDataResponse<RemainingDesignSessionData> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractDataResponseBuilder<Builder, CheckRemainingDesignSessionResponse, RemainingDesignSessionData> {

        @Override
        protected CheckRemainingDesignSessionResponse getResponseInstance() {
            return new CheckRemainingDesignSessionResponse();
        }
    }
}
