package com.cplerings.core.api.transport.data;

import com.cplerings.core.api.shared.AbstractPaginatedData;
import com.cplerings.core.application.shared.entity.order.ATransportationOrder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TransportationOrdersData extends AbstractPaginatedData<ATransportationOrder> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedDataBuilder<Builder, TransportationOrdersData, ATransportationOrder> {

        @Override
        protected TransportationOrdersData getDataInstance() {
            return new TransportationOrdersData();
        }
    }
}
