package com.cplerings.core.domain.order.status;

import java.time.Instant;

import com.cplerings.core.common.database.DatabaseConstant;
import com.cplerings.core.domain.order.TransportationOrder;
import com.cplerings.core.domain.shared.AbstractEntity;

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
@Table(name = "tbl_transportation_note")
public class TransportationNote extends AbstractEntity {

    private static final String TRANSPORTATION_ORDER_NOTE_SEQUENCE = "transportation_note_seq";

    @Id
    @GeneratedValue(generator = TRANSPORTATION_ORDER_NOTE_SEQUENCE, strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = TRANSPORTATION_ORDER_NOTE_SEQUENCE, allocationSize = DatabaseConstant.SEQ_ALLOCATION_SIZE)
    @Column(name = "transportation_note_id")
    private Long id;

    @Column(name = "date", nullable = false)
    private Instant date;

    @Column(name = "note")
    private String note;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "transport_order_id")
    private TransportationOrder transportationOrder;
}
