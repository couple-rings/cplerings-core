package com.cplerings.core.test.shared.datasource;

import static com.cplerings.core.domain.design.QCustomDesign.customDesign;

import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.account.AccountVerification;
import com.cplerings.core.domain.branch.Branch;
import com.cplerings.core.domain.contract.Contract;
import com.cplerings.core.domain.crafting.CraftingStage;
import com.cplerings.core.domain.design.CustomDesign;
import com.cplerings.core.domain.design.DesignVersion;
import com.cplerings.core.domain.design.crafting.CraftingRequest;
import com.cplerings.core.domain.design.request.CustomRequest;
import com.cplerings.core.domain.design.request.DesignCustomRequest;
import com.cplerings.core.domain.design.session.DesignSession;
import com.cplerings.core.domain.order.CustomOrder;
import com.cplerings.core.domain.order.TransportationOrder;
import com.cplerings.core.domain.payment.Payment;
import com.cplerings.core.domain.payment.PaymentReceiver;
import com.cplerings.core.domain.ring.Ring;
import com.cplerings.core.domain.spouse.Spouse;
import com.cplerings.core.domain.spouse.SpouseAccount;
import com.cplerings.core.infrastructure.datasource.AbstractDataSource;
import com.cplerings.core.infrastructure.repository.AccountRepository;
import com.cplerings.core.infrastructure.repository.AccountVerificationRepository;
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
import com.cplerings.core.infrastructure.repository.PaymentReceiverRepository;
import com.cplerings.core.infrastructure.repository.PaymentRepository;
import com.cplerings.core.infrastructure.repository.RingRepository;
import com.cplerings.core.infrastructure.repository.SpouseAccountRepository;
import com.cplerings.core.infrastructure.repository.SpouseRepository;
import com.cplerings.core.infrastructure.repository.TransportationOrderRepository;

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
