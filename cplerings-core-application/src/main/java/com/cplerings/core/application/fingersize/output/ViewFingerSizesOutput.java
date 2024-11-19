package com.cplerings.core.application.fingersize.output;

import com.cplerings.core.application.shared.entity.ring.AFingerSize;
import com.cplerings.core.application.shared.pagination.AbstractPaginatedOutput;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewFingerSizesOutput extends AbstractPaginatedOutput<AFingerSize> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedOutputBuilder<Builder, ViewFingerSizesOutput, AFingerSize> {

        @Override
        protected ViewFingerSizesOutput getOutputInstance() {
            return new ViewFingerSizesOutput();
        }
    }
}
