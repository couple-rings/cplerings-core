package com.cplerings.core.domain.agreement;

import java.time.Instant;
import java.util.Set;

import com.cplerings.core.common.database.DatabaseConstant;
import com.cplerings.core.domain.AbstractEntity;
import com.cplerings.core.domain.DomainConstant;
import com.cplerings.core.domain.ring.Ring;
import com.cplerings.core.domain.spouse.Spouse;

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
@Table(name = "tbl_agreement", schema = DatabaseConstant.SCHEME_CORE)
public class Agreement extends AbstractEntity {

    private static final String AGREEMENT_SEQUENCE = "agreement_seq";

    @Id
    @GeneratedValue(generator = AGREEMENT_SEQUENCE, strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = AGREEMENT_SEQUENCE, allocationSize = DomainConstant.DEFAULT_ALLOCATION_SIZE)
    @Column(name = "agreement_id")
    private Long id;

    @Column(name = "marriage_date", nullable = false)
    private Instant marriageDate;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "first_ring_id")
    private Ring firstRing;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "second_ring_id")
    private Ring secondRing;

    @OneToMany(mappedBy = "agreement")
    private Set<Spouse> spouses;
}
