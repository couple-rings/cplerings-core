package com.cplerings.core.test.shared.spouse;

import com.cplerings.core.common.temporal.TemporalUtils;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.spouse.Spouse;
import com.cplerings.core.domain.spouse.SpouseAccount;
import com.cplerings.core.infrastructure.repository.AccountRepository;
import com.cplerings.core.test.shared.datasource.TestDataSource;

import lombok.RequiredArgsConstructor;

import org.springframework.boot.test.context.TestComponent;

import java.util.UUID;

@TestComponent
@RequiredArgsConstructor
public class SpouseTestHelper {

    private final AccountRepository accountRepository;
    private final TestDataSource testDataSource;

    public Spouse createSpouseFromCustomerEmail(String email) {
        final Account customer = accountRepository.findByEmail(email)
                .orElse(null);
        if (customer == null) {
            throw new RuntimeException("Customer not found");
        }
        Spouse spouse = Spouse.builder()
                .citizenId(SpouseTestConstant.PRIMARY_SPOUSE_CITIZEN_ID)
                .coupleId(UUID.randomUUID())
                .dateOfBirth(TemporalUtils.getCurrentInstantUTC())
                .fullName("John Doe")
                .build();
        spouse = testDataSource.save(spouse);
        SpouseAccount spouseAccount = SpouseAccount.builder()
                .customer(customer)
                .spouse(spouse)
                .build();
        spouse.setSpouseAccount(spouseAccount);
        testDataSource.save(spouseAccount);
        return spouse;
    }
}
