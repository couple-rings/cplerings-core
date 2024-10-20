package com.cplerings.core.domain.payment;

import com.cplerings.core.common.database.DatabaseConstant;
import com.cplerings.core.domain.payment.transaction.VNPayTransaction;
import com.cplerings.core.domain.shared.AbstractEntity;
import com.cplerings.core.domain.shared.valueobject.Money;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_payment")
public class Payment extends AbstractEntity {

    private static final String PAYMENT_SEQUENCE = "payment_seq";

    @Id
    @GeneratedValue(generator = PAYMENT_SEQUENCE, strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = PAYMENT_SEQUENCE, allocationSize = DatabaseConstant.SEQ_ALLOCATION_SIZE)
    @Column(name = "payment_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", length = DatabaseConstant.DEFAULT_ENUM_LENGTH, nullable = false)
    private PaymentType type;

    @Column(name = "description", nullable = false)
    private String description;

    @Embedded
    private Money amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = DatabaseConstant.DEFAULT_ENUM_LENGTH, nullable = false)
    private PaymentStatus status;

    @Column(name = "secure_hash", length = DatabaseConstant.DEFAULT_PAYMENT_SECURE_HASH_LENGTH, nullable = false)
    private String secureHash;

    @OneToOne(mappedBy = "payment")
    private VNPayTransaction vnPayTransaction;
}
