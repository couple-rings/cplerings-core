package com.cplerings.core.api.account.response;

import com.cplerings.core.api.account.data.Profile;
import com.cplerings.core.api.shared.AbstractDataResponse;

public class ProfileResponse extends AbstractDataResponse<Profile> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder
            extends AbstractDataResponseBuilder<Builder, ProfileResponse, Profile> {

        @Override
        protected ProfileResponse getResponseInstance() {
            return new ProfileResponse();
        }
    }
}
