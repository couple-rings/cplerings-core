package com.cplerings.core.application.transport.output;

import com.cplerings.core.application.shared.entity.address.ATransportationAddress;
import com.cplerings.core.application.shared.pagination.AbstractPaginatedOutput;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewTransportationAddressesOutput extends AbstractPaginatedOutput<ATransportationAddress> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedOutputBuilder<Builder, ViewTransportationAddressesOutput, ATransportationAddress> {

        @Override
        protected ViewTransportationAddressesOutput getOutputInstance() {
            return new ViewTransportationAddressesOutput();
        }
    }
}
