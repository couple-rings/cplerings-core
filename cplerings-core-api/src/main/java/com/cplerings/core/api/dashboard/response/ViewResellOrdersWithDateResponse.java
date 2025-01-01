package com.cplerings.core.api.dashboard.response;

import com.cplerings.core.api.dashboard.data.ViewResellOrdersWithDateData;
import com.cplerings.core.api.shared.AbstractPaginatedResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ViewResellOrdersWithDateResponse extends AbstractPaginatedResponse<ViewResellOrdersWithDateData> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedResponseBuilder<Builder, ViewResellOrdersWithDateResponse, ViewResellOrdersWithDateData> {

        @Override
        protected ViewResellOrdersWithDateResponse getResponseInstance() {
            return new ViewResellOrdersWithDateResponse();
        }
    }
}
