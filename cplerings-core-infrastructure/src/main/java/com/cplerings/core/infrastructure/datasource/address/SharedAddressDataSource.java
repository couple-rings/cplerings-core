package com.cplerings.core.infrastructure.datasource.address;

import java.util.Optional;

import com.cplerings.core.application.transport.datasource.CreateTransportationAddressDataSource;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.account.QAccount;
import com.cplerings.core.domain.address.QTransportationAddress;
import com.cplerings.core.domain.address.TransportationAddress;
import com.cplerings.core.infrastructure.datasource.AbstractDataSource;
import com.cplerings.core.infrastructure.datasource.DataSource;
import com.cplerings.core.infrastructure.repository.TransportationAddressRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@DataSource
public class SharedAddressDataSource extends AbstractDataSource implements CreateTransportationAddressDataSource {

    private static final QTransportationAddress Q_TRANSPORTATION_ADDRESS = QTransportationAddress.transportationAddress;
    private static final QAccount Q_ACCOUNT = QAccount.account;

    private final TransportationAddressRepository transportationAddressRepository;

    @Override
    public TransportationAddress save(TransportationAddress address) {
        updateAuditor(address);
        return transportationAddressRepository.save(address);
    }

    @Override
    public Optional<Account> getCustomerById(Long customerId) {
        return Optional.ofNullable(createQuery()
                .select(Q_ACCOUNT)
                .from(Q_ACCOUNT)
                .where(Q_ACCOUNT.id.eq(customerId))
                .fetchOne());
    }
}
