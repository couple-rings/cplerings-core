package com.cplerings.core.domain;

import java.time.Instant;
import java.util.Objects;

import com.cplerings.core.common.temporal.TemporalUtils;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class AbstractEntity {

    private static final int NEW_ENTITY_ID = -1;
    private static final int DEFAULT_VERSION = 1;

    @Column(name = "CREATED_AT", nullable = false, updatable = false)
    @Builder.Default
    private Instant createdAt = TemporalUtils.getCurrentInstantUTC();

    @Column(name = "CREATE_BY", length = 50, nullable = false, updatable = false)
    private String createdBy;

    @Column(name = "MODIFIED_AT")
    @Builder.Default
    private Instant modifiedAt = TemporalUtils.getCurrentInstantUTC();

    @Column(name = "MODIFIED_BY", length = 50)
    private String modifiedBy;

    @Version
    @Column(name = "VERSION")
    @Builder.Default
    private Integer version = DEFAULT_VERSION;

    @PrePersist
    private void beforePersist() {
        if (createdAt == null) {
            this.createdAt = TemporalUtils.getCurrentInstantUTC();
        }
        if (version == null || version <= 0) {
            this.version = DEFAULT_VERSION;
        }
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

    @Override
    public final int hashCode() {
        return Objects.hashCode(getId());
    }
}
