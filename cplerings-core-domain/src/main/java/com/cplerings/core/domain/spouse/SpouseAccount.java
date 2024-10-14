package com.cplerings.core.domain.spouse;

import com.cplerings.core.common.database.DatabaseConstant;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.shared.AbstractEntity;

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
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "tbl_spouse_account",
        uniqueConstraints = @UniqueConstraint(
                name = "uq_spouse_account",
                columnNames = { "spouse_id", "customer_id" }
        )
)
public class SpouseAccount extends AbstractEntity {

    private static final String SPOUSE_ACCOUNT_SEQUENCE = "spouse_account_seq";

    @Id
    @GeneratedValue(generator = SPOUSE_ACCOUNT_SEQUENCE, strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = SPOUSE_ACCOUNT_SEQUENCE, allocationSize = DatabaseConstant.SEQ_ALLOCATION_SIZE)
    @Column(name = "spouse_account_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "spouse_id")
    private Spouse spouse;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id")
    private Account customer;
}
