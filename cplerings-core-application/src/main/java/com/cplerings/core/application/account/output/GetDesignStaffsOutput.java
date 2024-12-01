package com.cplerings.core.application.account.output;

import com.cplerings.core.application.shared.entity.account.ADesignStaff;
import com.cplerings.core.application.shared.pagination.AbstractPaginatedOutput;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GetDesignStaffsOutput extends AbstractPaginatedOutput<ADesignStaff> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedOutputBuilder<Builder, GetDesignStaffsOutput, ADesignStaff> {

        @Override
        protected GetDesignStaffsOutput getOutputInstance() {
            return new GetDesignStaffsOutput();
        }
    }
}
