package com.cplerings.core.domain.spouse;

import com.cplerings.core.common.database.DatabaseConstant;
import com.cplerings.core.domain.AbstractEntity;
import com.cplerings.core.domain.DomainConstant;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.agreement.Agreement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "tbl_spouse", schema = DatabaseConstant.SCHEME_CORE)
public class Spouse extends AbstractEntity {

    private static final String SPOUSE_SEQUENCE = "spouse_seq";

    @Id
    @GeneratedValue(generator = SPOUSE_SEQUENCE, strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = SPOUSE_SEQUENCE, allocationSize = DomainConstant.DEFAULT_ALLOCATION_SIZE)
    @Column(name = "spouse_id")
    private Long id;

    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    @Column(name = "middle_name", length = 50)
    private String middleName;

    @Column(name = "last_name", length = 50, nullable = false)
    private String lastName;

    @Column(name = "birth_date", nullable = false)
    private Instant birthDate;

    @Column(name = "id_card_number", length = 12, nullable = false, unique = true)
    private String idCardNumber;

    @Column(name = "id_card_image_url", nullable = false, unique = true)
    private String idCardImageURL;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", unique = true)
    private Account customer;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "verification_request_id")
    private SpouseVerificationRequest verificationRequest;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agreement_id")
    private Agreement agreement;
}
