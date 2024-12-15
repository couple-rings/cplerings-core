package com.cplerings.core.api.order.response;

import com.cplerings.core.api.order.data.ResellOrdersData;
import com.cplerings.core.api.shared.AbstractPaginatedResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewResellOrdersResponse extends AbstractPaginatedResponse<ResellOrdersData> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedResponseBuilder<Builder, ViewResellOrdersResponse, ResellOrdersData> {

        @Override
        protected ViewResellOrdersResponse getResponseInstance() {
            return new ViewResellOrdersResponse();
        }
    }
}
