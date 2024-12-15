package com.cplerings.core.api.account.response;

import com.cplerings.core.api.account.data.CustomersData;
import com.cplerings.core.api.shared.AbstractPaginatedResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewCustomersResponse extends AbstractPaginatedResponse<CustomersData> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedResponseBuilder<Builder, ViewCustomersResponse, CustomersData> {

        @Override
        protected ViewCustomersResponse getResponseInstance() {
            return new ViewCustomersResponse();
        }
    }
}
