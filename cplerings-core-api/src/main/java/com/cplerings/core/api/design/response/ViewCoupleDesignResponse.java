package com.cplerings.core.api.design.response;

import com.cplerings.core.api.design.data.CoupleDesignInformation;
import com.cplerings.core.api.shared.AbstractDataResponse;

public class ViewCoupleDesignResponse extends AbstractDataResponse<CoupleDesignInformation> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder
            extends AbstractDataResponseBuilder<Builder, ViewCoupleDesignResponse, CoupleDesignInformation> {

        @Override
        protected ViewCoupleDesignResponse getResponseInstance() {
            return new ViewCoupleDesignResponse();
        }
    }
}
