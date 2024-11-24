package com.cplerings.core.test.shared.datasource;

import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.account.AccountVerification;
import com.cplerings.core.domain.account.QAccount;
import com.cplerings.core.domain.address.TransportationAddress;
import com.cplerings.core.domain.branch.Branch;
import com.cplerings.core.domain.branch.QBranch;
import com.cplerings.core.domain.contract.Contract;
import com.cplerings.core.domain.crafting.CraftingStage;
import com.cplerings.core.domain.crafting.QCraftingStage;
import com.cplerings.core.domain.design.CustomDesign;
import com.cplerings.core.domain.design.DesignVersion;
import com.cplerings.core.domain.design.crafting.CraftingRequest;
import com.cplerings.core.domain.design.request.CustomRequest;
import com.cplerings.core.domain.design.request.DesignCustomRequest;
import com.cplerings.core.domain.design.session.DesignSession;
import com.cplerings.core.domain.diamond.Diamond;
import com.cplerings.core.domain.file.Document;
import com.cplerings.core.domain.file.Image;
import com.cplerings.core.domain.order.CustomOrder;
import com.cplerings.core.domain.order.TransportationOrder;
import com.cplerings.core.domain.order.status.TransportationNote;
import com.cplerings.core.domain.payment.Payment;
import com.cplerings.core.domain.payment.PaymentReceiver;
import com.cplerings.core.domain.ring.Ring;
import com.cplerings.core.domain.spouse.Agreement;
import com.cplerings.core.domain.spouse.QAgreement;
import com.cplerings.core.domain.spouse.Spouse;
import com.cplerings.core.domain.spouse.SpouseAccount;
import com.cplerings.core.infrastructure.datasource.AbstractDataSource;
import com.cplerings.core.infrastructure.repository.AccountRepository;
import com.cplerings.core.infrastructure.repository.AccountVerificationRepository;
import com.cplerings.core.infrastructure.repository.AgreementRepository;
import com.cplerings.core.infrastructure.repository.BranchRepository;
import com.cplerings.core.infrastructure.repository.ContractRepository;
import com.cplerings.core.infrastructure.repository.CraftingRequestRepository;
import com.cplerings.core.infrastructure.repository.CraftingStageRepository;
import com.cplerings.core.infrastructure.repository.CustomDesignRepository;
import com.cplerings.core.infrastructure.repository.CustomOrderRepository;
import com.cplerings.core.infrastructure.repository.CustomRequestRepository;
import com.cplerings.core.infrastructure.repository.DesignCustomRequestRepository;
import com.cplerings.core.infrastructure.repository.DesignSessionRepository;
import com.cplerings.core.infrastructure.repository.DesignVersionRepository;
import com.cplerings.core.infrastructure.repository.DiamondRepository;
import com.cplerings.core.infrastructure.repository.DocumentRepository;
import com.cplerings.core.infrastructure.repository.ImageRepository;
import com.cplerings.core.infrastructure.repository.PaymentReceiverRepository;
import com.cplerings.core.infrastructure.repository.PaymentRepository;
import com.cplerings.core.infrastructure.repository.RingRepository;
import com.cplerings.core.infrastructure.repository.SpouseAccountRepository;
import com.cplerings.core.infrastructure.repository.SpouseRepository;
import com.cplerings.core.infrastructure.repository.TransportationAddressRepository;
import com.cplerings.core.infrastructure.repository.TransportationNoteRepository;
import com.cplerings.core.infrastructure.repository.TransportationOrderRepository;
import com.cplerings.core.test.shared.entity.order.DummyOrder;
import com.cplerings.core.test.shared.entity.order.DummyOrderRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.boot.test.context.TestComponent;

import java.util.Collection;
import java.util.Optional;

@TestComponent
@RequiredArgsConstructor
public class SharedTestDataSource extends AbstractDataSource implements TestDataSource {

    private static final QCraftingStage Q_CRAFTING_STAGE = QCraftingStage.craftingStage;
    private static final QAccount Q_ACCOUNT = QAccount.account;
    private static final QBranch Q_BRANCH = QBranch.branch;
    private static final QAgreement Q_AGREEMENT = QAgreement.agreement;

    private final PaymentRepository paymentRepository;
    private final PaymentReceiverRepository paymentReceiverRepository;
    private final DesignSessionRepository designSessionRepository;
    private final SpouseRepository spouseRepository;
    private final SpouseAccountRepository spouseAccountRepository;
    private final DesignVersionRepository designVersionRepository;
    private final ContractRepository contractRepository;
    private final BranchRepository branchRepository;
    private final RingRepository ringRepository;
    private final CustomOrderRepository customOrderRepository;
    private final CraftingStageRepository craftingStageRepository;
    private final CustomRequestRepository customRequestRepository;
    private final DesignCustomRequestRepository designCustomRequestRepository;
    private final AccountRepository accountRepository;
    private final AccountVerificationRepository accountVerificationRepository;
    private final CustomDesignRepository customDesignRepository;
    private final CraftingRequestRepository craftingRequestRepository;
    private final TransportationOrderRepository transportationOrderRepository;
    private final TransportationAddressRepository transportationAddressRepository;
    private final DummyOrderRepository dummyOrderRepository;
    private final DocumentRepository documentRepository;
    private final AgreementRepository agreementRepository;
    private final DiamondRepository diamondRepository;
    private final ImageRepository imageRepository;
    private final TransportationNoteRepository transportationNoteRepository;

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

