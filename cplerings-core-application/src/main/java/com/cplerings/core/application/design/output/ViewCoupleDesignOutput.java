package com.cplerings.core.application.design.output;

import com.cplerings.core.application.shared.entity.design.ADesignCouple;
import com.cplerings.core.application.shared.pagination.AbstractPaginatedOutput;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ViewCoupleDesignOutput extends AbstractPaginatedOutput<ADesignCouple> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedOutputBuilder<Builder, ViewCoupleDesignOutput, ADesignCouple> {

        @Override
        protected ViewCoupleDesignOutput getOutputInstance() {
            return new ViewCoupleDesignOutput();
        }
    }
}
