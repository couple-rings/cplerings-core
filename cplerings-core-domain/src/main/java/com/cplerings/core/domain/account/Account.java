package com.cplerings.core.domain.account;

import java.util.Set;

import com.cplerings.core.common.database.DatabaseConstant;
import com.cplerings.core.domain.AbstractEntity;
import com.cplerings.core.domain.Modifier;
import com.cplerings.core.domain.address.Address;
import com.cplerings.core.domain.custom.CustomRequest;
import com.cplerings.core.domain.spouse.Spouse;
import com.cplerings.core.domain.spouse.SpouseVerificationRequest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
@Table(name = "tbl_account", schema = DatabaseConstant.SCHEME_CORE)
public class Account extends AbstractEntity implements Modifier {

    private static final String ACCOUNT_SEQUENCE = "account_seq";

    @Id
    @GeneratedValue(generator = ACCOUNT_SEQUENCE, strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = ACCOUNT_SEQUENCE, allocationSize = 10)
    @Column(name = "account_id")
    private Long id;

    @Column(name = "email", length = 100, unique = true, nullable = false)
    private String email;

    @Column(name = "password", length = 62, unique = true, nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", length = 12, nullable = false)
    private Role role;

    @OneToOne(mappedBy = "customer")
    private Spouse spouse;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 10, nullable = false)
    private AccountStatus status;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private Set<CustomRequest> customRequests;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private Set<SpouseVerificationRequest> spouseVerificationRequests;

    @Override
    public String getModifierName() {
        return getEmail();
    }
}
