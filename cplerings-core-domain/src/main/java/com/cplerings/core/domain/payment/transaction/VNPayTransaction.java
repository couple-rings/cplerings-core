package com.cplerings.core.domain.payment.transaction;

import com.cplerings.core.common.database.DatabaseConstant;
import com.cplerings.core.domain.payment.Payment;
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
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import java.time.Instant;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_vnpay_transaction")
public class VNPayTransaction extends AbstractEntity {

    private static final String VNPAY_TRANSACTION_SEQUENCE = "vnpay_transaction_seq";

    @Id
    @GeneratedValue(generator = VNPAY_TRANSACTION_SEQUENCE, strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = VNPAY_TRANSACTION_SEQUENCE, allocationSize = DatabaseConstant.SEQ_ALLOCATION_SIZE)
    @Column(name = "vnpay_transaction_id")
    private Long id;

    @Embedded
    private Money amount;

    @Column(name = "bank_code", length = DatabaseConstant.DEFAULT_BANK_CODE_LENGTH, nullable = false)
    private String bankCode;

    @Column(name = "bank_transfer_id", nullable = false)
    private String bankTransferId;

    @Column(name = "card_type", length = DatabaseConstant.DEFAULT_CARD_TYPE_LENGTH, nullable = false)
    private String cardType;

    @Column(name = "pay_date", nullable = false)
    private Instant payDate;

    @Column(name = "order_info")
    private String orderInfo;

    @Column(name = "transaction_id", nullable = false)
    private String transactionId;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "payment_id")
    private Payment payment;

    @Column(name = "secure_hash", length = DatabaseConstant.DEFAULT_PAYMENT_SECURE_HASH_LENGTH, nullable = false)
    private String secureHash;
}
