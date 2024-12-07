package com.cplerings.core.test.shared.order;

import java.math.BigDecimal;

import org.springframework.boot.test.context.TestComponent;

import com.cplerings.core.domain.order.StandardOrder;
import com.cplerings.core.domain.order.StandardOrderStatus;
import com.cplerings.core.domain.shared.valueobject.Money;
import com.cplerings.core.infrastructure.repository.AccountRepository;
import com.cplerings.core.test.shared.datasource.TestDataSource;

import lombok.RequiredArgsConstructor;

@TestComponent
@RequiredArgsConstructor
public class StandardOrderTestHelper {

    private final AccountRepository accountRepository;
    private final TestDataSource testDataSource;

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
}
