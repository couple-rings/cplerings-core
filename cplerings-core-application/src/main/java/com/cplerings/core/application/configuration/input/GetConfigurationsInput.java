package com.cplerings.core.application.configuration.input;

import com.cplerings.core.application.shared.pagination.AbstractPaginatedInput;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GetConfigurationsInput extends AbstractPaginatedInput {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedInputBuilder<Builder, GetConfigurationsInput> {

        @Override
        protected GetConfigurationsInput getRequestInstance() {
            return new GetConfigurationsInput();
        }
    }
}
