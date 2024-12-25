package com.cplerings.core.application.order.datasource;

import java.util.Collection;
import java.util.Optional;

import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.diamond.Diamond;
import com.cplerings.core.domain.file.Image;
import com.cplerings.core.domain.order.CustomOrder;
import com.cplerings.core.domain.resell.ResellOrder;
import com.cplerings.core.domain.ring.Ring;
import com.cplerings.core.domain.spouse.Agreement;

public interface ResellCustomOrderDataSource {

    Optional<CustomOrder> getCustomOrderById(Long id);

    ResellOrder save(ResellOrder resellOrder);

    Optional<Account> findCustomerById(Long customerId);

    Optional<Image> findProofImageById(Long proofImageId);

    Account getStaffReference(Long staffId);

    Ring save(Ring ring);

    void delete(Agreement agreement);

    void saveDiamonds(Collection<Diamond> diamonds);
}
