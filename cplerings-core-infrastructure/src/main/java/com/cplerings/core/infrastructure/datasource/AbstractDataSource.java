package com.cplerings.core.infrastructure.datasource;

import com.cplerings.core.domain.shared.AbstractEntity;
import com.cplerings.core.domain.shared.Auditor;
import com.cplerings.core.infrastructure.security.SecurityHelper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.blazebit.persistence.CriteriaBuilderFactory;
import com.blazebit.persistence.querydsl.BlazeJPAQuery;
import com.querydsl.core.types.dsl.BooleanExpression;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.Objects;
import java.util.Optional;

@DataSource
public abstract class AbstractDataSource {

    @PersistenceContext
    protected EntityManager entityManager;

    protected CriteriaBuilderFactory cbf;

    protected SecurityHelper securityHelper;

    @Value("${spring.application.name}")
    private String systemName;

    protected final <T> BlazeJPAQuery<T> createQuery() {
        return new BlazeJPAQuery<>(entityManager, cbf);
    }

    protected final <T extends AbstractEntity> void updateAuditor(T entity) {
        Objects.requireNonNull(entity, "Entity is null");
        final Optional<Auditor> currentAuditor = securityHelper.getCurrentAuditor();
        if (currentAuditor.isPresent()) {
            // Use currently logged in Auditor
            final Auditor auditor = currentAuditor.get();
            if (entityIsNew(entity) || StringUtils.isBlank(entity.getCreatedBy()) || entity.getCreatedAt() == null) {
                entity.setCreator(auditor);
            }
            entity.updateModifier(auditor);
        } else {
            // Default to the system as Auditor
            final Auditor systemAuditor = (() -> systemName);
            if (entityIsNew(entity) || StringUtils.isBlank(entity.getCreatedBy()) || entity.getCreatedAt() == null) {
                entity.setCreator(systemAuditor);
            }
            entity.updateModifier(systemAuditor);
        }
    }

    private <T extends AbstractEntity> boolean entityIsNew(T entity) {
        return ((entity.getId() == null) || (entity.getId() <= 0));
    }

    protected final BooleanExpressionBuilder createBooleanExpressionBuilder() {
        return new BooleanExpressionBuilder();
    }

    @Autowired
    public final void setCriteriaBuilderFactory(CriteriaBuilderFactory cbf) {
        this.cbf = cbf;
    }

    @Autowired
    public void setSecurityHelper(SecurityHelper securityHelper) {
        this.securityHelper = securityHelper;
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    protected static final class BooleanExpressionBuilder {

        private BooleanExpression predicate;

        public BooleanExpressionBuilder and(BooleanExpression predicate) {
            if (predicate == null) {
                return this;
            }
            if (this.predicate == null) {
                this.predicate = predicate;
            } else {
                this.predicate = this.predicate.and(predicate);
            }
            return this;
        }

        public BooleanExpressionBuilder or(BooleanExpression predicate) {
            if (predicate == null) {
                return this;
            }
            if (this.predicate == null) {
                this.predicate = predicate;
            } else {
                this.predicate = this.predicate.or(predicate);
            }
            return this;
        }

        public BooleanExpression build() {
            return this.predicate;
        }
    }
}
