package com.cplerings.core.domain.transportation;

import com.cplerings.core.common.database.DatabaseConstant;
import com.cplerings.core.domain.AbstractEntity;
import com.cplerings.core.domain.DomainConstant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_transportation_tracing", schema = DatabaseConstant.SCHEME_CORE)
public class TransportationTracing extends AbstractEntity {

    private static final String TRANSPORTATION_TRACING_SEQUENCE = "transportation_tracing_seq";

    @Id
    @GeneratedValue(generator = TRANSPORTATION_TRACING_SEQUENCE, strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = TRANSPORTATION_TRACING_SEQUENCE, allocationSize = DomainConstant.DEFAULT_ALLOCATION_SIZE)
    @Column(name = "transportation_tracing_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 12, nullable = false)
    private TransportationStatus status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "transportation_id")
    private Transportation transportation;
}
