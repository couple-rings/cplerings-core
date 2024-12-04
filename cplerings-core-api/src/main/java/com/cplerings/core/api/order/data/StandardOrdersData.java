package com.cplerings.core.api.order.data;

import com.cplerings.core.api.shared.AbstractPaginatedData;
import com.cplerings.core.application.shared.entity.order.AStandardOrder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StandardOrdersData extends AbstractPaginatedData<AStandardOrder> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedDataBuilder<Builder, StandardOrdersData, AStandardOrder> {

        @Override
        protected StandardOrdersData getDataInstance() {
            return new StandardOrdersData();
        }
    }
}
