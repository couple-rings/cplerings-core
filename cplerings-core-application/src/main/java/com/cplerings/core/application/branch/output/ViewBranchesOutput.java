package com.cplerings.core.application.branch.output;

import com.cplerings.core.application.shared.entity.branch.ABranch;
import com.cplerings.core.application.shared.pagination.AbstractPaginatedOutput;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewBranchesOutput extends AbstractPaginatedOutput<ABranch>{

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedOutputBuilder<Builder, ViewBranchesOutput, ABranch> {

        @Override
        protected ViewBranchesOutput getOutputInstance() {
            return new ViewBranchesOutput();
        }
    }
}
