package com.cplerings.core.domain.custom;

import com.cplerings.core.common.database.DatabaseConstant;
import com.cplerings.core.domain.AbstractEntity;
import com.cplerings.core.domain.DomainConstant;
import com.cplerings.core.domain.design.Design;
import com.cplerings.core.domain.transaction.Deposit;

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
@Table(name = "tbl_contract", schema = DatabaseConstant.SCHEME_CORE)
public class Contract extends AbstractEntity {

    private static final String CONTRACT_SEQUENCE = "contract_seq";

    @Id
    @GeneratedValue(generator = CONTRACT_SEQUENCE, strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = CONTRACT_SEQUENCE, allocationSize = DomainConstant.DEFAULT_ALLOCATION_SIZE)
    @Column(name = "contract_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "design_id")
    private Design design;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "custom_request_id")
    private CustomRequest customRequest;

    @OneToMany(mappedBy = "contract", fetch = FetchType.LAZY)
    private Set<Deposit> deposits;
}
