package com.cplerings.core.domain.order;

import com.cplerings.core.common.database.DatabaseConstant;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.file.Image;
import com.cplerings.core.domain.order.status.TransportationNote;
import com.cplerings.core.domain.shared.AbstractOrderEntity;

import jakarta.persistence.OneToOne;
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import java.util.Set;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_transport_order")
public class TransportationOrder extends AbstractOrderEntity {

    private static final String TRANSPORT_ORDER_SEQUENCE = "transport_order_seq";

    @Id
    @GeneratedValue(generator = TRANSPORT_ORDER_SEQUENCE, strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = TRANSPORT_ORDER_SEQUENCE, allocationSize = DatabaseConstant.SEQ_ALLOCATION_SIZE)
    @Column(name = "transport_order_id")
    private Long id;

    @Column(name = "status", nullable = false, length = DatabaseConstant.DEFAULT_ENUM_LENGTH)
    @Enumerated(EnumType.STRING)
    private TransportStatus status;

    @Column(name = "receiver_name", nullable = false)
    private String receiverName;

    @Column(name = "receiver_phone", nullable = false)
    private String receiverPhone;

    @Column(name = "delivery_address", nullable = false)
    private String deliveryAddress;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "custom_order_id")
    private CustomOrder customOrder;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "transporter_id")
    private Account transporter;

    @OneToMany(mappedBy = "transportationOrder", fetch = FetchType.LAZY)
    private Set<TransportationNote> transportationNotes;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id")
    private Image image;
}
