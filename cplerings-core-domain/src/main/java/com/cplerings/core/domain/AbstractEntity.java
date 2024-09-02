package com.cplerings.core.domain;

import java.time.LocalDateTime;
import java.util.Objects;

import com.cplerings.core.common.temporal.TemporalHelper;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    @Builder.Default
    private Integer id = NEW_ENTITY_ID;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_AT", nullable = false, updatable = false)
    @Builder.Default
    private LocalDateTime createdAt = TemporalHelper.getCurrentDateTimeUTC();

    @Column(name = "CREATE_BY", length = 50, nullable = false, updatable = false)
    private String createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "MODIFIED_AT")
    @Builder.Default
    private LocalDateTime modifiedAt = TemporalHelper.getCurrentDateTimeUTC();

    @Column(name = "MODIFIED_BY", length = 50)
    private String modifiedBy;

    @Version
    @Column(name = "VERSION")
    @Builder.Default
    private Integer version = DEFAULT_VERSION;

    @Override
    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if ((o == null) || (getClass() != o.getClass())) {
            return false;
        }
        AbstractEntity that = (AbstractEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(id);
    }
}
