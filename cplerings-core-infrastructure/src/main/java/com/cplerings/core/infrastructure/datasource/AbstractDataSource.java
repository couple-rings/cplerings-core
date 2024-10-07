package com.cplerings.core.infrastructure.datasource;

import com.cplerings.core.domain.AbstractEntity;
import com.cplerings.core.domain.Auditor;
import com.cplerings.core.infrastructure.security.SecurityHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.blazebit.persistence.CriteriaBuilderFactory;
import com.blazebit.persistence.querydsl.BlazeJPAQuery;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.Objects;
import java.util.Optional;

@DataSource
public abstract class AbstractDataSource {

    @PersistenceContext
    protected EntityManager em;

    protected CriteriaBuilderFactory cbf;
    protected SecurityHelper securityHelper;

    @Value("${spring.application.name}")
    private String systemName;

    protected final <T> BlazeJPAQuery<T> createQuery() {
        return new BlazeJPAQuery<>(em, cbf);
    }

    protected final <T extends AbstractEntity> void updateAuditor(T entity) {
        Objects.requireNonNull(entity, "Entity is null");
        final Optional<Auditor> currentAuditor = securityHelper.getCurrentAuditor();
        if (currentAuditor.isPresent()) {
            // Use currently logged in Auditor
            final Auditor auditor = currentAuditor.get();
            if (entityIsNew(entity)) {
                entity.setCreator(auditor);
            }
            entity.updateModifier(auditor);
        } else {
            // Default to the system as Auditor
            final Auditor systemAuditor = (() -> systemName);
            if (entityIsNew(entity)) {
                entity.setCreator(systemAuditor);
            }
            entity.updateModifier(systemAuditor);
        }
    }

    private <T extends AbstractEntity> boolean entityIsNew(T entity) {
        return ((entity.getId() == null) || (entity.getId() <= 0));
    }

    @Autowired
    public final void setCriteriaBuilderFactory(CriteriaBuilderFactory cbf) {
        this.cbf = cbf;
    }

    @Autowired
    public void setSecurityHelper(SecurityHelper securityHelper) {
        this.securityHelper = securityHelper;
    }
}
