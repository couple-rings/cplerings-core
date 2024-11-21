package com.cplerings.core.api.transport.data;

import com.cplerings.core.api.shared.AbstractPaginatedData;
import com.cplerings.core.application.shared.entity.address.ATransportationAddress;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TransportationAddressesData extends AbstractPaginatedData<ATransportationAddress> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedDataBuilder<Builder, TransportationAddressesData, ATransportationAddress> {

        @Override
        protected TransportationAddressesData getDataInstance() {
            return new TransportationAddressesData();
        }
    }
}