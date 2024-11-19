package com.cplerings.core.application.order.datasource;

import java.util.Optional;

import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.order.CustomOrder;

public interface AssignJewelerToCustomOrderDataSource {

    Optional<Account> getJewelerById(Long jewelerId);

    Optional<CustomOrder> getCustomOrderById(Long customOrderId);

    CustomOrder save(CustomOrder customOrder);
}
