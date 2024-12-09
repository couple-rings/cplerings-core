package com.cplerings.core.test.shared.order;

import com.cplerings.core.domain.jewelry.Jewelry;
import com.cplerings.core.domain.order.StandardOrder;
import com.cplerings.core.domain.order.StandardOrderItem;
import com.cplerings.core.domain.order.StandardOrderStatus;
import com.cplerings.core.domain.shared.valueobject.Money;
import com.cplerings.core.infrastructure.repository.AccountRepository;
import com.cplerings.core.test.shared.datasource.TestDataSource;
import com.cplerings.core.test.shared.jewelry.JewelryTestHelper;

import lombok.RequiredArgsConstructor;

import org.springframework.boot.test.context.TestComponent;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

@TestComponent
@RequiredArgsConstructor
public class StandardOrderTestHelper {

    private final AccountRepository accountRepository;
    private final TestDataSource testDataSource;
    private final JewelryTestHelper jewelryTestHelper;

    public StandardOrder createStandardOrder() {
        StandardOrder standardOrder = StandardOrder.builder()
                .totalPrice(Money.create(BigDecimal.valueOf(100)))
                .status(StandardOrderStatus.PENDING)
                .customer(accountRepository.getReferenceById(1L))
                .build();
        return testDataSource.save(standardOrder);
    }

    public StandardOrder createPaidStandardOrder() {
        StandardOrder standardOrder = StandardOrder.builder()
                .totalPrice(Money.create(BigDecimal.valueOf(100)))
                .status(StandardOrderStatus.PAID)
                .customer(accountRepository.getReferenceById(1L))
                .build();
        return testDataSource.save(standardOrder);
    }

    public StandardOrder createCompleteStandardOrder() {
        StandardOrder standardOrder = StandardOrder.builder()
                .totalPrice(Money.create(BigDecimal.valueOf(100)))
                .status(StandardOrderStatus.COMPLETED)
                .customer(accountRepository.getReferenceById(1L))
                .build();
        return testDataSource.save(standardOrder);
    }

    public StandardOrder createStandardOrderWithJewelries() {
        final Collection<Jewelry> jewelries = new ArrayList<>();
        createJewelriesWithSameSpec(jewelries);

        final StandardOrder standardOrder = createStandardOrder();
        jewelries.forEach(jewelry -> {
            final StandardOrderItem standardOrderItem = StandardOrderItem.builder()
                    .standardOrder(standardOrder)
                    .branch(jewelry.getBranch())
                    .design(jewelry.getDesign())
                    .metalSpecification(jewelry.getMetalSpecification())
                    .price(Money.create(BigDecimal.valueOf(100)))
                    .build();
            testDataSource.save(standardOrderItem);
        });

        return standardOrder;
    }

    private void createJewelriesWithSameSpec(Collection<Jewelry> jewelries) {
        jewelries.add(jewelryTestHelper.createJewelry());
        jewelries.add(jewelryTestHelper.createJewelry());
    }
}
