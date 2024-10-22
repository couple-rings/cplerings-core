package com.cplerings.core.api.design.response;

import com.cplerings.core.api.account.data.CustomerEmailInfo;
import com.cplerings.core.api.account.response.CustomerEmailInfoResponse;
import com.cplerings.core.api.design.data.DesignSession;
import com.cplerings.core.api.shared.AbstractDataResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateDesignSessionResponse extends AbstractDataResponse<DesignSession> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder
            extends AbstractDataResponseBuilder<Builder, CreateDesignSessionResponse, DesignSession> {

        @Override
        protected CreateDesignSessionResponse getResponseInstance() {
            return new CreateDesignSessionResponse();
        }
    }
}
