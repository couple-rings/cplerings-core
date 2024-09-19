package com.cplerings.core.domain.ring;

import com.cplerings.core.common.database.DatabaseConstant;
import com.cplerings.core.domain.AbstractEntity;
import com.cplerings.core.domain.DomainConstant;
import com.cplerings.core.domain.design.Design;
import com.cplerings.core.domain.diamond.Diamond;
import com.cplerings.core.domain.metal.Metal;
import com.cplerings.core.domain.order.OrderRing;

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
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import java.util.Set;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_ring", schema = DatabaseConstant.SCHEME_CORE)
public class Ring extends AbstractEntity {

    private static final String RING_SEQUENCE = "ring_seq";

    @Id
    @GeneratedValue(generator = RING_SEQUENCE, strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = RING_SEQUENCE, allocationSize = DomainConstant.DEFAULT_ALLOCATION_SIZE)
    @Column(name = "ring_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "design_id")
    private Design design;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "diamond_id")
    private Diamond diamond;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "metal_id")
    private Metal metal;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "warranty_id")
    private Warranty warranty;

    @OneToMany(mappedBy = "ring", fetch = FetchType.LAZY)
    private Set<OrderRing> orderRings;
}
