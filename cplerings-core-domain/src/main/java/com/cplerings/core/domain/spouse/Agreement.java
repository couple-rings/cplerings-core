package com.cplerings.core.domain.spouse;

import com.cplerings.core.common.database.DatabaseConstant;
import com.cplerings.core.domain.AbstractEntity;

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
@Table(name = "agreement")
public class Agreement extends AbstractEntity {

    private static final String AGREEMENT_SEQUENCE = "agreement_seq";

    @Id
    @GeneratedValue(generator = AGREEMENT_SEQUENCE, strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = AGREEMENT_SEQUENCE, allocationSize = DatabaseConstant.SEQ_ALLOCATION_SIZE)
    @Column(name = "agreement_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "main_spouse_id", unique = true)
    private Spouse mainSpouse;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "second_spouse_id", unique = true)
    private Spouse secondSpouse;

    @Column(name = "signed_date", nullable = false)
    private Instant signedDate;
}
