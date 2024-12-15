package com.cplerings.core.application.account.output;

import com.cplerings.core.application.shared.entity.account.AAccount;
import com.cplerings.core.application.shared.pagination.AbstractPaginatedOutput;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewCustomersOutput extends AbstractPaginatedOutput<AAccount> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedOutputBuilder<Builder, ViewCustomersOutput, AAccount> {

        @Override
        protected ViewCustomersOutput getOutputInstance() {
            return new ViewCustomersOutput();
        }
    }
}
