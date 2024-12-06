package com.cplerings.core.application.configuration.output;

import com.cplerings.core.application.shared.entity.configuration.AConfiguration;
import com.cplerings.core.application.shared.pagination.AbstractPaginatedOutput;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GetConfigurationsOutput extends AbstractPaginatedOutput<AConfiguration> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedOutputBuilder<Builder, GetConfigurationsOutput, AConfiguration> {

        @Override
        protected GetConfigurationsOutput getOutputInstance() {
            return new GetConfigurationsOutput();
        }
    }
}
