package com.cplerings.core.application.order.datasource;

import java.util.Optional;

import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.file.Image;
import com.cplerings.core.domain.jewelry.Jewelry;
import com.cplerings.core.domain.order.StandardOrder;
import com.cplerings.core.domain.order.TransportationOrder;
import com.cplerings.core.domain.refund.Refund;

public interface RefundStandardOrderDataSource {

    Optional<StandardOrder> getStandardOrderWithOrderItem(Long id);

    Jewelry save(Jewelry jewelry);

    Refund save(Refund refund);

    Optional<Account> getStaffById(Long id);

    Optional<Image> getImageById(Long id);

    TransportationOrder save(TransportationOrder transportationOrder);
}
