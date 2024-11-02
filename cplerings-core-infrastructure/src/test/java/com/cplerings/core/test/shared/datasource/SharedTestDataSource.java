package com.cplerings.core.test.shared.datasource;

import com.cplerings.core.domain.design.DesignVersion;
import com.cplerings.core.domain.design.session.DesignSession;
import com.cplerings.core.domain.payment.Payment;
import com.cplerings.core.domain.payment.PaymentReceiver;
import com.cplerings.core.domain.spouse.Spouse;
import com.cplerings.core.domain.spouse.SpouseAccount;
import com.cplerings.core.infrastructure.datasource.AbstractDataSource;
import com.cplerings.core.infrastructure.repository.DesignSessionRepository;
import com.cplerings.core.infrastructure.repository.DesignVersionRepository;
import com.cplerings.core.infrastructure.repository.PaymentReceiverRepository;
import com.cplerings.core.infrastructure.repository.PaymentRepository;
import com.cplerings.core.infrastructure.repository.SpouseAccountRepository;
import com.cplerings.core.infrastructure.repository.SpouseRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.boot.test.context.TestComponent;

@TestComponent
@RequiredArgsConstructor
public class SharedTestDataSource extends AbstractDataSource implements TestDataSource {

    private final PaymentRepository paymentRepository;
    private final PaymentReceiverRepository paymentReceiverRepository;
    private final DesignSessionRepository designSessionRepository;
    private final SpouseRepository spouseRepository;
    private final SpouseAccountRepository spouseAccountRepository;
    private final DesignVersionRepository designVersionRepository;

    @Override
    public Payment save(Payment payment) {
        updateAuditor(payment);
        return paymentRepository.save(payment);
    }

    @Override
    public PaymentReceiver save(PaymentReceiver paymentReceiver) {
        updateAuditor(paymentReceiver);
        return paymentReceiverRepository.save(paymentReceiver);
    }

    @Override
    public DesignSession save(DesignSession designSession) {
        updateAuditor(designSession);
        return designSessionRepository.save(designSession);
    }

    @Override
    public Spouse save(Spouse spouse) {
        updateAuditor(spouse);
        return spouseRepository.saveAndFlush(spouse);
    }

    @Override
    public SpouseAccount save(SpouseAccount spouseAccount) {
        updateAuditor(spouseAccount);
        return spouseAccountRepository.save(spouseAccount);
    }

    @Override
    public DesignVersion save(DesignVersion designVersion) {
        updateAuditor(designVersion);
        return designVersionRepository.save(designVersion);
    }
}
