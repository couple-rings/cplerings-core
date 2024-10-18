package com.cplerings.core.infrastructure.datasource.spouseaccount;

import com.cplerings.core.domain.spouse.QSpouseAccount;
import com.cplerings.core.domain.spouse.SpouseAccount;
import com.cplerings.core.infrastructure.datasource.AbstractDataSource;
import com.cplerings.core.infrastructure.datasource.DataSource;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

@DataSource
@RequiredArgsConstructor
public class SharedSpouseAccountDataSource extends AbstractDataSource {

    private static final QSpouseAccount Q_SPOUSE_ACCOUNT = QSpouseAccount.spouseAccount;

    public Optional<SpouseAccount> findBySpouseAndCustomer(Long spouseId, Long customerId) {
        return Optional.ofNullable(createQuery()
                .select(Q_SPOUSE_ACCOUNT)
                .from(Q_SPOUSE_ACCOUNT)
                .where(Q_SPOUSE_ACCOUNT.spouse.id.eq(spouseId)
                        .and(Q_SPOUSE_ACCOUNT.customer.id.eq(customerId)))
                .fetchOne());
    }
}
