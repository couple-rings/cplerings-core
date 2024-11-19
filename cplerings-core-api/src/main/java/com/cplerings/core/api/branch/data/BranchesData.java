package com.cplerings.core.api.branch.data;

import com.cplerings.core.api.shared.AbstractPaginatedData;
import com.cplerings.core.application.shared.entity.branch.ABranch;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BranchesData extends AbstractPaginatedData<ABranch> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedDataBuilder<Builder, BranchesData, ABranch> {

        @Override
        protected BranchesData getDataInstance() {
            return new BranchesData();
        }
    }

}
