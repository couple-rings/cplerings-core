package com.cplerings.core.domain.custom;

import java.util.Set;

import com.cplerings.core.common.database.DatabaseConstant;
import com.cplerings.core.domain.AbstractEntity;
import com.cplerings.core.domain.DomainConstant;
import com.cplerings.core.domain.account.Account;

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
@Table(name = "tbl_custom_request", schema = DatabaseConstant.SCHEME_CORE)
public class CustomRequest extends AbstractEntity {

    private static final String CUSTOM_REQUEST_SEQUENCE = "custom_request_seq";

    @Id
    @GeneratedValue(generator = CUSTOM_REQUEST_SEQUENCE, strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = CUSTOM_REQUEST_SEQUENCE, allocationSize = DomainConstant.DEFAULT_ALLOCATION_SIZE)
    @Column(name = "custom_request_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id")
    private Account customer;

    @OneToOne(mappedBy = "customRequest")
    private Contract contract;

    @OneToMany(mappedBy = "customRequest", fetch = FetchType.LAZY)
    private Set<CustomRequestTracing> tracings;
}
