package com.cplerings.core.application.order.datasource;

import java.util.Collection;
import java.util.List;

import com.cplerings.core.domain.jewelry.Jewelry;
import com.cplerings.core.domain.order.StandardOrder;

public interface ProcessPayStandardOrderDataSource {

    StandardOrder save(StandardOrder order);

    List<Jewelry> saveJewelries(List<Jewelry> jewelries);
}
