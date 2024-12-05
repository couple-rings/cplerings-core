package com.cplerings.core.domain.refund;

import com.cplerings.core.common.database.DatabaseConstant;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.file.Image;
import com.cplerings.core.domain.order.CustomOrder;
import com.cplerings.core.domain.order.StandardOrder;
import com.cplerings.core.domain.shared.AbstractEntity;
import com.cplerings.core.domain.shared.valueobject.Money;

import jakarta.persistence.AttributeOverride;
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
@Table(name = "tbl_refund")
public class Refund extends AbstractEntity {

    private static final String REFUND_SEQUENCE = "refund_seq";

    @Id
    @GeneratedValue(generator = REFUND_SEQUENCE, strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = REFUND_SEQUENCE, allocationSize = DatabaseConstant.SEQ_ALLOCATION_SIZE)
    @Column(name = "refund_id")
    private Long id;

    @Column(name = "reason", nullable = false)
    private String reason;

    @Column(name = "method", nullable = false)
    private RefundMethod method;

    @Embedded
    @AttributeOverride(name = "amount", column = @Column(name = "price", precision = 12, scale = 3, nullable = false))
    private Money amount;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "staff_id")
    private Account staff;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "image_id")
    private Image proofImage;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "standard_order_id")
    private StandardOrder standardOrder;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "custom_order_id")
    private CustomOrder customOrder;
}
