package com.cplerings.core.api.account.response;

import com.cplerings.core.api.account.data.TransportersData;
import com.cplerings.core.api.shared.AbstractPaginatedResponse;

public class ViewTransportersResponse extends AbstractPaginatedResponse<TransportersData>{

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedResponseBuilder<Builder, ViewTransportersResponse, TransportersData> {

        @Override
        protected ViewTransportersResponse getResponseInstance() {
            return new ViewTransportersResponse();
        }
    }
}
