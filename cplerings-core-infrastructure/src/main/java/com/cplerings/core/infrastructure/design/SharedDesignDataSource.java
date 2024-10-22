package com.cplerings.core.infrastructure.design;

import java.util.List;
import java.util.Optional;

import com.cplerings.core.application.design.datasource.CreateDesignSessionDataSource;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.account.QAccount;
import com.cplerings.core.domain.design.DesignSession;
import com.cplerings.core.domain.design.QDesignSession;
import com.cplerings.core.infrastructure.datasource.AbstractDataSource;
import com.cplerings.core.infrastructure.datasource.DataSource;
import com.cplerings.core.infrastructure.repository.DesignSessionRepository;

import lombok.RequiredArgsConstructor;

@DataSource
@RequiredArgsConstructor
public class SharedDesignDataSource extends AbstractDataSource
                implements CreateDesignSessionDataSource {

    private static final QDesignSession Q_DESIGN_SESSION = QDesignSession.designSession;
    private static final QAccount Q_ACCOUNT = QAccount.account;

    private final DesignSessionRepository designSessionRepository;

    @Override
    public void createDesignSession(List<DesignSession> designSessions) {
        for (DesignSession designSession : designSessions) {
            updateAuditor(designSession);
        }
        designSessionRepository.saveAll(designSessions);
    }

    @Override
    public Optional<Account> getAccountById(Long id) {
        return Optional.ofNullable(createQuery()
                .select(Q_ACCOUNT)
                .from(Q_ACCOUNT)
                .where(Q_ACCOUNT.id.eq(id))
                .fetchOne());
    }
}
