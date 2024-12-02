package com.cplerings.core.test.shared.order;

import com.cplerings.core.common.temporal.TemporalUtils;
import com.cplerings.core.domain.branch.Branch;
import com.cplerings.core.domain.contract.Contract;
import com.cplerings.core.domain.order.CustomOrder;
import com.cplerings.core.domain.order.CustomOrderStatus;
import com.cplerings.core.domain.ring.Ring;
import com.cplerings.core.domain.ring.RingStatus;
import com.cplerings.core.domain.shared.valueobject.Money;
import com.cplerings.core.infrastructure.repository.AccountRepository;
import com.cplerings.core.infrastructure.repository.BranchRepository;
import com.cplerings.core.infrastructure.repository.MetalSpecificationRepository;
import com.cplerings.core.test.shared.datasource.TestDataSource;
import com.cplerings.core.test.shared.design.CustomDesignSpouse;
import com.cplerings.core.test.shared.design.CustomDesignTestHelper;
import com.cplerings.core.test.shared.helper.BranchTestHelper;

import lombok.RequiredArgsConstructor;

import org.springframework.boot.test.context.TestComponent;

import java.math.BigDecimal;

@TestComponent
@RequiredArgsConstructor
public class CustomOrderTestHelper {

    private final TestDataSource testDataSource;
    private final AccountRepository accountRepository;
    private final BranchRepository branchRepository;
    private final CustomDesignTestHelper customDesignTestHelper;
    private final MetalSpecificationRepository metalSpecificationRepository;

    public CustomOrder createCustomOrder() {
        CustomDesignSpouse customDesignSpouse = customDesignTestHelper.createCustomDesignsAndSpouses();
        Ring firstRing = Ring.builder()
                .spouse(customDesignSpouse.spouses()[0])
                .maintenanceExpiredDate(TemporalUtils.getCurrentInstantUTC())
                .purchaseDate(TemporalUtils.getCurrentInstantUTC())
                .branch(branchRepository.getReferenceById(1L))
                .status(RingStatus.NOT_AVAIL)
                .customDesign(customDesignSpouse.customDesign().get(0))
                .fingerSize(15)
                .metalSpecification(metalSpecificationRepository.getReferenceById(1L))
                .price(Money.create(BigDecimal.valueOf(1000)))
                .build();
        Ring firstRingCreated = testDataSource.save(firstRing);
        Ring secondRing = Ring.builder()
                .spouse(customDesignSpouse.spouses()[1])
                .maintenanceExpiredDate(TemporalUtils.getCurrentInstantUTC())
                .purchaseDate(TemporalUtils.getCurrentInstantUTC())
                .branch(branchRepository.getReferenceById(1L))
                .status(RingStatus.NOT_AVAIL)
                .customDesign(customDesignSpouse.customDesign().get(1))
                .fingerSize(16)
                .metalSpecification(metalSpecificationRepository.getReferenceById(11L))
                .price(Money.create(BigDecimal.valueOf(1000)))
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

    public CustomOrder createCustomOrderDone() {
        CustomDesignSpouse customDesignSpouse = customDesignTestHelper.createCustomDesignsAndSpouses();
        Ring firstRing = Ring.builder()
                .spouse(customDesignSpouse.spouses()[0])
                .maintenanceExpiredDate(TemporalUtils.getCurrentInstantUTC())
                .purchaseDate(TemporalUtils.getCurrentInstantUTC())
                .branch(branchRepository.getReferenceById(1L))
                .status(RingStatus.NOT_AVAIL)
                .customDesign(customDesignSpouse.customDesign().get(0))
                .fingerSize(15)
                .metalSpecification(metalSpecificationRepository.getReferenceById(1L))
                .price(Money.create(BigDecimal.valueOf(1000)))
                .build();
        Ring firstRingCreated = testDataSource.save(firstRing);
        Ring secondRing = Ring.builder()
                .spouse(customDesignSpouse.spouses()[1])
                .maintenanceExpiredDate(TemporalUtils.getCurrentInstantUTC())
                .purchaseDate(TemporalUtils.getCurrentInstantUTC())
                .branch(branchRepository.getReferenceById(1L))
                .status(RingStatus.NOT_AVAIL)
                .customDesign(customDesignSpouse.customDesign().get(1))
                .fingerSize(16)
                .metalSpecification(metalSpecificationRepository.getReferenceById(11L))
                .price(Money.create(BigDecimal.valueOf(1000)))
                .build();
        Ring secondRingCreated = testDataSource.save(secondRing);
        Contract contract = Contract.builder().build();
        Contract contractCreated = testDataSource.save(contract);
        CustomOrder customOrder = CustomOrder.builder()
                .totalPrice(Money.create(BigDecimal.valueOf(120000)))
                .customer(accountRepository.getReferenceById(1L))
                .status(CustomOrderStatus.DONE)
                .firstRing(firstRingCreated)
                .secondRing(secondRingCreated)
                .contract(contractCreated)
                .jeweler(accountRepository.getReferenceById(41L))
                .build();
        return testDataSource.save(customOrder);
    }
}
