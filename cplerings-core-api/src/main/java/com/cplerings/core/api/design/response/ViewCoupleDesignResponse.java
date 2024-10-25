package com.cplerings.core.api.design.response;

import com.cplerings.core.api.design.data.DesignCoupleData;
import com.cplerings.core.api.shared.AbstractPaginatedResponse;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ViewCoupleDesignResponse extends AbstractPaginatedResponse<DesignCoupleData> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedResponseBuilder<Builder, ViewCoupleDesignResponse, DesignCoupleData> {

        @Override
        protected ViewCoupleDesignResponse getResponseInstance() {
            return new ViewCoupleDesignResponse();
        }
    }
}
