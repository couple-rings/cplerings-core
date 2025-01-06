package com.cplerings.core.application.order.datasource;

import com.cplerings.core.domain.design.Design;
import com.cplerings.core.domain.design.DesignVersion;
import com.cplerings.core.domain.diamond.Diamond;
import com.cplerings.core.domain.order.CustomOrder;
import com.cplerings.core.domain.order.CustomOrderHistory;
import com.cplerings.core.domain.order.TransportationOrder;
import com.cplerings.core.domain.ring.Ring;
import com.cplerings.core.domain.ring.RingHistory;
import com.cplerings.core.domain.spouse.Agreement;

import java.util.Collection;
import java.util.Optional;

public interface CancelCustomOrderDataSource {

    Optional<CustomOrder> findCustomOrderById(Long customOrderId);

    CustomOrder save(CustomOrder customOrder);

    CustomOrderHistory save(CustomOrderHistory customOrderHistory);

    Collection<TransportationOrder> saveTransportationOrders(Collection<TransportationOrder> transportationOrders);

    RingHistory save(RingHistory ringHistory);

    Collection<Ring> saveRings(Collection<Ring> rings);

    Collection<Diamond> saveDiamonds(Collection<Diamond> diamonds);

    Collection<Design> saveDesigns(Collection<Design> designs);

    void delete(Agreement agreement);

    Collection<DesignVersion> findActiveDesignVersionsByDesignIds(Collection<Long> designIds);

    Collection<DesignVersion> saveDesignVersions(Collection<DesignVersion> designVersions);
}
