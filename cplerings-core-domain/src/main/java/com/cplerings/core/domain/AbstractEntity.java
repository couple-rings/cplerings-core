package com.cplerings.core.domain;

import com.cplerings.core.common.database.DatabaseConstant;
import com.cplerings.core.common.temporal.TemporalUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import org.apache.commons.lang3.StringUtils;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Version;

import java.time.Instant;
import java.util.Objects;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class AbstractEntity {

    private static final int NEW_ENTITY_ID = -1;
    private static final int DEFAULT_VERSION = 1;

    @Builder.Default
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt = TemporalUtils.getCurrentInstantUTC();

    @Column(name = "create_by", nullable = false, updatable = false)
    private String createdBy;

    @Builder.Default
    @Column(name = "modified_at")
    private Instant modifiedAt = TemporalUtils.getCurrentInstantUTC();

    @Column(name = "modified_by")
    private String modifiedBy;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(name = "state", length = DatabaseConstant.DEFAULT_ENUM_LENGTH, nullable = false)
    private State state = State.ACTIVE;

    @Builder.Default
    @Version
    @Column(name = "opt_version", nullable = false)
    private Integer optVersion = DEFAULT_VERSION;

    @PrePersist
    private void beforePersist() {
        if (createdAt == null) {
            this.createdAt = TemporalUtils.getCurrentInstantUTC();
        }
        if (optVersion == null || optVersion <= 0) {
            this.optVersion = DEFAULT_VERSION;
        }
        if (state == null) {
            this.state = State.ACTIVE;
        }
    }

    public final void updateModification(Modifiable modifiable) {
        Objects.requireNonNull(modifiable, "Modifier must not be null");
        if (StringUtils.isBlank(modifiable.getModifierName())) {
            throw new IllegalArgumentException("Modifier name must not be blank");
        }
        setModifiedAt(TemporalUtils.getCurrentInstantUTC());
        setModifiedBy(modifiable.getModifierName());
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if ((o == null) || (getClass() != o.getClass())) {
            return false;
        }
        AbstractEntity that = (AbstractEntity) o;
        return Objects.equals(getId(), that.getId());
    }

    public abstract Long getId();

    public abstract void setId(Long id);
}
