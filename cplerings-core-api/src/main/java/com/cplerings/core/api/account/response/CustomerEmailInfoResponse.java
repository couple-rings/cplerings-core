package com.cplerings.core.api.account.response;

import com.cplerings.core.api.account.data.CustomerEmailInfo;
import com.cplerings.core.api.shared.AbstractDataResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CustomerEmailInfoResponse extends AbstractDataResponse<CustomerEmailInfo> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder
            extends AbstractDataResponseBuilder<Builder, CustomerEmailInfoResponse, CustomerEmailInfo> {

        @Override
        protected CustomerEmailInfoResponse getResponseInstance() {
            return new CustomerEmailInfoResponse();
        }
    }
}
