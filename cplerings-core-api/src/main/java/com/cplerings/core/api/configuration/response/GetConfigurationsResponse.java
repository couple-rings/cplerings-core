package com.cplerings.core.api.configuration.response;

import com.cplerings.core.api.configuration.data.GetConfigurationsData;
import com.cplerings.core.api.shared.AbstractPaginatedResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GetConfigurationsResponse extends AbstractPaginatedResponse<GetConfigurationsData> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedResponseBuilder<Builder, GetConfigurationsResponse, GetConfigurationsData> {

        @Override
        protected GetConfigurationsResponse getResponseInstance() {
            return new GetConfigurationsResponse();
        }
    }
}
