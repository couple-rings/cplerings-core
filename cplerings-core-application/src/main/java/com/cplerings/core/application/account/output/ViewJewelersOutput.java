package com.cplerings.core.application.account.output;

import com.cplerings.core.application.shared.entity.account.AAccount;
import com.cplerings.core.application.shared.pagination.AbstractPaginatedOutput;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewJewelersOutput extends AbstractPaginatedOutput<AAccount>{

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedOutput.AbstractPaginatedOutputBuilder<Builder, ViewJewelersOutput, AAccount> {

        @Override
        protected ViewJewelersOutput getOutputInstance() {
            return new ViewJewelersOutput();
        }
    }
}
