package com.cplerings.core.infrastructure.datasource.design;

import com.cplerings.core.application.design.datasource.CheckRemainingDesignSessionDataSource;
import com.cplerings.core.application.design.datasource.CreateDesignSessionDataSource;
import com.cplerings.core.application.design.datasource.CreateDesignVersionDataSource;
import com.cplerings.core.application.design.datasource.ProcessDesignSessionPaymentDataSource;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.design.Design;
import com.cplerings.core.domain.design.DesignVersion;
import com.cplerings.core.domain.design.QDesign;
import com.cplerings.core.domain.design.request.CustomRequest;
import com.cplerings.core.domain.design.session.DesignSession;
import com.cplerings.core.domain.design.session.DesignSessionStatus;
import com.cplerings.core.domain.file.Document;
import com.cplerings.core.domain.file.Image;
import com.cplerings.core.domain.payment.PaymentReceiver;
import com.cplerings.core.infrastructure.datasource.AbstractDataSource;
import com.cplerings.core.infrastructure.datasource.DataSource;
import com.cplerings.core.infrastructure.repository.AccountRepository;
import com.cplerings.core.infrastructure.repository.CustomRequestRepository;
import com.cplerings.core.infrastructure.repository.DesignSessionRepository;
import com.cplerings.core.infrastructure.repository.DesignVersionRepository;
import com.cplerings.core.infrastructure.repository.DocumentRepository;
import com.cplerings.core.infrastructure.repository.ImageRepository;
import com.cplerings.core.infrastructure.repository.PaymentReceiverRepository;

import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.Optional;

@DataSource
@RequiredArgsConstructor
public class SharedDesignDataSource extends AbstractDataSource
        implements CreateDesignSessionDataSource, ProcessDesignSessionPaymentDataSource, CheckRemainingDesignSessionDataSource,
        CreateDesignVersionDataSource {

    private static final QDesign Q_DESIGN = QDesign.design;

    private final DesignSessionRepository designSessionRepository;
    private final AccountRepository accountRepository;
    private final PaymentReceiverRepository paymentReceiverRepository;
    private final CustomRequestRepository customRequestRepository;
    private final DesignVersionRepository designVersionRepository;
    private final DocumentRepository documentRepository;
    private final ImageRepository imageRepository;

    @Override
    public Optional<Account> getAccountByEmail(String email) {
        return accountRepository.findByEmail(email);
    }

    @Override
    public boolean thereIsNoUnusedDesignSession(Long accountId) {
        return !designSessionRepository.existsByCustomerIdAndStatus(accountId, DesignSessionStatus.UNUSED);
    }

    @Override
    public PaymentReceiver save(PaymentReceiver paymentReceiver) {
        updateAuditor(paymentReceiver);
        return paymentReceiverRepository.save(paymentReceiver);
    }

    @Override
    public Optional<Account> getAccountById(Long accountId) {
        return accountRepository.findById(accountId);
    }

    @Override
    public Collection<DesignSession> saveAll(Collection<DesignSession> designSessions) {
        designSessions.forEach(this::updateAuditor);
        return designSessionRepository.saveAll(designSessions);
    }

    @Override
    public CustomRequest save(CustomRequest customRequest) {
        updateAuditor(customRequest);
        return customRequestRepository.save(customRequest);
    }

    @Override
    public int getRemainingDesignSessionsCount(Long accountId) {
        return designSessionRepository.countByCustomerIdAndStatus(accountId, DesignSessionStatus.UNUSED);
    }

    @Override
    public DesignVersion save(DesignVersion designVersion) {
        updateAuditor(designVersion);
        return designVersionRepository.save(designVersion);
    }

    @Override
    public Document saveDocument(Document document) {
        updateAuditor(document);
        return documentRepository.save(document);
    }

    @Override
    public Image saveImage(Image image) {
        updateAuditor(image);
        return imageRepository.save(image);
    }

    @Override
    public Optional<Design> getDesignByID(long designID) {
        return Optional.ofNullable(createQuery().select(Q_DESIGN)
                .from(Q_DESIGN)
                .where(Q_DESIGN.id.eq(designID))
                .fetchOne());
    }
}
