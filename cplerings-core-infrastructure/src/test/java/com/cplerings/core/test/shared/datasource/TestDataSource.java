package com.cplerings.core.test.shared.datasource;

import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.account.AccountVerification;
import com.cplerings.core.domain.address.TransportationAddress;
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

import java.util.Optional;

public interface TestDataSource {

    Payment save(Payment payment);

    PaymentReceiver save(PaymentReceiver paymentReceiver);

    DesignSession save(DesignSession designSession);

    Spouse save(Spouse spouse);

    SpouseAccount save(SpouseAccount spouseAccount);

    DesignVersion save(DesignVersion designVersion);

    Contract save(Contract contract);

    Branch save(Branch branch);

    Ring save(Ring ring);

    CustomOrder save(CustomOrder customOrder);

    CraftingStage save(CraftingStage craftingStage);

    CustomRequest save(CustomRequest customRequest);

    DesignCustomRequest save(DesignCustomRequest designCustomRequest);

    Account save(Account account);

    AccountVerification save(AccountVerification accountVerification);

    CustomDesign save(CustomDesign customDesign);

    CraftingRequest save(CraftingRequest craftingRequest);

    TransportationOrder save(TransportationOrder transportationOrder);

    TransportationAddress save(TransportationAddress transportationAddress);

    Optional<CraftingStage> findCraftingStageById(Long craftingStageId);
}
