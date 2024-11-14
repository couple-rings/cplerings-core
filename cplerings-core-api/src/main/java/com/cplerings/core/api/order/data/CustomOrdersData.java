package com.cplerings.core.api.order.data;

import com.cplerings.core.api.shared.AbstractPaginatedData;
import com.cplerings.core.application.shared.entity.order.ACustomOrder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CustomOrdersData extends AbstractPaginatedData<ACustomOrder> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedDataBuilder<Builder, CustomOrdersData, ACustomOrder> {

        @Override
        protected CustomOrdersData getDataInstance() {
            return new CustomOrdersData();
        }
    }
}
