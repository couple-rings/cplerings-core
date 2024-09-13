package com.cplerings.core.domain.ring;

import java.time.Instant;

import com.cplerings.core.common.database.DatabaseConstant;
import com.cplerings.core.domain.AbstractEntity;
import com.cplerings.core.domain.DomainConstant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
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
@Table(name = "tbl_warranty", schema = DatabaseConstant.SCHEME_CORE)
public class Warranty extends AbstractEntity {

    private static final String WARRANTY_SEQUENCE = "warranty_seq";

    @Id
    @GeneratedValue(generator = WARRANTY_SEQUENCE, strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = WARRANTY_SEQUENCE, allocationSize = DomainConstant.DEFAULT_ALLOCATION_SIZE)
    @Column(name = "warranty_id")
    private Long id;

    @Column(name = "start_time", nullable = false)
    private Instant startTime;

    @Column(name = "end_time")
    private Instant endTime;

    @OneToOne(mappedBy = "warranty")
    private Ring ring;
}
