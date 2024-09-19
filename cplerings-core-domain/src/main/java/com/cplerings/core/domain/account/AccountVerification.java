package com.cplerings.core.domain.account;

import com.cplerings.core.common.database.DatabaseConstant;
import com.cplerings.core.domain.AbstractEntity;

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
@Table(name = "tbl_account_verification", schema = DatabaseConstant.SCHEME_CORE)
public class AccountVerification extends AbstractEntity {

    private static final String ACCOUNT_VERIFICATION_SEQUENCE = "account_verification_seq";

    @Id
    @GeneratedValue(generator = ACCOUNT_VERIFICATION_SEQUENCE, strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = ACCOUNT_VERIFICATION_SEQUENCE, allocationSize = 10)
    @Column(name = "account_verification_id")
    private Long id;

    @Column(name = "verification_code", length = 6, nullable = false)
    private String verificationCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 10, nullable = false)
    private VerificationCodeStatus status;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "account_id")
    private Account account;
}
