package com.cplerings.core.test.shared.datasource;

import com.cplerings.core.domain.design.session.DesignSession;
import com.cplerings.core.domain.payment.Payment;
import com.cplerings.core.domain.payment.PaymentReceiver;

public interface TestDataSource {

    Payment save(Payment payment);

    PaymentReceiver save(PaymentReceiver paymentReceiver);

    DesignSession save(DesignSession designSession);
}
