package com.cplerings.core.test.shared.order;

import java.math.BigDecimal;

import org.springframework.boot.test.context.TestComponent;

import com.cplerings.core.domain.file.Image;
import com.cplerings.core.domain.order.StandardOrder;
import com.cplerings.core.domain.refund.Refund;
import com.cplerings.core.domain.refund.RefundMethod;
import com.cplerings.core.domain.shared.valueobject.Money;
import com.cplerings.core.infrastructure.repository.AccountRepository;
import com.cplerings.core.infrastructure.repository.ImageRepository;
import com.cplerings.core.test.shared.datasource.TestDataSource;

import lombok.RequiredArgsConstructor;

@TestComponent
@RequiredArgsConstructor
public class RefundTestHelper {

    private final TestDataSource testDataSource;
    private final AccountRepository accountRepository;
    private final ImageRepository imageRepository;
    private final StandardOrderTestHelper standardOrderTestHelper;

    public Refund createRefund() {
        StandardOrder standardOrder = standardOrderTestHelper.createStandardOrder();
        Refund refund = Refund.builder()
                .method(RefundMethod.CASH)
                .staff(accountRepository.getReferenceById(21L))
                .reason("test")
                .amount(Money.create(BigDecimal.valueOf(1000)))
                .proofImage(imageRepository.getReferenceById(1L))
                .standardOrder(standardOrder)
                .build();
        return testDataSource.save(refund);
    }
}
