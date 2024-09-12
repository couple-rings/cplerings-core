package com.cplerings.core.domain.ring;

import com.cplerings.core.domain.AbstractEntity;
import com.cplerings.core.domain.DomainConstant;
import com.cplerings.core.domain.design.Design;
import com.cplerings.core.domain.diamond.Diamond;
import com.cplerings.core.domain.metal.Metal;

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
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "RING")
public class Ring extends AbstractEntity {

    private static final String RING_SEQUENCE = "RING_SEQ";

    @Id
    @GeneratedValue(generator = RING_SEQUENCE, strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = RING_SEQUENCE, allocationSize = DomainConstant.DEFAULT_ALLOCATION_SIZE)
    @Column(name = "RING_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "DESIGN_ID")
    private Design design;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "DIAMOND_ID")
    private Diamond diamond;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "METAL_ID")
    private Metal metal;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "WARRANTY_ID")
    private Warranty warranty;
}
