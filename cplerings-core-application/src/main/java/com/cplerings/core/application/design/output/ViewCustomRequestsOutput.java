package com.cplerings.core.application.design.output;

import com.cplerings.core.application.shared.entity.design.request.ACustomRequest;
import com.cplerings.core.application.shared.pagination.AbstractPaginatedOutput;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewCustomRequestsOutput extends AbstractPaginatedOutput<ACustomRequest> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedOutputBuilder<Builder, ViewCustomRequestsOutput, ACustomRequest> {

        @Override
        protected ViewCustomRequestsOutput getOutputInstance() {
            return new ViewCustomRequestsOutput();
        }
    }
}
