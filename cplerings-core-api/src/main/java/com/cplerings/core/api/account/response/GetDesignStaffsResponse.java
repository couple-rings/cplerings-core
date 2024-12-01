package com.cplerings.core.api.account.response;

import com.cplerings.core.api.account.data.DesignStaffsData;
import com.cplerings.core.api.shared.AbstractPaginatedResponse;

public class GetDesignStaffsResponse extends AbstractPaginatedResponse<DesignStaffsData> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedResponseBuilder<Builder, GetDesignStaffsResponse, DesignStaffsData> {

        @Override
        protected GetDesignStaffsResponse getResponseInstance() {
            return new GetDesignStaffsResponse();
        }
    }
}
