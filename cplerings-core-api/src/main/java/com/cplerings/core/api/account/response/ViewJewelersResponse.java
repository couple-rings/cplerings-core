package com.cplerings.core.api.account.response;

import com.cplerings.core.api.account.data.JewelersData;
import com.cplerings.core.api.shared.AbstractPaginatedResponse;

public class ViewJewelersResponse extends AbstractPaginatedResponse<JewelersData> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedResponse.AbstractPaginatedResponseBuilder<Builder, ViewJewelersResponse, JewelersData> {

        @Override
        protected ViewJewelersResponse getResponseInstance() {
            return new ViewJewelersResponse();
        }
    }
}
