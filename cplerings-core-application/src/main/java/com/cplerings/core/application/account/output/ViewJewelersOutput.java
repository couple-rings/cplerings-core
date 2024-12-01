package com.cplerings.core.application.account.output;

import com.cplerings.core.application.shared.entity.account.AJeweler;
import com.cplerings.core.application.shared.pagination.AbstractPaginatedOutput;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewJewelersOutput extends AbstractPaginatedOutput<AJeweler> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedOutput.AbstractPaginatedOutputBuilder<Builder, ViewJewelersOutput, AJeweler> {

        @Override
        protected ViewJewelersOutput getOutputInstance() {
            return new ViewJewelersOutput();
        }
    }
}
