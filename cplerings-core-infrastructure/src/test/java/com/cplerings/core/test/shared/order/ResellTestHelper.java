package com.cplerings.core.test.shared.order;

import java.math.BigDecimal;

import org.springframework.boot.test.context.TestComponent;

import com.cplerings.core.domain.resell.PaymentMethod;
import com.cplerings.core.domain.resell.ResellOrder;
import com.cplerings.core.domain.shared.valueobject.Money;
import com.cplerings.core.infrastructure.repository.AccountRepository;
import com.cplerings.core.infrastructure.repository.ImageRepository;
import com.cplerings.core.test.shared.datasource.TestDataSource;
import com.cplerings.core.test.shared.jewelry.JewelryTestHelper;

import lombok.RequiredArgsConstructor;

@TestComponent
@RequiredArgsConstructor
public class ResellTestHelper {

    private final TestDataSource testDataSource;
    private final AccountRepository accountRepository;
    private final ImageRepository imageRepository;
    private final JewelryTestHelper jewelryTestHelper;

    public ResellOrder createResellOrder() {
        ResellOrder resellOrder = ResellOrder.builder()
                .jewelry(jewelryTestHelper.createJewelry())
                .amount(Money.create(BigDecimal.valueOf(1000)))
                .customer(accountRepository.getReferenceById(1L))
                .staff(accountRepository.getReferenceById(21L))
                .note("test")
                .paymentMethod(PaymentMethod.CASH)
                .proofImage(imageRepository.getReferenceById(1L))
                .build();
        return testDataSource.save(resellOrder);
    }
}
