package com.cplerings.core.domain.order;

import com.cplerings.core.common.database.DatabaseConstant;
import com.cplerings.core.domain.AbstractEntity;
import com.cplerings.core.domain.DomainConstant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "tbl_order_tracing", schema = DatabaseConstant.SCHEME_CORE)
public class OrderTracing extends AbstractEntity {

    private static final String ORDER_TRACING_SEQUENCE = "order_tracing_seq";

    @Id
    @GeneratedValue(generator = ORDER_TRACING_SEQUENCE, strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = ORDER_TRACING_SEQUENCE, allocationSize = DomainConstant.DEFAULT_ALLOCATION_SIZE)
    @Column(name = "order_tracing_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 10, nullable = false)
    private OrderStatus status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id")
    private Order order;
}
