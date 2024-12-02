package com.cplerings.core.domain.design.request;

import java.util.Set;

import com.cplerings.core.common.database.DatabaseConstant;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.payment.Payment;
import com.cplerings.core.domain.shared.AbstractEntity;
import com.cplerings.core.domain.shared.valueobject.Money;

import jakarta.persistence.AttributeOverride;
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
import jakarta.persistence.OneToMany;
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
@Table(name = "tbl_custom_request")
public class CustomRequest extends AbstractEntity {

    private static final String CUSTOM_REQUEST_SEQUENCE = "custom_request_seq";

    @Id
    @GeneratedValue(generator = CUSTOM_REQUEST_SEQUENCE, strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = CUSTOM_REQUEST_SEQUENCE, allocationSize = DatabaseConstant.SEQ_ALLOCATION_SIZE)
    @Column(name = "custom_request_id")
    private Long id;

    @Column(name = "comment", length = DatabaseConstant.DEFAULT_COMMENT_LENGTH)
    private String comment;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = DatabaseConstant.DEFAULT_ENUM_LENGTH, nullable = false)
    private CustomRequestStatus status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id")
    private Account customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_id")
    private Account staff;

    @Embedded
    @AttributeOverride(name = "amount", column = @Column(name = "design_fee", precision = 12, scale = 3, nullable = false))
    private Money designFee;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "payment_id")
    private Payment payment;

    @OneToMany(mappedBy = "customRequest", fetch = FetchType.LAZY)
    private Set<DesignCustomRequest> designCustomRequests;

    @OneToMany(mappedBy = "customRequest", fetch = FetchType.LAZY)
    private Set<CustomRequestHistory> customRequestHistories;
}
