package com.cplerings.core.test.shared.order;

import java.math.BigDecimal;

import org.springframework.boot.test.context.TestComponent;

import com.cplerings.core.common.temporal.TemporalUtils;
import com.cplerings.core.domain.branch.Branch;
import com.cplerings.core.domain.contract.Contract;
import com.cplerings.core.domain.order.CustomOrder;
import com.cplerings.core.domain.order.CustomOrderStatus;
import com.cplerings.core.domain.ring.Ring;
import com.cplerings.core.domain.ring.RingStatus;
import com.cplerings.core.domain.shared.valueobject.Money;
import com.cplerings.core.domain.spouse.Spouse;
import com.cplerings.core.infrastructure.repository.AccountRepository;
import com.cplerings.core.infrastructure.repository.DocumentRepository;
import com.cplerings.core.infrastructure.repository.ImageRepository;
import com.cplerings.core.test.shared.account.AccountTestConstant;
import com.cplerings.core.test.shared.datasource.TestDataSource;
import com.cplerings.core.test.shared.spouse.SpouseTestHelper;

import lombok.RequiredArgsConstructor;

@TestComponent
@RequiredArgsConstructor
public class CustomOrderTestHelper {

    private final TestDataSource testDataSource;
    private final DocumentRepository documentRepository;
    private final AccountRepository accountRepository;
    private final SpouseTestHelper spouseTestHelper;
    private final ImageRepository imageRepository;

    public CustomOrder createCustomOrder() {
        final Spouse[] spouses = spouseTestHelper.createSpouseFromCustomerEmail(AccountTestConstant.CUSTOMER_EMAIL);
        Branch branch = Branch.builder()
                .address("test")
                .coverImage(imageRepository.getReferenceById(1L))
                .storeName("test")
                .phone("test")
                .build();
        Branch branchCreated = testDataSource.save(branch);
        Ring firstRing = Ring.builder()
                .spouse(spouses[0])
                .maintenanceExpiredDate(TemporalUtils.getCurrentInstantUTC())
                .purchaseDate(TemporalUtils.getCurrentInstantUTC())
                .branch(branchCreated)
                .status(RingStatus.NOT_AVAIL)
                .build();
        Ring firstRingCreated = testDataSource.save(firstRing);
        Ring secondRing = Ring.builder()
                .spouse(spouses[1])
                .maintenanceExpiredDate(TemporalUtils.getCurrentInstantUTC())
                .purchaseDate(TemporalUtils.getCurrentInstantUTC())
                .branch(branchCreated)
                .status(RingStatus.NOT_AVAIL)
                .build();
        Ring secondRingCreated = testDataSource.save(secondRing);
        Contract contract = Contract.builder().build();
        Contract contractCreated = testDataSource.save(contract);
        CustomOrder customOrder = CustomOrder.builder()
                .totalPrice(Money.create(BigDecimal.valueOf(120000)))
                .customer(accountRepository.getReferenceById(1L))
                .status(CustomOrderStatus.PENDING)
                .firstRing(firstRingCreated)
                .secondRing(secondRingCreated)
                .contract(contractCreated)
                .jeweler(accountRepository.getReferenceById(41L))
                .build();
        return testDataSource.save(customOrder);
    }
}
