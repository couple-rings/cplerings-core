package com.cplerings.core.domain.transportation;

import com.cplerings.core.common.database.DatabaseConstant;
import com.cplerings.core.domain.AbstractEntity;
import com.cplerings.core.domain.DomainConstant;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.address.Address;
import com.cplerings.core.domain.order.Order;

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
@Table(name = "tbl_transportation", schema = DatabaseConstant.SCHEME_CORE)
public class Transportation extends AbstractEntity {

    private static final String TRANSPORTATION_SEQUENCE = "transportation_seq";

    @Id
    @GeneratedValue(generator = TRANSPORTATION_SEQUENCE, strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = TRANSPORTATION_SEQUENCE, allocationSize = DomainConstant.DEFAULT_ALLOCATION_SIZE)
    @Column(name = "transportation_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "transporter_id")
    private Account transporter;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "address_id")
    private Address address;
}
