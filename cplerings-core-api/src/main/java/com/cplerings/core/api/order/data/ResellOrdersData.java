package com.cplerings.core.api.order.data;

import com.cplerings.core.api.shared.AbstractPaginatedData;
import com.cplerings.core.application.shared.entity.order.AResellOrder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResellOrdersData extends AbstractPaginatedData<AResellOrder> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedDataBuilder<Builder, ResellOrdersData, AResellOrder> {

        @Override
        protected ResellOrdersData getDataInstance() {
            return new ResellOrdersData();
        }
    }
}
