package com.cplerings.core.api.configuration.data;

import com.cplerings.core.api.shared.AbstractPaginatedData;
import com.cplerings.core.application.shared.entity.configuration.AConfiguration;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GetConfigurationsData extends AbstractPaginatedData<AConfiguration> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedDataBuilder<Builder, GetConfigurationsData, AConfiguration> {

        @Override
        protected GetConfigurationsData getDataInstance() {
            return new GetConfigurationsData();
        }
    }
}
