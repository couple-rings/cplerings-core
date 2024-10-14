package com.cplerings.core.api.spouse.response;

import com.cplerings.core.api.shared.AbstractDataResponse;
import com.cplerings.core.api.shared.NoData;
import com.cplerings.core.api.shared.NoResponse;

public class SpouseInfoResponse extends AbstractDataResponse<NoData> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder
            extends AbstractDataResponseBuilder<Builder, NoResponse, NoData> {

        @Override
        protected NoResponse getResponseInstance() {
            return NoResponse.INSTANCE;
        }
    }
}