    @Override
    public CustomRequest save(CustomRequest customRequest) {
        updateAuditor(customRequest);
        return customRequestRepository.save(customRequest);
    }

    @Override
    public DesignCustomRequest save(DesignCustomRequest designCustomRequest) {
        updateAuditor(designCustomRequest);
        return designCustomRequestRepository.save(designCustomRequest);
    }

    @Override
    public Account save(Account account) {
        updateAuditor(account);
        return accountRepository.save(account);
    }

    @Override
    public AccountVerification save(AccountVerification accountVerification) {
        updateAuditor(accountVerification);
        return accountVerificationRepository.save(accountVerification);
    }

    @Override
    public CustomDesign save(CustomDesign customDesign) {
        updateAuditor(customDesign);
        return customDesignRepository.save(customDesign);
    }

    @Override
    public CraftingRequest save(CraftingRequest craftingRequest) {
        updateAuditor(craftingRequest);
        return craftingRequestRepository.save(craftingRequest);
    }

    @Override
    public TransportationOrder save(TransportationOrder transportationOrder) {
        updateAuditor(transportationOrder);
        return transportationOrderRepository.save(transportationOrder);
    }

    @Override
    public TransportationAddress save(TransportationAddress transportationAddress) {
        updateAuditor(transportationAddress);
        return transportationAddressRepository.save(transportationAddress);
    }

    @Override
    public Optional<CraftingStage> findCraftingStageById(Long craftingStageId) {
        return Optional.ofNullable(createQuery().select(Q_CRAFTING_STAGE)
                .from(Q_CRAFTING_STAGE)
                .leftJoin(Q_CRAFTING_STAGE.customOrder).fetchJoin()
                .leftJoin(Q_CRAFTING_STAGE.customOrder.transportationAddress).fetchJoin()
                .where(Q_CRAFTING_STAGE.id.eq(craftingStageId))
                .fetchOne());
    }

    @Override
    public Account getTransporterWithBranch(Long id) {
        return createQuery()
                .select(Q_ACCOUNT)
                .from(Q_ACCOUNT)
                .leftJoin(Q_ACCOUNT.branch, Q_BRANCH).fetchJoin()
                .where(Q_ACCOUNT.id.eq(id))
                .fetchOne();
    }

    @Override
    public DummyOrder save(DummyOrder dummyOrder) {
        updateAuditor(dummyOrder);
        return dummyOrderRepository.save(dummyOrder);
    }

    @Override
    public Document save(Document document) {
        updateAuditor(document);
        return documentRepository.save(document);
    }

    @Override
    public Collection<Ring> findAllRingsByIds(Collection<Long> ringIds) {
        return ringRepository.findAllById(ringIds);
    }

    @Override
    public Agreement save(Agreement agreement) {
        updateAuditor(agreement);
        return agreementRepository.save(agreement);
    }

    @Override
    public Diamond save(Diamond diamond) {
        updateAuditor(diamond);
        return diamondRepository.save(diamond);
    }

    @Override
    public Image save(Image image) {
        updateAuditor(image);
        return imageRepository.save(image);
    }

    @Override
    public Optional<Agreement> findAgreementById(Long agreementId) {
        return Optional.ofNullable(createQuery().select(Q_AGREEMENT)
                .from(Q_AGREEMENT)
                .leftJoin(Q_AGREEMENT.mainSignature).fetchJoin()
                .leftJoin(Q_AGREEMENT.partnerSignature).fetchJoin()
                .leftJoin(Q_AGREEMENT.customer).fetchJoin()
                .where(Q_AGREEMENT.id.eq(agreementId))
                .fetchFirst());
    }

    @Override
    public TransportationNote save(TransportationNote transportationNote) {
        updateAuditor(transportationNote);
        return transportationNoteRepository.save(transportationNote);
    }

    @Override
    public Contract save(Contract contract) {
        updateAuditor(contract);
        return contractRepository.save(contract);
    }

    @Override
    public Branch save(Branch branch) {
        updateAuditor(branch);
        return branchRepository.save(branch);
    }

    @Override
    public Ring save(Ring ring) {
        updateAuditor(ring);
        return ringRepository.save(ring);
    }

    @Override
    public CustomOrder save(CustomOrder customOrder) {
        updateAuditor(customOrder);
        return customOrderRepository.save(customOrder);
    }

    @Override
    public CraftingStage save(CraftingStage craftingStage) {
        updateAuditor(craftingStage);
        return craftingStageRepository.save(craftingStage);
    }
}
