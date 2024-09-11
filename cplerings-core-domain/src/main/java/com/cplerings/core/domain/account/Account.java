package com.cplerings.core.domain.account;

import com.cplerings.core.domain.AbstractEntity;
import com.cplerings.core.domain.agreement.Spouse;

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
@Table(name = "ACCOUNT")
public class Account extends AbstractEntity {

    private static final String ACCOUNT_SEQUENCE = "ACCOUNT_SEQ";

    @Id
    @GeneratedValue(generator = ACCOUNT_SEQUENCE, strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = ACCOUNT_SEQUENCE, allocationSize = 10)
    @Column(name = "ACCOUNT_ID")
    private Long id;

    @Column(name = "EMAIL", length = 100, unique = true, nullable = false)
    private String email;

    @Column(name = "PASSWORD", length = 62, unique = true, nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE")
    private Role role;

    @OneToOne(mappedBy = "account")
    private Spouse spouse;
}
