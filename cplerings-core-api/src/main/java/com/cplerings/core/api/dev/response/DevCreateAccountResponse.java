package com.cplerings.core.api.dev.response;

import com.cplerings.core.api.shared.AbstractDataResponse;
import com.cplerings.core.application.shared.entity.account.AAccount;

public class DevCreateAccountResponse extends AbstractDataResponse<AAccount> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractDataResponseBuilder<Builder, DevCreateAccountResponse, AAccount> {

        @Override
        protected DevCreateAccountResponse getResponseInstance() {
            return new DevCreateAccountResponse();
        }
    }
}
