package com.cplerings.core.application.transport.datasource;

import java.util.Optional;

import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.address.TransportationAddress;

public interface CreateTransportationAddressDataSource {

    TransportationAddress save(TransportationAddress address);

    Optional<Account> getCustomerById(Long customerId);
}
