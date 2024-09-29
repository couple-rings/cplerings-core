package com.cplerings.core.domain.account;

import com.cplerings.core.common.database.DatabaseConstant;
import com.cplerings.core.domain.AbstractEntity;
import com.cplerings.core.domain.Modifier;
import com.cplerings.core.domain.blog.Blog;
import com.cplerings.core.domain.branch.Branch;
import com.cplerings.core.domain.design.DesignSession;
import com.cplerings.core.domain.spouse.SpouseAccount;

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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import java.util.Set;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_account")
public class Account extends AbstractEntity implements Modifier {

    private static final String ACCOUNT_SEQUENCE = "account_seq";

    @Id
    @GeneratedValue(generator = ACCOUNT_SEQUENCE, strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = ACCOUNT_SEQUENCE, allocationSize = DatabaseConstant.SEQ_ALLOCATION_SIZE)
    @Column(name = "account_id")
    private Long id;

    @Column(name = "email", length = 100, unique = true, nullable = false)
    private String email;

    @Column(name = "password", length = 62, unique = true, nullable = false)
    private String password;

    @Column(name = "username", length = 50, unique = true, nullable = false)
    private String username;

    @Column(name = "phone", length = 10, unique = true)
    private String phone;

    @Column(name = "avatar", unique = true)
    private String avatar;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", length = 12, nullable = false)
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 10, nullable = false)
    private AccountStatus status;

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    private Set<AccountVerification> verification;

    @OneToOne(mappedBy = "account")
    private PushNotification pushNotification;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private Set<SpouseAccount> spouseAccounts;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private Set<DesignSession> designSessions;

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    private Set<Blog> blogs;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "branch_id")
    private Branch branch;

    @Override
    public String getModifierName() {
        return getEmail();
    }
}
