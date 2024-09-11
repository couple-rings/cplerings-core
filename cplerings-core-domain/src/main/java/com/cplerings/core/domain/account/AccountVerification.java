package com.cplerings.core.domain.account;

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
@Table(name = "ACCOUNT_VERIFICATION")
public class AccountVerification extends AbstractEntity {

    private static final String ACCOUNT_VERIFICATION_SEQUENCE = "ACCOUNT_VERIFICATION_SEQ";

    @Id
    @GeneratedValue(generator = ACCOUNT_VERIFICATION_SEQUENCE, strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = ACCOUNT_VERIFICATION_SEQUENCE, allocationSize = 10)
    @Column(name = "ACCOUNT_VERIFICATION_ID")
    private Long id;

    @Column(name = "VERIFICATION_CODE", length = 6, nullable = false)
    private String verificationCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", length = 10, nullable = false)
    private VerificationCodeStatus status;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ACCOUNT_ID")
    private Account account;
}
