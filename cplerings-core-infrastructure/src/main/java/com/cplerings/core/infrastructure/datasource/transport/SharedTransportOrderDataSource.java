package com.cplerings.core.infrastructure.datasource.transport;

import java.util.List;
import java.util.Optional;

import com.cplerings.core.application.transport.datasource.AssignTransportOrderDataSource;
import com.cplerings.core.application.transport.datasource.UpdateTransportationOrdersToOngoingDataSource;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.account.QAccount;
import com.cplerings.core.domain.order.QTransportationOrder;
import com.cplerings.core.domain.order.TransportationOrder;
import com.cplerings.core.domain.shared.State;
import com.cplerings.core.infrastructure.datasource.AbstractDataSource;
import com.cplerings.core.infrastructure.datasource.DataSource;
import com.cplerings.core.infrastructure.repository.TransportationOrderRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@DataSource
public class SharedTransportOrderDataSource extends AbstractDataSource implements AssignTransportOrderDataSource, UpdateTransportationOrdersToOngoingDataSource {

    private static final QAccount Q_ACCOUNT = QAccount.account;
    private static final QTransportationOrder Q_TRANSPORTATION_ORDER = QTransportationOrder.transportationOrder;

    private final TransportationOrderRepository transportationOrderRepository;

    @Override
    public Optional<Account> getTransporterById(Long transporterId) {
        return Optional.ofNullable(createQuery()
                .select(Q_ACCOUNT)
                .from(Q_ACCOUNT)
                .where(Q_ACCOUNT.id.eq(transporterId)
                        .and(Q_ACCOUNT.state.eq(State.ACTIVE)))
                .fetchOne());
    }

    @Override
    public Optional<TransportationOrder> getTransportationOrderById(Long transportationOrderId) {
        return Optional.ofNullable(createQuery()
                .select(Q_TRANSPORTATION_ORDER)
                .from(Q_TRANSPORTATION_ORDER)
                .where(Q_TRANSPORTATION_ORDER.id.eq(transportationOrderId))
                .fetchOne());
    }

    @Override
    public TransportationOrder save(TransportationOrder transportationOrder) {
        updateAuditor(transportationOrder);
        return transportationOrderRepository.save(transportationOrder);
    }

    @Override
    public List<TransportationOrder> getTransportationOrders(List<Long> ids) {
        return createQuery().select(Q_TRANSPORTATION_ORDER)
                .from(Q_TRANSPORTATION_ORDER)
                .where(Q_TRANSPORTATION_ORDER.id.in(ids))
                .fetch();
    }

    @Override
    public List<TransportationOrder> updateToOngoing(List<TransportationOrder> transportationOrders) {
        transportationOrders.forEach(this::updateAuditor);
        return transportationOrderRepository.saveAll(transportationOrders);
    }
}
