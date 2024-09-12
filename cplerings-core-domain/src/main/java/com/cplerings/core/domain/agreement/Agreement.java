package com.cplerings.core.domain.agreement;

import com.cplerings.core.domain.AbstractEntity;
import com.cplerings.core.domain.DomainConstant;
import com.cplerings.core.domain.ring.Ring;

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
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import java.time.Instant;
import java.util.Set;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "AGREEMENT")
public class Agreement extends AbstractEntity {

    private static final String AGREEMENT_SEQUENCE = "AGREEMENT_SEQ";

    @Id
    @GeneratedValue(generator = AGREEMENT_SEQUENCE, strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = AGREEMENT_SEQUENCE, allocationSize = DomainConstant.DEFAULT_ALLOCATION_SIZE)
    @Column(name = "AGREEMENT_ID")
    private Long id;

    @Column(name = "MARRIAGE_DATE", nullable = false)
    private Instant marriageDate;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "FIRST_RING_ID")
    private Ring firstRing;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "SECOND_RING_ID")
    private Ring secondRing;

    @OneToMany(mappedBy = "agreement")
    private Set<Spouse> spouses;
}
