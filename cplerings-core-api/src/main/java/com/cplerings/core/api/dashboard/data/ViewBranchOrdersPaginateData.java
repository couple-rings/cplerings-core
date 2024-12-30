package com.cplerings.core.api.dashboard.data;

import com.cplerings.core.api.shared.AbstractPaginatedData;
import com.cplerings.core.application.dashboard.datasource.data.CombinedOrder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewBranchOrdersPaginateData extends AbstractPaginatedData<CombinedOrder> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedDataBuilder<Builder, ViewBranchOrdersPaginateData, CombinedOrder> {

        @Override
        protected ViewBranchOrdersPaginateData getDataInstance() {
            return new ViewBranchOrdersPaginateData();
        }
    }
}
