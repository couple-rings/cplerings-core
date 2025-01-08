package com.cplerings.core.api.dashboard.data;

import com.cplerings.core.api.shared.AbstractPaginatedData;
import com.cplerings.core.application.dashboard.datasource.data.CombinedOrder;
import com.cplerings.core.application.shared.entity.order.ACustomOrder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewCustomOrdersWithDateData extends AbstractPaginatedData<ACustomOrder> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedDataBuilder<Builder, ViewCustomOrdersWithDateData, ACustomOrder> {

        @Override
        protected ViewCustomOrdersWithDateData getDataInstance() {
            return new ViewCustomOrdersWithDateData();
        }
    }
}
