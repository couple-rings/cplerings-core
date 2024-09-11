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
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
@Table(name = "RING_WARRANTY")
public class RingWarranty extends AbstractEntity {

    private static final String RING_WARRANTY_SEQUENCE = "RING_WARRANTY_SEQ";

    @Id
    @GeneratedValue(generator = RING_WARRANTY_SEQUENCE, strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = RING_WARRANTY_SEQUENCE, allocationSize = DomainConstant.DEFAULT_ALLOCATION_SIZE)
    @Column(name = "RING_WARRANTY_ID")
    private Long id;

    @Column(name = "START_TIME", nullable = false)
    private Instant startTime;

    @Column(name = "END_TIME", nullable = false)
    private Instant endTime;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "RING_ID")
    private Ring ring;
}
