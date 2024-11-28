package com.cplerings.core.domain.order;

import com.cplerings.core.common.database.DatabaseConstant;
import com.cplerings.core.domain.ring.Ring;
import com.cplerings.core.domain.shared.AbstractEntity;

import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_transport_order_history")
public class TransportOrderHistory extends AbstractEntity {

    private static final String TRANSPORT_ORDER_HISTORY_SEQUENCE = "transport_order_history_seq";

    @Id
    @GeneratedValue(generator = TRANSPORT_ORDER_HISTORY_SEQUENCE, strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = TRANSPORT_ORDER_HISTORY_SEQUENCE, allocationSize = DatabaseConstant.SEQ_ALLOCATION_SIZE)
    @Column(name = "transport_order_history_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = DatabaseConstant.DEFAULT_ENUM_LENGTH, nullable = false)
    private TransportStatus status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "transport_order_id")
    private TransportationOrder transportationOrder;
}
