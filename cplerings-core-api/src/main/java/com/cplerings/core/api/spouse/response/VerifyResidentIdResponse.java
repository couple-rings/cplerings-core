package com.cplerings.core.api.spouse.response;

import com.cplerings.core.api.shared.AbstractDataResponse;
import com.cplerings.core.api.spouse.data.SpouseData;

public class VerifyResidentIdResponse extends AbstractDataResponse<SpouseData> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder
            extends AbstractDataResponseBuilder<Builder, VerifyResidentIdResponse, SpouseData> {

        @Override
        protected VerifyResidentIdResponse getResponseInstance() {
            return new VerifyResidentIdResponse();
        }
    }
}
