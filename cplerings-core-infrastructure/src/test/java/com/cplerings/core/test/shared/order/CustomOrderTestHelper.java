package com.cplerings.core.test.shared.order;

import com.cplerings.core.common.temporal.TemporalUtils;
import com.cplerings.core.domain.branch.Branch;
import com.cplerings.core.domain.contract.Contract;
import com.cplerings.core.domain.order.CustomOrder;
import com.cplerings.core.domain.order.CustomOrderStatus;
import com.cplerings.core.domain.order.StandardOrder;
import com.cplerings.core.domain.order.StandardOrderStatus;
import com.cplerings.core.domain.ring.Ring;
import com.cplerings.core.domain.ring.RingDiamond;
import com.cplerings.core.domain.ring.RingStatus;
import com.cplerings.core.domain.shared.valueobject.Money;
import com.cplerings.core.domain.spouse.Agreement;
import com.cplerings.core.infrastructure.repository.AccountRepository;
import com.cplerings.core.infrastructure.repository.BranchRepository;
import com.cplerings.core.infrastructure.repository.MetalSpecificationRepository;
import com.cplerings.core.test.shared.datasource.TestDataSource;
import com.cplerings.core.test.shared.design.CustomDesignSpouse;
import com.cplerings.core.test.shared.design.CustomDesignTestHelper;
import com.cplerings.core.test.shared.diamond.DiamondTestHelper;
import com.cplerings.core.test.shared.helper.BranchTestHelper;
import com.cplerings.core.test.shared.helper.FileTestHelper;

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
    private final FileTestHelper fileTestHelper;
    private final DiamondTestHelper diamondTestHelper;

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

    public CustomOrder createCompleteCustomOrder() {
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

        final RingDiamond firstRingDiamond = RingDiamond.builder()
                .ring(firstRing)
                .diamond(diamondTestHelper.createDiamond())
                .build();
        testDataSource.save(firstRingDiamond);

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

        final RingDiamond secondRingDiamond = RingDiamond.builder()
                .ring(secondRing)
                .diamond(diamondTestHelper.createDiamond())
                .build();
        testDataSource.save(secondRingDiamond);

        Contract contract = Contract.builder().build();
        Contract contractCreated = testDataSource.save(contract);

        final Agreement agreement = Agreement.builder()
                .customer(accountRepository.getReferenceById(1L))
                .mainName("John Doe")
                .partnerName("Jane Doe")
                .mainSignature(fileTestHelper.createImage())
                .partnerSignature(fileTestHelper.createImage())
                .signedDate(TemporalUtils.getCurrentInstantUTCExcludeTimePartAndBelow())
                .build();
        testDataSource.save(agreement);

        CustomOrder customOrder = CustomOrder.builder()
                .totalPrice(Money.create(BigDecimal.valueOf(120000)))
                .customer(accountRepository.getReferenceById(1L))
                .status(CustomOrderStatus.COMPLETED)
                .firstRing(firstRingCreated)
                .secondRing(secondRingCreated)
                .contract(contractCreated)
                .jeweler(accountRepository.getReferenceById(41L))
                .build();
        return testDataSource.save(customOrder);
    }
}
