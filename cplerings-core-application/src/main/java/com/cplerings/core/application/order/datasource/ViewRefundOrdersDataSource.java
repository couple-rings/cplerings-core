package com.cplerings.core.application.order.datasource;

import com.cplerings.core.application.order.datasource.result.Refunds;
import com.cplerings.core.application.order.input.ViewRefundOrdersInput;

public interface ViewRefundOrdersDataSource {

    Refunds getRefunds(ViewRefundOrdersInput input);
}
