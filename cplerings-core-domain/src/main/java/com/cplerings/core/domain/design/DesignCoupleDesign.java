package com.cplerings.core.domain.design;

import com.cplerings.core.common.database.DatabaseConstant;

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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "design_couple_design")
public class DesignCoupleDesign {

    private static final String DESIGN_COUPLE_DESIGN_SEQUENCE = "design_couple_design_seq";

    @Id
    @GeneratedValue(generator = DESIGN_COUPLE_DESIGN_SEQUENCE, strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = DESIGN_COUPLE_DESIGN_SEQUENCE, allocationSize = DatabaseConstant.SEQ_ALLOCATION_SIZE)
    @Column(name = "design_couple_design_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "design_id")
    private Design design;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "design_couple_id")
    private DesignCouple designCouple;
}