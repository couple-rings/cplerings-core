package com.cplerings.core.test.shared.helper;

import com.cplerings.core.common.temporal.TemporalUtils;
import com.cplerings.core.domain.spouse.Agreement;
import com.cplerings.core.infrastructure.repository.AccountRepository;
import com.cplerings.core.test.shared.account.AccountTestConstant;
import com.cplerings.core.test.shared.datasource.TestDataSource;

import lombok.RequiredArgsConstructor;

import org.springframework.boot.test.context.TestComponent;

@TestComponent
@RequiredArgsConstructor
public class AgreementTestHelper {

    private final TestDataSource testDataSource;
    private final AccountRepository accountRepository;
    private final FileTestHelper fileTestHelper;
    private final AccountTestHelper accountTestHelper;

    public Agreement createUnsignedAgreement() {
        final Agreement agreement = Agreement.builder()
                .customer(accountRepository.findByEmail(AccountTestConstant.CUSTOMER_EMAIL)
                        .orElseThrow(() -> new IllegalStateException("Customer not found")))
                .build();
        return testDataSource.save(agreement);
    }

    public Agreement createSignedAgreement() {
        final Agreement agreement = Agreement.builder()
                .customer(accountRepository.findByEmail(AccountTestConstant.CUSTOMER_EMAIL)
                        .orElseThrow(() -> new IllegalStateException("Customer not found")))
                .mainName("John Smith")
                .mainSignature(fileTestHelper.createImage())
                .partnerName("Jane Smith")
                .partnerSignature(fileTestHelper.createImage())
                .signedDate(TemporalUtils.getCurrentInstantUTC())
                .build();
        return testDataSource.save(agreement);
    }
}
