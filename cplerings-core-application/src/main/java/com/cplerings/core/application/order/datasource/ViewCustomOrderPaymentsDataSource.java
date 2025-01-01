package com.cplerings.core.application.order.datasource;

import com.cplerings.core.domain.payment.Payment;

import java.util.List;

public interface ViewCustomOrderPaymentsDataSource {

    boolean customOrderDoesNotExist(Long customOrderId);

    List<Payment> getPaymentsOfCustomOrder(Long customOrderId);
}
