package com.cplerings.core.test.integration.environment;

import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.account.QAccount;
import com.cplerings.core.domain.account.Role;
import com.cplerings.core.test.shared.AbstractIT;

import org.apache.commons.lang3.StringUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class DefaultAccountVerificationIT extends AbstractIT {

    private static final QAccount QACCOUNT = QAccount.account;

    @Test
    void givenDatabase_whenQueryToGetDefaultAccounts() {
        final List<Account> defaultAccounts = createQuery()
                .select(QACCOUNT)
                .from(QACCOUNT)
                .where(QACCOUNT.email.contains("@cplerings.com"))
                .fetch();

        thenEachAccountRoleHasOneAccount(defaultAccounts);
    }

    private void thenEachAccountRoleHasOneAccount(List<Account> accounts) {
        Assertions.assertThat(accounts)
                .hasSize(Role.values().length);
        Arrays.stream(Role.values()).forEach(role -> {
            final List<Account> roleAccounts = accounts.stream()
                    .filter(acc -> StringUtils.containsIgnoreCase(acc.getEmail(), role.name()))
                    .toList();
            Assertions.assertThat(roleAccounts)
                    .hasSize(1)
                    .first()
                    .isNotNull();
        });
    }
}
