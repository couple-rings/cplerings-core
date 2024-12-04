package com.cplerings.core.application.order.datasource;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.cplerings.core.application.order.datasource.data.JewelrySearchInfo;
import com.cplerings.core.domain.jewelry.Jewelry;
import com.cplerings.core.domain.order.StandardOrder;
import com.cplerings.core.domain.order.StandardOrderItem;

public interface PayStandardOrderDataSource {

    Optional<StandardOrder> findStandardOrderByIdAndCustomerId(Long standardOrderId, Long customerId);

    List<Jewelry> getJewelries(JewelrySearchInfo jewelrySearchInfo);

    List<Jewelry> save(List<Jewelry> jewelries);

    List<StandardOrderItem> save(Collection<StandardOrderItem> standardOrderItems);

    StandardOrder save(StandardOrder standardOrder);
}
