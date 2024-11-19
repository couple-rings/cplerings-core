package com.cplerings.core.application.fingersize.input;

import com.cplerings.core.application.shared.pagination.AbstractPaginatedInput;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewFingerSizesInput extends AbstractPaginatedInput {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedInputBuilder<Builder, ViewFingerSizesInput> {

        @Override
        protected ViewFingerSizesInput getRequestInstance() {
            return new ViewFingerSizesInput();
        }
    }
}
