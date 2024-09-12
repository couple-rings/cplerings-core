package com.cplerings.core.domain.ring;

import com.cplerings.core.domain.AbstractEntity;
import com.cplerings.core.domain.DomainConstant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import java.time.Instant;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "WARRANTY")
public class Warranty extends AbstractEntity {

    private static final String WARRANTY_SEQUENCE = "WARRANTY_SEQ";

    @Id
    @GeneratedValue(generator = WARRANTY_SEQUENCE, strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = WARRANTY_SEQUENCE, allocationSize = DomainConstant.DEFAULT_ALLOCATION_SIZE)
    @Column(name = "WARRANTY_ID")
    private Long id;

    @Column(name = "START_TIME", nullable = false)
    private Instant startTime;

    @Column(name = "END_TIME")
    private Instant endTime;

    @OneToOne(mappedBy = "warranty")
    private Ring ring;
}
