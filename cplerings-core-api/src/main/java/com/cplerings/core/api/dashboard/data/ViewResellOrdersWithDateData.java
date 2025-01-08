package com.cplerings.core.api.dashboard.data;

import com.cplerings.core.api.shared.AbstractPaginatedData;
import com.cplerings.core.application.dashboard.datasource.data.CombinedOrder;
import com.cplerings.core.application.shared.entity.order.AResellOrder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewResellOrdersWithDateData extends AbstractPaginatedData<AResellOrder> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedDataBuilder<Builder, ViewResellOrdersWithDateData, AResellOrder> {

        @Override
        protected ViewResellOrdersWithDateData getDataInstance() {
            return new ViewResellOrdersWithDateData();
        }
    }
}
