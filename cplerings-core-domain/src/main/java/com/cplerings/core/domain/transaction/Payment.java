package com.cplerings.core.domain.transaction;

import com.cplerings.core.common.database.DatabaseConstant;
import com.cplerings.core.domain.AbstractEntity;
import com.cplerings.core.domain.DomainConstant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "tbl_payment", schema = DatabaseConstant.SCHEME_CORE)
public class Payment extends AbstractEntity {

    private static final String PAYMENT_SEQUENCE = "payment_seq";

    @Id
    @GeneratedValue(generator = PAYMENT_SEQUENCE, strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = PAYMENT_SEQUENCE, allocationSize = DomainConstant.DEFAULT_ALLOCATION_SIZE)
    @Column(name = "payment_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", length = 10, nullable = false)
    private PaymentType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 10, nullable = false)
    private PaymentStatus status;

    @OneToOne(mappedBy = "payment")
    private Transaction transaction;
}
