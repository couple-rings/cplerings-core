package com.cplerings.core.domain.order;

import com.cplerings.core.common.database.DatabaseConstant;
import com.cplerings.core.domain.AbstractEntity;
import com.cplerings.core.domain.DomainConstant;
import com.cplerings.core.domain.transaction.Transaction;

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
@Table(name = "tbl_order", schema = DatabaseConstant.SCHEME_CORE)
public class Order extends AbstractEntity {

    private static final String ORDER_SEQUENCE = "order_seq";

    @Id
    @GeneratedValue(generator = ORDER_SEQUENCE, strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = ORDER_SEQUENCE, allocationSize = DomainConstant.DEFAULT_ALLOCATION_SIZE)
    @Column(name = "order_id")
    private Long id;

    @OneToOne(mappedBy = "order")
    private Transaction transaction;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    private Set<OrderRing> orderRings;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    private Set<OrderTracing> tracings;
}
