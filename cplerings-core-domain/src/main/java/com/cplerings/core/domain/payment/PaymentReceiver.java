package com.cplerings.core.domain.payment;

import com.cplerings.core.common.database.DatabaseConstant;
import com.cplerings.core.domain.shared.AbstractEntity;

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
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_payment_receiver")
public class PaymentReceiver extends AbstractEntity {

    private static final String PAYMENT_RECEIVER_SEQUENCE = "payment_receiver_seq";

    @Id
    @GeneratedValue(generator = PAYMENT_RECEIVER_SEQUENCE, strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = PAYMENT_RECEIVER_SEQUENCE, allocationSize = DatabaseConstant.SEQ_ALLOCATION_SIZE)
    @Column(name = "payment_receiver_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "payment_id")
    private Payment payment;

    @Column(name = "receiver_id", nullable = false)
    private String receiverId;

    @Enumerated(EnumType.STRING)
    @Column(name = "receiver_type", length = DatabaseConstant.DEFAULT_ENUM_LENGTH, nullable = false)
    private PaymentReceiverType receiverType;
}
