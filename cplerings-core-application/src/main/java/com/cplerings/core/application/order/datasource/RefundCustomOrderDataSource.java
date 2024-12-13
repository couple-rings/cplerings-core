package com.cplerings.core.application.order.datasource;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.design.Design;
import com.cplerings.core.domain.diamond.Diamond;
import com.cplerings.core.domain.file.Image;
import com.cplerings.core.domain.order.CustomOrder;
import com.cplerings.core.domain.order.CustomOrderHistory;
import com.cplerings.core.domain.order.TransportationOrder;
import com.cplerings.core.domain.refund.Refund;
import com.cplerings.core.domain.ring.Ring;
import com.cplerings.core.domain.ring.RingDiamond;
import com.cplerings.core.domain.spouse.Agreement;

public interface RefundCustomOrderDataSource {

    Optional<CustomOrder> findCustomOrderById(Long customOrderId);

    CustomOrder save(CustomOrder customOrder);

    Optional<Account> findStaffById(Long staffId);

    Optional<Image> findImageById(Long imageId);

    Refund save(Refund refund);

    CustomOrderHistory save(CustomOrderHistory customOrderHistory);

    List<Ring> saveRings(Collection<Ring> rings);

    List<Design> saveDesigns(Collection<Design> designs);

    void delete(Agreement agreement);

    void deleteRingDiamonds(Collection<RingDiamond> ringDiamonds);

    void saveDiamonds(Collection<Diamond> diamonds);

    TransportationOrder save(TransportationOrder transportationOrder);
}
