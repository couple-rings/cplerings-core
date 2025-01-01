package com.cplerings.core.api.dashboard.response;

import com.cplerings.core.api.dashboard.data.ViewPaymentWithDateData;
import com.cplerings.core.api.shared.AbstractPaginatedResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ViewPaymentWithDateResponse extends AbstractPaginatedResponse<ViewPaymentWithDateData> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedResponseBuilder<Builder, ViewPaymentWithDateResponse, ViewPaymentWithDateData> {

        @Override
        protected ViewPaymentWithDateResponse getResponseInstance() {
            return new ViewPaymentWithDateResponse();
        }
    }
}
