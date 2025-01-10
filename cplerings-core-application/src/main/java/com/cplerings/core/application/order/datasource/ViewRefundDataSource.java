package com.cplerings.core.application.order.datasource;

import com.cplerings.core.domain.refund.Refund;

public interface ViewRefundDataSource {

    Refund getRefundById(Long id);
}
