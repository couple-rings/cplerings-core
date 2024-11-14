package com.cplerings.core.api.spouse.response;

import com.cplerings.core.api.shared.AbstractDataResponse;
import com.cplerings.core.api.spouse.data.SpouseList;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewSpousesOfCustomerResponse extends AbstractDataResponse<SpouseList> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder
            extends AbstractDataResponseBuilder<Builder, ViewSpousesOfCustomerResponse, SpouseList> {

        @Override
        protected ViewSpousesOfCustomerResponse getResponseInstance() {
            return new ViewSpousesOfCustomerResponse();
        }
    }
}
