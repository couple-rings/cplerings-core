package com.cplerings.core.test.shared.datasource;

import com.cplerings.core.domain.design.DesignVersion;
import com.cplerings.core.domain.design.request.CustomRequest;
import com.cplerings.core.domain.design.session.DesignSession;
import com.cplerings.core.domain.payment.Payment;
import com.cplerings.core.domain.payment.PaymentReceiver;
import com.cplerings.core.domain.spouse.Spouse;
import com.cplerings.core.domain.spouse.SpouseAccount;

public interface TestDataSource {

    Payment save(Payment payment);

    PaymentReceiver save(PaymentReceiver paymentReceiver);

    DesignSession save(DesignSession designSession);

    Spouse save(Spouse spouse);

    SpouseAccount save(SpouseAccount spouseAccount);

    DesignVersion save(DesignVersion designVersion);

    CustomRequest save(CustomRequest customRequest);
}
