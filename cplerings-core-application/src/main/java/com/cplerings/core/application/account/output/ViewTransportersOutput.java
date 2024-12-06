package com.cplerings.core.application.account.output;

import com.cplerings.core.application.shared.entity.account.ATransporter;
import com.cplerings.core.application.shared.pagination.AbstractPaginatedOutput;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewTransportersOutput extends AbstractPaginatedOutput<ATransporter> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedOutputBuilder<Builder, ViewTransportersOutput, ATransporter> {

        @Override
        protected ViewTransportersOutput getOutputInstance() {
            return new ViewTransportersOutput();
        }
    }
}
