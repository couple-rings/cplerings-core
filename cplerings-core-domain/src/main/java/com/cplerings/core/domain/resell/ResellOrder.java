package com.cplerings.core.domain.resell;

import com.cplerings.core.common.database.DatabaseConstant;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.file.Image;
import com.cplerings.core.domain.jewelry.Jewelry;
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
@Table(name = "tbl_resell_order")
public class ResellOrder extends AbstractEntity {

    private static final String RESELL_ORDER_SEQUENCE = "resell_order_seq";

    @Id
    @GeneratedValue(generator = RESELL_ORDER_SEQUENCE, strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = RESELL_ORDER_SEQUENCE, allocationSize = DatabaseConstant.SEQ_ALLOCATION_SIZE)
    @Column(name = "resell_order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "jewelry_id")
    private Jewelry jewelry;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id")
    private Account customer;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "staff_id")
    private Account staff;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method", length = DatabaseConstant.DEFAULT_ENUM_LENGTH, nullable = false)
    private PaymentMethod paymentMethod;

    @Embedded
    private Money amount;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "proof_image_id")
    private Image proofImage;

    @Column(name = "note", length = DatabaseConstant.DEFAULT_COMMENT_LENGTH, nullable = false)
    private String note;
}
