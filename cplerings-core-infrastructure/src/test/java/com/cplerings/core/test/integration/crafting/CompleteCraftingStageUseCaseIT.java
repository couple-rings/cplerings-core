package com.cplerings.core.test.integration.crafting;

import com.cplerings.core.domain.branch.Branch;
import com.cplerings.core.domain.contract.Contract;
import com.cplerings.core.domain.crafting.CraftingStage;
import com.cplerings.core.domain.crafting.CraftingStageStatus;
import com.cplerings.core.domain.order.CustomOrder;
import com.cplerings.core.domain.order.CustomOrderStatus;
import com.cplerings.core.domain.ring.Ring;
import com.cplerings.core.domain.ring.RingStatus;
import com.cplerings.core.domain.shared.valueobject.Money;
import com.cplerings.core.domain.spouse.Spouse;
import com.cplerings.core.infrastructure.repository.AccountRepository;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.account.AccountTestConstant;
import com.cplerings.core.test.shared.datasource.TestDataSource;
import com.cplerings.core.test.shared.spouse.SpouseTestHelper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

class CompleteCraftingStageUseCaseIT extends AbstractIT {

    @Autowired
    private SpouseTestHelper spouseTestHelper;

    @Autowired
    private TestDataSource testDataSource;

    @Autowired
    private AccountRepository accountRepository;

    private CraftingStage firstCraftingStage;

    private CraftingStage secondCraftingStage;

    @BeforeEach
    public void setUpCraftingStage() {
        final Spouse[] spouses = spouseTestHelper.createSpouseFromCustomerEmail(AccountTestConstant.CUSTOMER_EMAIL);
        Contract contract = Contract.builder()
                .build();
        contract = testDataSource.save(contract);
        Branch branch = Branch.builder()
                .address("123 Hello")
                .phone("1234567890")
                .storeName("Hello")
                .build();
        branch = testDataSource.save(branch);
        Ring firstRing = Ring.builder()
                .status(RingStatus.NOT_AVAIL)
                .branch(branch)
                .spouse(spouses[0])
                .build();
        firstRing = testDataSource.save(firstRing);
        Ring secondRing = Ring.builder()
                .status(RingStatus.NOT_AVAIL)
                .branch(branch)
                .spouse(spouses[1])
                .build();
        secondRing = testDataSource.save(secondRing);
        CustomOrder customOrder = CustomOrder.builder()
                .customer(accountRepository.findByEmail(AccountTestConstant.CUSTOMER_EMAIL).orElse(null))
                .contract(contract)
                .status(CustomOrderStatus.PENDING)
                .totalPrice(Money.create(BigDecimal.valueOf(100000)))
                .firstRing(firstRing)
                .secondRing(secondRing)
                .build();
        customOrder = testDataSource.save(customOrder);

        CraftingStage firstCraftingStage = CraftingStage.builder()
                .customOrder(customOrder)
                .status(CraftingStageStatus.PENDING)
                .name("Stage 1")
                .progress(30)
                .build();
        this.firstCraftingStage = testDataSource.save(firstCraftingStage);
        CraftingStage secondCraftingStage = CraftingStage.builder()
                .customOrder(customOrder)
                .status(CraftingStageStatus.PENDING)
                .name("Stage 1")
                .progress(100)
                .build();
        this.secondCraftingStage = testDataSource.save(secondCraftingStage);
    }

    @Test
    void givenJeweler_whenCompleteCraftingStage() {

    }
}
