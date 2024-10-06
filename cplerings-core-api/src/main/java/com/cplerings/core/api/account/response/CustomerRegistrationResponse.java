package com.cplerings.core.api.account.response;

import com.cplerings.core.api.account.data.CustomerRegistration;
import com.cplerings.core.api.shared.AbstractDataResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CustomerRegistrationResponse extends AbstractDataResponse<CustomerRegistration> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder
            extends AbstractDataResponseBuilder<Builder, CustomerRegistrationResponse, CustomerRegistration> {

        @Override
        protected CustomerRegistrationResponse getResponseInstance() {
            return new CustomerRegistrationResponse();
        }
    }
}
