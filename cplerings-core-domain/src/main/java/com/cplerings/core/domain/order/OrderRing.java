package com.cplerings.core.domain.order;

import com.cplerings.core.common.database.DatabaseConstant;
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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_order_ring", schema = DatabaseConstant.SCHEME_CORE)
public class OrderRing extends AbstractEntity {

    private static final String ORDER_RING_SEQUENCE = "order_ring_seq";

    @Id
    @GeneratedValue(generator = ORDER_RING_SEQUENCE, strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = ORDER_RING_SEQUENCE, allocationSize = DomainConstant.DEFAULT_ALLOCATION_SIZE)
    @Column(name = "order_ring_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ring_id")
    private Ring ring;
}
